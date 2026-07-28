package com.sukhdev.reboot.careerreboot.labs.day2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    private final WelcomeService welcomeService;

    public WelcomeController(WelcomeService welcomeService) 
    {
        this.welcomeService = welcomeService;

                System.out.println(
                "Controller created : "
                + this.hashCode());

        System.out.println(
                "Injected Service : "
                + welcomeService.hashCode());
    }

    @GetMapping("/welcome")
    public WelcomeResponse welcome() 
    {
        return welcomeService.getMessage();
    }

      @GetMapping("/info")
    public ApplicationInfoResponse info() 
    {
        return welcomeService.getapplicationInfo();
    }
}
