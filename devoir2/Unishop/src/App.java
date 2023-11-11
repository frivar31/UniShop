import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {

    private static void getSRegistrationStream(Scanner scanner) {
        System.out.println("Choisissez une option d'inscription");
            System.out.println("1. Vendeur");
            System.out.println("2. Acheteur");
            if (getOption(scanner) == 2) {
                getClientRegistrationInfo(scanner);
            }
            else getSellerRegistrationInfo(scanner);
    }

    private static void getBookInfo(Scanner scanner) {

        List<Object> inputs = new ArrayList<>() ;
        System.out.println("Donnez les informations du Livre/Manuel: ");
        String title = getUserStrInfo(scanner,"Titre") ;
        inputs.add(title) ;
        String desc = getUserStrInfo(scanner,"Description") ;
        inputs.add(desc) ;
        String author = getUserStrInfo(scanner,"Autheur") ;
        inputs.add(author) ;
        String isbn = getUserStrInfo(scanner,"ISBN") ;
        inputs.add(isbn) ;
        String editor = getUserStrInfo(scanner,"Maison d'edition") ;
        inputs.add(editor) ;

        System.out.println("possede numero d'edition: ");
        System.out.println("1. oui");
        System.out.println("2. non");

        long editionNum = -1 ;
        if (getOption(scanner) == 1) {
            editionNum =  getUserNumInfo(scanner,"Numero d'edition") ;
            inputs.add(editionNum) ;
            scanner.nextLine() ;
        }

        String genre = getUserStrInfo(scanner,"genre: ") ;
        inputs.add(genre) ;
        String pubDate = getUserStrInfo(scanner,"date de parution (DD/MM/YYYY)") ;
        inputs.add(pubDate) ;

        System.out.println("possede numero de volume");
        System.out.println("1. oui");
        System.out.println("2. non");

        long volNum = -1 ;
        if(getOption(scanner) == 1) {
            volNum = getUserNumInfo(scanner,"Numero de volume") ;
            inputs.add(volNum) ;
            scanner.nextLine() ;
        }

        long initQuantity = getUserNumInfo(scanner,"Quantite") ;
        inputs.add(initQuantity) ;

        System.out.println("offrir des points bonus pour le produit:") ;
        System.out.println("1. oui");
        System.out.println("2. non");

        boolean success = false ;
        long points = 1 ;

        if(getOption(scanner) == 1) {

            while (!success) {
                points = getUserNumInfo(scanner,"bonus/$") ;
                if (points >= 20) {
                    System.out.println("point bonus par $ ne peut pas depasser 20");
                }
                else success = true ;
            }

        }
        inputs.add(points) ;

        double price = getUserNumInfo(scanner,"Prix") ;
        inputs.add(price) ;

        System.out.println();
        printInfo(inputs);
        System.out.println();

        success = false ;
        while (!success) {

            System.out.println();
            printInfo(inputs);
            System.out.println();

            System.out.println("Valider les informations: du produit ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();

            if (getOption(scanner) == 1) {
                System.out.println("produit ajoutes avec succes");
                success = true ;
            }
            else {
                System.out.println("Voulez vous modifier les informations du produit:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (getOption(scanner) == 1) {
                    System.out.println("Choisir information a modifier: ");


                    while (!success) {
                        System.out.println("1. Titre") ;
                        System.out.println("2. Description");
                        System.out.println("3. Autheur");
                        System.out.println("4. Maison d'edition");
                        if (editionNum != -1) System.out.println("5. Numero d'edition");
                        System.out.println("6. Genre");
                        System.out.println("7. date de parution (DD/MM/YYYY): ");
                        if (volNum != -1) System.out.println("8. Numero de Volume");
                        System.out.println("9. Quantite") ;
                        System.out.println("10. bonus/$: ");
                        System.out.println("11. Price: ");

                        int option = getOption(scanner) ;
                        scanner.nextLine() ;
                        int pos = -1;
                        if (option == 1) {
                            pos = inputs.indexOf(title);
                            title = getUserStrInfo(scanner, "Title");
                            success = true;
                            inputs.add(pos, title);
                        }
                        else if (option == 2) {
                            pos = inputs.indexOf(desc);
                            desc = getUserStrInfo(scanner, "Description");
                            success = true;
                            inputs.add(pos, desc);
                        }
                        else {
                            System.out.println("Option invalide");

                        }
                        //To do

                    }
                    success = false ;
                }

            }
        }



    }

    private static void getSellerRegistrationInfo(Scanner scanner) {
        List<Object> inputs = new ArrayList<>() ;
        System.out.println("Vous devez offrir au moins un produit à vendre au prealable") ;
        System.out.println("1. Offrir un produit à vendre:") ;
        if (getOption(scanner) == 1) {
            System.out.println("Choisissez une categorie de produit a vendre:") ;
            System.out.println("1. Livres et Manuels");
            System.out.println("2. Ressource d'apprentissage");
            System.out.println("3. Article de papeterie") ;
            System.out.println("4. Materiel informatique");
            System.out.println("5. Equipement de bureau") ;

            int option = getOption(scanner) ;
            scanner.nextLine() ;

            switch (option) {
                case 1:
                    getBookInfo(scanner);

                //To do
            }

        }
    }

    private static void getClientRegistrationInfo(Scanner scanner) {
        scanner = new Scanner(System.in);
        System.out.println("Saisissez vos informations") ;
        List<Object> inputs = new ArrayList<>() ;

        String firstName = getUserStrInfo(scanner,"Prenom") ;
        inputs.add(firstName) ;
        String lastName = getUserStrInfo(scanner,"Nom") ;
        inputs.add(lastName) ;
        String email = getUserStrInfo(scanner,"Email") ;
        inputs.add(email) ;
        String pseudo = getUserStrInfo(scanner, "pseudo") ;
        inputs.add(pseudo) ;
        long number = getUserNumInfo(scanner,"Numero") ;
        scanner.nextLine() ;
        inputs.add(number) ;
        String shipAddress = getUserStrInfo(scanner,"Adresse de livraison") ;
        inputs.add(shipAddress) ;

        boolean success = false ;
        while (!success) {

            System.out.println();
            printInfo(inputs);
            System.out.println();

            System.out.println("Confirmer vos informations: ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();

            if (getOption(scanner) == 1) {
                System.out.println("votre compte a ete cree avec succes");
                success = true ;
            }
            else {
                System.out.println("Voulez vous modifier vos informations:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (getOption(scanner) == 1) {
                    System.out.println("Choisir information a modifier: ");


                    while (!success) {
                        System.out.println("1. Prenom") ;
                        System.out.println("2. Nom");
                        System.out.println("3. Email");
                        System.out.println("4. Pseudo");
                        System.out.println("5. Number");

                        int option = getOption(scanner) ;
                        scanner.nextLine() ;

                        switch (option) {
                            case 1:
                                int pos = inputs.indexOf(firstName) ;
                                firstName = getUserStrInfo(scanner,"Prenom") ;
                                success = true ;
                                inputs.add(pos,firstName) ;
                                break;
                            case 2:
                                pos = inputs.indexOf(lastName) ;
                                lastName = getUserStrInfo(scanner,"Nom") ;
                                success = true ;
                                inputs.add(pos,lastName) ;
                                break;
                            case 3:
                                pos = inputs.indexOf(email) ;
                                email = getUserStrInfo(scanner,"Email") ;
                                success = true ;
                                inputs.add(pos,email) ;
                                break;
                            case 4:
                                pos = inputs.indexOf(pseudo) ;
                                pseudo = getUserStrInfo(scanner, "Pseudo") ;
                                success = true ;
                                inputs.add(pos,pseudo) ;
                                break;
                            case 5:
                                pos = inputs.indexOf(number) ;
                                number = getUserNumInfo(scanner,"Numero") ;
                                success = true ;
                                inputs.add(pos,number) ;
                                scanner.nextLine() ;
                                break;
                            default:
                                System.out.println("option doit etre compris entre 1 et 5");
                                break;
                        }
                    }
                    success = false ;
                }

            }
        }
    }

    private static int getOption(Scanner scanner) {
        int option = 0;
        boolean success = false ;
        while (!success) {
            try {
                option = scanner.nextInt() ;
                success = true ;
            } catch (InputMismatchException e) {
                System.err.println("Ooops! option doit etre un chiffre");
            }
        }
        return option ;
    }

    private static String getUserStrInfo(Scanner scanner, String info) {
        System.out.print(info+": ");
        String input = null;
        boolean success = false ;
        while (!success) {
            try {
                input = scanner.nextLine();
                success = true ;
            } catch (InputMismatchException e) {
                System.err.println("Ooops! "+info+" doit etre une chaine de caracteres");
            }
        }
        return input ;
    }

    private static long getUserNumInfo(Scanner scanner, String info) {
        System.out.print(info+": ");
        long input = 0;
        boolean success = false ;
        while (!success) {
            try {
                input = scanner.nextLong();
                success = true ;
            } catch (InputMismatchException e) {
                System.err.println("Ooops! "+info+" doit etre un nombre");
            }
        }
        return input ;
    }

    private static void printInfo(List<Object> inputs) {
        for (Object input : inputs) {
            System.out.println("- " + input);
        }
    }


    public static void run() {
        Scanner scanner = new Scanner(System.in) ;
        getSRegistrationStream(scanner) ;
    }

    public static void main(String[] args) {
        App.run();
    }
}
