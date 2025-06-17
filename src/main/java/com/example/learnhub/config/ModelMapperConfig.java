package com.example.learnhub.config;

import com.example.learnhub.dto.UserDTO;
import com.example.learnhub.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        //Skip the creation date of the User entity
        mapper.typeMap(User.class, UserDTO.class)
                .addMappings(m -> m.skip(UserDTO::setCreatedAt));

        return mapper;
    }
}