package com.sukhdev.reboot.careerreboot.labs.day1;

public record Contractor(
    String company,
    double hourlyRate
) implements EmployeeType {}