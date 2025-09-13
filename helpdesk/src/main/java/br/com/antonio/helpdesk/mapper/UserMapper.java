package br.com.antonio.helpdesk.mapper;

import br.com.antonio.helpdesk.domain.User;
import br.com.antonio.helpdesk.dto.CreatedUserDto;
import br.com.antonio.helpdesk.dto.UserDTO;
import br.com.antonio.helpdesk.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toDomain(UserEntity entity);

    UserDTO toDto(User domain);

    UserEntity toEntity(User domain);

    User toDomain(CreatedUserDto dto);
}
