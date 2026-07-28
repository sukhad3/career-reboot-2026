package com.sukhdev.reboot.careerreboot.labs.day2;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
public class WelcomeService {

       public WelcomeService() {
        System.out.println("1. Constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("2. PostConstruct");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("3. PreDestroy");
    }

    public WelcomeResponse getMessage() 
    {
                return new WelcomeResponse(

                "Welcome to Career Reboot",

                "Spring Boot 3.5",

                "ChatGPT"

        );
    }

        public ApplicationInfoResponse getapplicationInfo() 
    {
                return new ApplicationInfoResponse(

                "Career Reboot",

                "26" ,

                "Spring Boot 3.5",

                "Dependency Injection"

        );
    }
}
