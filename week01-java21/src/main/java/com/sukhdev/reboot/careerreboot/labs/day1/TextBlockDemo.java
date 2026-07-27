package com.sukhdev.reboot.careerreboot.labs.day1;

public class TextBlockDemo {

    public static void main(String[] args) {

        String employeeJson = """
                {
                    "id": 101,
                    "name": "Sukhdev",
                    "department": "Engineering",
                    "salary": 150000
                }
                """;

        System.out.println(employeeJson);

    }
}