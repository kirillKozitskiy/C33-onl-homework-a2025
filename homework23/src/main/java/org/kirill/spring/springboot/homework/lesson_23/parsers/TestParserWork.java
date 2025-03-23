package org.kirill.spring.springboot.homework.lesson_23.parsers;

import java.util.Scanner;

public class TestParserWork {

    private static int action;

    public static void main(String[] args) {

        System.out.println("Print 1 if you want to use DOM parser\n" +
                "Print 2 if you want to use SAX parser");
        action = new Scanner(System.in).nextInt();

        switch (action){
            case 1:
                new DOMParserProcessor();
                break;
            case 2:
                new SAXParserProcessor();
                break;
            default:
                System.out.println("Wrong action");
                break;
        }



    }
}
