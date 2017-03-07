package com.buiha;

import com.buiha.storage.FileStorageService;
import com.buiha.storage.StorageException;
import com.buiha.storage.StorageProperties;
import com.buiha.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by tal on 2017/03/07.
 */

@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    @Autowired
    private StorageProperties storageProperties;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
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
