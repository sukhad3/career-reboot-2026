package com.sukhdev.reboot.careerreboot.labs.day1;

public class RecordDemo {

    public static void main(String[] args) {

        Employee emp1 =
                new Employee(1, "Sukhdev", "Engineering", 150000);

        Employee emp2 =
                new Employee(1, "Sukhdev", "Engineering", 150000);

        System.out.println(emp1);

        System.out.println(emp1.equals(emp2));

        System.out.println(emp1.hashCode());

        System.out.println(emp1.name());

    }

}