package com.dagg.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dagg
 */

@Configuration
public class ComponentConfiguration {

	@Bean
	public ModelMapper initModelMapper() {
		return new ModelMapper();
	}

}

