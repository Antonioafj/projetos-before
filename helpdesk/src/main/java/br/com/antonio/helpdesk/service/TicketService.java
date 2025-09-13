package br.com.antonio.helpdesk.service;

import br.com.antonio.helpdesk.domain.Attachment;
import br.com.antonio.helpdesk.domain.Ticket;
import br.com.antonio.helpdesk.domain.TicketInteraction;
import br.com.antonio.helpdesk.dto.AttachmentDto;
import br.com.antonio.helpdesk.dto.CreatedTicketDto;
import br.com.antonio.helpdesk.dto.TicketInteractionDto;
import br.com.antonio.helpdesk.entity.TicketAttachmentEntity;
import br.com.antonio.helpdesk.entity.TicketEntity;
import br.com.antonio.helpdesk.entity.TicketInteractionEntity;
import br.com.antonio.helpdesk.entity.UserEntity;
import br.com.antonio.helpdesk.enums.TicketStatus;
import br.com.antonio.helpdesk.exceptions.BusinessException;
import br.com.antonio.helpdesk.mapper.TicketMapper;
import br.com.antonio.helpdesk.repository.TicketAttachmentRepository;
import br.com.antonio.helpdesk.repository.TicketInteractionRepository;
import br.com.antonio.helpdesk.repository.TicketRepository;
import br.com.antonio.helpdesk.repository.UserRepository;
import br.com.antonio.helpdesk.utils.FileUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketInteractionRepository ticketInteractionRepository;
    private final TicketAttachmentRepository ticketAttachmentRepository;
    private final UserRepository userRepository;
    private final TicketMapper mapper;

    @Value("${helpdesk.attachments-folder}")
    private String attachmentsFolder;

    @Transactional
    public Ticket createTicket(CreatedTicketDto newTicketDto, String username){

        UserEntity createdByUser = userRepository.findByUsername(username).orElse(null);

        if (createdByUser == null) {
            throw new EntityNotFoundException("User not found with provided id");
        }

        TicketEntity entity = new TicketEntity();
        entity.setSubject(newTicketDto.getSubject());
        entity.setDescription(newTicketDto.getDescription());
        entity.setCreateBy(createdByUser);
        entity.setStatus(TicketStatus.OPEN);
        entity.setCreateAt(new Date());

        entity = ticketRepository.save(entity);

        if (newTicketDto.getAttachments() != null && !newTicketDto.getAttachments().isEmpty()) {
            for (AttachmentDto attachment : newTicketDto.getAttachments()) {
                TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
                ticketAttachmentEntity.setTicket(entity);
                ticketAttachmentEntity.setCreateBy(createdByUser);
                ticketAttachmentEntity.setCreateAt(new Date());
                ticketAttachmentEntity.setFilename(attachment.getFilename());
                ticketAttachmentEntity = ticketAttachmentRepository.save(ticketAttachmentEntity);
                saveFileToDisk(ticketAttachmentEntity, attachment.getContent());


            }
        }
        return mapper.toDomain(entity);
    }

    public Ticket ticketInteract(TicketInteraction domain, String username) {

        TicketEntity ticket = ticketRepository.findById(domain.getTicketId()).orElse(null);

        if (ticket == null) {
            throw new BusinessException("Ticket not found with provided id");
        }

        UserEntity user = userRepository.findByUsername(username).orElse(null);

        if (user == null) {
            throw new BusinessException("User not found with provided id");
        }

        Date now = new Date();
        TicketStatus status = TicketStatus.IN_PROGRESS;
        if (ticket.getCreateBy().getId() != user.getId()) {
            ticket.setSupportUser(user);
            status = TicketStatus.AWAITING_CUSTOMER_ANSWER;
        }

        TicketInteractionEntity entity = new TicketInteractionEntity();
        entity.setTicket(ticket);
        entity.setMessage(domain.getMessage());
        entity.setCreateBy(user);
        entity.setSendByUser(user);
        entity.setCreateAt(new Date());
        entity.setStatus(status);
        entity = ticketInteractionRepository.save(entity);

        if (domain.getAttachments() != null && !domain.getAttachments().isEmpty()){
            for (Attachment attachment: domain.getAttachments()) {
                TicketAttachmentEntity ticketAttachmentEntity = new TicketAttachmentEntity();
                ticketAttachmentEntity.setTicketInteraction(entity);
                ticketAttachmentEntity.setCreateBy(user);
                ticketAttachmentEntity.setCreateAt(new Date());
                ticketAttachmentEntity.setFilename(attachment.getFilename());
                ticketAttachmentEntity = ticketAttachmentRepository.save(ticketAttachmentEntity);
                saveFileToDisk(ticketAttachmentEntity, attachment.getContent());
            }
        }

        ticket.setUpdatedAt(now);
        ticket.setUpdatedBy(user.getId());
        ticket.setStatus(status);
        ticket = ticketRepository.save(ticket);

        return mapper.toDomain(ticket);
    }

    private void saveFileToDisk(TicketAttachmentEntity entity, String content) {
        System.out.println("Conteúdo Base64 recebido: " + content);

        byte[] attachmentContent = null;

        try {
            attachmentContent = FileUtils.converteBase64ToByteArray(content);
            String fileName = entity.getId().toString();

            FileUtils.saveByteArrayToFile(attachmentContent, new File(attachmentsFolder + fileName));
        } catch (IOException ex) {
            throw  new BusinessException("Error saving " + entity.getFilename() + " file");
        }
    }

    public List<Ticket> listAll() {
        return mapper.toDomain(ticketRepository.findAll());
    }

    public Ticket getById(UUID ticketId) {

        TicketEntity entity = ticketRepository.findById(ticketId).orElse(null);

        if (entity== null){
            throw new BusinessException("Icket was not found!!");
        }
        return mapper.toDomain(entity);
    }

    public List<TicketInteraction> getInteractionsByTicketId(UUID ticketId) {
        TicketEntity entity = ticketRepository.findById(ticketId).orElse(null);

        if (entity== null){
            throw new BusinessException("Icket was not found!!");
        }
        return mapper.toInterectionDomain(ticketInteractionRepository.findByTicket(entity));
    }
}



























