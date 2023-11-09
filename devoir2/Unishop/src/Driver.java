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
        System.out.println("2. Se connecter") ;
        int option = -1 ;
        try {
            option = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.err.println("Ooops! option doit etre chiffre 1 ou 2");
        }
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
                scanner = new Scanner(System.in);
                System.out.print("Saisissez votre Prenom: ");
                String firstName = "";
                try {
                    firstName = scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! prenom doit etre une chaine de caracteres");
                }

                System.out.print("Saisissez votre nom: ");
                String lastName = null ;
                try {
                    lastName = scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! nom doit etre une chaine de caracteres");
                }


                System.out.print("Saisissez votre pseudo: ");
                String pseudo = null;
                try {
                    pseudo = scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! pseudo doit etre une chaine de caracteres");
                }

                System.out.print("Saisissez votre email: ");
                String email = null;
                try {
                    email = scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! email doit etre une chaine de caracteres");
                }

                scanner = new Scanner(System.in);
                System.out.print("Saisissez votre numero: ");
                long number = 0;
                try {
                    number = scanner.nextLong();
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! numero doit etre un nombre");
                }

                scanner = new Scanner(System.in);
                System.out.print("Saisissez votre adresse de livraison: ");
                String shipAddress = null;
                try {
                    shipAddress = scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.err.println("Ooops! adresse de livraison doit etre une chaine de caractere");
                }
            }

        }


    }
}