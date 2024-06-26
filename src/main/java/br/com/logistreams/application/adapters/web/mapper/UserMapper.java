package br.com.logistreams.application.adapters.web.mapper;

import br.com.logistreams.application.adapters.web.dto.input.user.CreateUserDTO;
import br.com.logistreams.application.core.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UserMapper {
    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "email", target = "email")
    public abstract User toDomain(CreateUserDTO dto);
}
