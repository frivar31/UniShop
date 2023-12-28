package Service;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * La classe UserInteractionService fournit des méthodes utilitaires pour interagir avec l'utilisateur
 * en récupérant des informations numériques et textuelles depuis la console.
 */
public class UserInteractionService {
    /**
     * Scanner utilisé pour lire l'entrée utilisateur depuis la console.
     */
    private final static Scanner scanner = new Scanner(System.in);

    public UserInteractionService() {
    }

    /**
     * Demande à l'utilisateur un nombre dans un intervalle spécifié et renvoie la valeur saisie.
     *
     * @param info  Message d'information à afficher à l'utilisateur.
     * @param lower Limite inférieure de l'intervalle autorisé.
     * @param upper Limite supérieure de l'intervalle autorisé.
     * @return La valeur numérique saisie par l'utilisateur dans l'intervalle spécifié.
     */
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
    /**
     * Demande à l'utilisateur une chaîne de caractères et renvoie la valeur saisie.
     *
     * @param info Message d'information à afficher à l'utilisateur.
     * @return La chaîne de caractères saisie par l'utilisateur.
     */
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
    /**
     * Demande à l'utilisateur de saisir un nombre dans un intervalle spécifié et renvoie la valeur saisie.
     * L'utilisateur est invité à entrer une nouvelle valeur tant que celle-ci n'est pas dans l'intervalle autorisé.
     *
     * @param lower Limite inférieure de l'intervalle autorisé.
     * @param upper Limite supérieure de l'intervalle autorisé.
     * @return La valeur numérique saisie par l'utilisateur dans l'intervalle spécifié.
     */
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
    /**
     * Demande à l'utilisateur un nombre dans un intervalle spécifié et renvoie la valeur saisie.
     * Cette méthode ne vérifie que la limite inférieure de l'intervalle.
     *
     * @param lower Limite inférieure de l'intervalle autorisé.
     * @return La valeur numérique saisie par l'utilisateur, supérieure ou égale à la limite inférieure.
     */
    public int getOption(int lower){
        int option = 0;
        boolean success = false;

        while (!success) {
            try {
                option = scanner.nextInt();
                scanner.nextLine();

                // Check if the entered option is within the specified bounds
                if (option >= lower) {
                    success = true;
                } else {
                    System.err.println("Veuillez entrer un nombre supérieur ou égal " + lower);
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
