package com.sukhdev.reboot.careerreboot.labs.day1;

public class EmployeeProcessor {

    public static String process(EmployeeType employee) {

        return switch (employee) {

            case Developer developer ->
                    "Developer - Primary Language: "
                            + developer.primaryLanguage();

            case Manager manager ->
                    "Manager - Team Size: "
                            + manager.teamSize();

            case Intern intern ->
                    "Intern - Mentor: "
                            + intern.mentorName();

            case Contractor contractor ->
                    "Contractor - Company: "
                            + contractor.company() + "Hourly Rate " + contractor.hourlyRate();                
        };
    }
}