package com.Job_Portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.naming.CannotProceedException;

@SpringBootApplication
@EnableScheduling
public class JobPortalApplication {


	public static void main(String[] args) {

//		System.setProperty("spring.data.mongodb.uri",
//				"mongodb+srv://dummygmail1212_db_user:g7UzsLUIHPbyqa85@jobportal.njv4pom.mongodb.net/jobPortal?retryWrites=true&w=majority&appName=jobPortal");
		SpringApplication.run(JobPortalApplication.class, args);


	}

}
