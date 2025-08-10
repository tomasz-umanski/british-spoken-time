package pl.umanski;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SpokenTimeConverter converter = new BritishSpokenTimeConverter();
        Scanner scanner = new Scanner(System.in);

        System.out.println("British Spoken Time Converter");
        System.out.println("Enter time in HH:MM or H:MM format (or 'quit' to exit):");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if ("quit".equalsIgnoreCase(input)) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                Time parsedTime = TimeParser.parse(input);
                String spokenTime = converter.convert(parsedTime);
                System.out.println("Spoken time: " + spokenTime);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please enter time in HH:MM or H:MM format (e.g., 7:30)");
            }
        }

        scanner.close();
    }

}

