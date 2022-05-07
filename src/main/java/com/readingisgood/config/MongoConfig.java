package com.readingisgood.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Umut Ismet Erdolu
 */
@Configuration
public class MongoConfig {

    public @Bean MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }
}
