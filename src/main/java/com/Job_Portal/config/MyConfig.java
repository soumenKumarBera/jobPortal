package com.Job_Portal.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class MyConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder){
        return builder.getAuthenticationManager();
    }

    @Bean
    public MongoClient mongoClient(){
        return MongoClients.create("mongodb+srv://dummygmail1212_db_user:3RdWohvZfCwBY6Oa@jobportal.njv4pom.mongodb.net/jobPortal?appName=jobPortal");
    }
}
