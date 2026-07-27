package com.sukhdev.reboot.careerreboot.labs.day1;

public class SealedClassDemo {

    public static void main(String[] args) {

        System.out.println(
                EmployeeProcessor.process(
                        new Developer("Java")));

        System.out.println(
                EmployeeProcessor.process(
                        new Manager(12)));

        System.out.println(
                EmployeeProcessor.process(
                        new Intern("Sukhdev")));

        System.out.println(
                EmployeeProcessor.process(
                        new Contractor("IWSC", 1500)));


    }
}