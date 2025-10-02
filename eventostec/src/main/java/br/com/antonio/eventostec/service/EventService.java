package br.com.antonio.eventostec.service;

import br.com.antonio.eventostec.domain.event.Event;
import br.com.antonio.eventostec.domain.event.EventRequestDTO;
import br.com.antonio.eventostec.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectAclRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventService {


    @Value("${aws.bucket.name}")
    private String bucketName;

    @Autowired
    private S3Client s3Client;

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(EventRequestDTO data) {
        String imUrl = null;

        if (data.image() != null) {
           imUrl = this.uploadImg(data.image());
        }

        Event newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.discription());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setDate(new Date(data.date()));
        newEvent.setImgUrl(imUrl);
        newEvent.setRemote(data.remote());

        eventRepository.save(newEvent);

        return newEvent;
    }

    private String uploadImg(MultipartFile multipartFile) {
        String filename = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();

        try {
            File file = this.convertMultpartToFile(multipartFile);

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(filename)
                            .contentType(multipartFile.getContentType())
                    .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromFile(file));
        file.delete();

        return this.generatePublicUrl(filename);

        } catch (Exception e) {
            System.out.println("Erro ao subir arquivo");
            e.printStackTrace(); // <-- Adicione isto para ver o erro real!
            return "";
        }
    }

    private String generatePublicUrl(String filename) {

        String region = s3Client.serviceClientConfiguration().region().id();
        return String.format("https://%s.s3.%s.amazonaws.com/%s",
                bucketName, region, filename);
    }
    private  File convertMultpartToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();
        return convFile;
    }
}





















