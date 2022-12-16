package com.example.magicbasebackend;

import com.example.magicbasebackend.dto.AddCardRequestDto;
import com.example.magicbasebackend.model.Card;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MagicBaseBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MagicBaseBackendApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap propertyMap = new PropertyMap<AddCardRequestDto, Card>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        };

        modelMapper.addMappings(propertyMap);
        return modelMapper;
    }

}
