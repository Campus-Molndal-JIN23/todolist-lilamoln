package org.campusmolndal;

import java.util.Scanner;

public class InputGetter {
    static Scanner scanner = new Scanner(System.in);
    public static String getStringInput(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
    public static int getIntInput(String message) {
        System.out.println(message);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, please try again.");
            return getIntInput(message);
        }
    }
    public static int getIntInput(String message, int min, int max) {
        int input = getIntInput(message);
        if(input < min || input > max) {
            System.out.println("Invalid input, please try again.");
            return getIntInput(message, min, max);
        }
        return input;
    }

    public static boolean getBooleanInput(String s) {
        String input = getStringInput(s + " (y/n)");
        if(input.equalsIgnoreCase("y")) {
            return true;
        } else if(input.equalsIgnoreCase("n")) {
            return false;
        } else {
            System.out.println("Invalid input, please try again.");
            return getBooleanInput(s);
        }
    }
}
