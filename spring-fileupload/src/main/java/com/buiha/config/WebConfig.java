package com.buiha.config;

import com.buiha.Application;
import com.buiha.storage.FileStorageService;
import com.buiha.storage.StorageException;
import com.buiha.storage.StorageProperties;
import com.buiha.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by tal on 2017/03/07.
 */

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private ApplicationContext applicationContext;

    @Autowired
    private StorageProperties storageProperties;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Bean
    public StorageService storageService() {
        StorageService storageService = new FileStorageService(storageProperties);
        try {
            storageService.init();
        } catch (StorageException e) {
            logger.debug("Could not init storage");
        }
        return storageService;
    }
}
