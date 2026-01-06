package com.devas.IMS.Configs

import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.context.annotation.Configuration
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean

@Configuration
class ModelMapperConfig {

    @Bean
    fun modelMapper(): ModelMapper {
        val modelMapper = ModelMapper();
        modelMapper.configuration.isFieldMatchingEnabled = true;
        modelMapper.configuration.fieldAccessLevel = AccessLevel.PRIVATE
        modelMapper.configuration.matchingStrategy = MatchingStrategies.STANDARD
        return modelMapper;
    }
}