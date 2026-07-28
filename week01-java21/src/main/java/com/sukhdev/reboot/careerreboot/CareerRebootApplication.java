package com.sukhdev.reboot.careerreboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext ;

import com.sukhdev.reboot.careerreboot.labs.day2.WelcomeService;

@SpringBootApplication
public class CareerRebootApplication {

	public static void main(String[] args) 
	{
		        ApplicationContext context =
                SpringApplication.run(CareerRebootApplication.class, args);

        WelcomeService service1 =
                context.getBean(WelcomeService.class);

        WelcomeService service2 =
                context.getBean(WelcomeService.class);

        System.out.println("Service 1 : " + service1.hashCode());
        System.out.println("Service 2 : " + service2.hashCode());

        System.out.println(service1 == service2);
	}

}
