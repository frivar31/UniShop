import Data.Entities.Users.Client;
import Utils.PrintUtil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        PrintUtil.welcomeMessage("Bienvenue dans la Plateforme Unishop !!!");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choisissez une option: ");
        System.out.println("1. S'inscrire");
        System.out.println("2. Se connecter");

        int option = scanner.nextInt();
        if (option == 1) {
            System.out.println("Choisissez une option d'inscription");
            System.out.println("1. Vendeur");
            System.out.println("2. Acheteur");
            try {
                option = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.err.println("Ooops! option d'inscription doit etre chiffre 1 ou 2");
            }
            if (option == 2) {
                scanner = new Scanner(System.in) ;
                System.out.print("Saisissez votre Prenom: ");
                String firstName = scanner.nextLine();

                System.out.print("Saisissez votre nom: ");
                String lastName = scanner.nextLine();

                System.out.print("Saisissez votre pseudo: ");
                String pseudo = scanner.nextLine();

                System.out.print("Saisissez votre email: ");
                String email = scanner.nextLine();

                scanner = new Scanner(System.in);
                System.out.print("Saisissez votre numero: ") ;
                long number = scanner.nextLong() ;

                scanner = new Scanner(System.in);
                System.out.print("Saisissez votre adresse de livraison: ");
                String shipAddress = scanner.nextLine();

                Client client = new Client(firstName, lastName, email, pseudo, number, shipAddress);

            }

        }


    }
}