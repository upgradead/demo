package com.ivan.cayabyab.gofluent.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationBean {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
