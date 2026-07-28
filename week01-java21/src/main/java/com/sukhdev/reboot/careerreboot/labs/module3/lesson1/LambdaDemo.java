package com.sukhdev.reboot.careerreboot.labs.module3.lesson1;

public class LambdaDemo {

    public static void main(String[] args) {

        Calculator add = (a, b) -> a + b;

        Calculator multiply = (a, b) -> a * b;

        System.out.println(add.calculate(10, 20));

        System.out.println(multiply.calculate(10, 20));

    }

}