package Service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInteractionService {
    private final static Scanner scanner=new Scanner(System.in);
    public UserInteractionService() {
    }
    public int getUserNumInfo(String info, int lower, int upper) {
        System.out.print(info + ": ");
        int option = 0;
        boolean success = false;
        while (!success) {
            try {
                option = scanner.nextInt();
                scanner.nextLine();
                // Check if the entered option is within the specified bounds
                if (option >= lower && option <= upper) {
                    success = true;
                } else {
                    System.err.println("Veuillez entrer un nombre entre " + lower + " et " + upper + " (inclusif) svp.");
                }
            } catch (InputMismatchException e) {
                // Handle the case where the user enters a non-integer
                System.err.println("Oops! option invale. veuillez entrer un chiffre valide svp.");
                scanner.next(); // Consume the invalid input to prevent an infinite loop
            }
        }

        return option;
    }
    public String getUserStrInfo(String info) {
        System.out.print(info + ": ");
        String input = null;
        boolean success = false;

        while (!success) {
            try {
                input = scanner.nextLine();
                success = true;
            } catch (InputMismatchException e) {
                System.err.println("Oops! " + info + " doit être une chaîne de caractères.");
                scanner.nextLine();  // Consume the invalid input to prevent an infinite loop
            }
        }
        return input;
    }
    public int getOption(int lower, int upper) {
        int option = 0;
        boolean success = false;

        while (!success) {
            try {
                option = scanner.nextInt();
                scanner.nextLine();

                // Check if the entered option is within the specified bounds
                if (option >= lower && option <= upper) {
                    success = true;
                } else {
                    System.err.println("Veuillez entrer un nombre entre " + lower + " et " + upper + " (inclusif) svp.");
                }
            } catch (InputMismatchException e) {
                // Handle the case where the user enters a non-integer
                System.err.println("Oops! option invale. veuillez entrer un chiffre valide svp.");
                scanner.next(); // Consume the invalid input to prevent an infinite loop
            }
        }

        return option;
    }

}
