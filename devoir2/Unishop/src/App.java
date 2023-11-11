import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {

    private static void getSRegistrationStream(Scanner scanner) {
        List<Object> inputs = new ArrayList<>() ;
        System.out.println("Choisissez une option d'inscription");
            System.out.println("1. Vendeur");
            System.out.println("2. Acheteur");
            if (getOption(scanner) == 2) {
                getUserRegistrationInfo(scanner, inputs);
            }
            else {
                System.out.println("Vous devez offrir au moins un produit à vendre au prealable") ;
                System.out.println("1. Offrir un produit à vendre:") ;
                if (getOption(scanner) == 1) {
                    inputs = new ArrayList<>() ;
                    getProductInfo(scanner,inputs);
                }
                inputs = new ArrayList<>() ;
                getUserRegistrationInfo(scanner,inputs);
            }
    }

    private static void getLearningResourceInfo(Scanner scanner, List<Object> inputs) {
        System.out.println("Donnez les informations du Livre/Manuel: ");
        String title = getUserStrInfo(scanner,"Titre") ;
        inputs.add(title) ;
        String desc = getUserStrInfo(scanner,"Description") ;
        inputs.add(desc) ;
        String author = getUserStrInfo(scanner,"Auteur") ;
        inputs.add(author) ;
        String isbn = getUserStrInfo(scanner,"ISBN") ;
        inputs.add(isbn) ;
        String org = getUserStrInfo(scanner,"Organisation") ;
        inputs.add(org) ;

        System.out.println("possede numero d'edition: ");
        System.out.println("1. oui");
        System.out.println("2. non");

        long editionNum = -1 ;
        if (getOption(scanner) == 1) {
            editionNum =  getUserNumInfo(scanner,"Numero d'edition") ;
            inputs.add(editionNum) ;
            scanner.nextLine() ;
        }

        String pubDate = getUserStrInfo(scanner,"date de parution (DD/MM/YYYY)") ;
        inputs.add(pubDate) ;

        System.out.println("Type: ");
        System.out.println("1. imprime");
        System.out.println("2. electronique");

        String type = null ;
        if(getOption(scanner) == 1) {
            type = "imprime" ;
            inputs.add(type) ;
        }
        else {
            type = "electronique" ;
            inputs.add(type) ;
        }
        scanner.nextLine() ;

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
                        System.out.println("4. Organisation");
                        System.out.println("5. Numero d'edition");
                        System.out.println("6. date de parution (DD/MM/YYYY): ");
                        System.out.println("7. Type");
                        System.out.println("8. Quantite") ;
                        System.out.println("9. bonus/$: ");
                        System.out.println("10. Price: ");
                        System.out.println("10. ISBN: ");

                        int option = getOption(scanner) ;
                        scanner.nextLine() ;
                        int pos = -1;
                        if (option == 1) {
                            pos = inputs.indexOf(title);
                            inputs.remove(title) ;
                            title = getUserStrInfo(scanner, "Title");
                            success = true;
                            inputs.add(pos, title);
                        }
                        else if (option == 2) {
                            pos = inputs.indexOf(desc);
                            inputs.remove(desc) ;
                            desc = getUserStrInfo(scanner, "Description");
                            success = true;
                            inputs.add(pos, desc);
                        }
                        else if (option == 3) {
                            pos = inputs.indexOf(author);
                            inputs.remove(author) ;
                            author = getUserStrInfo(scanner, "Auteur");
                            success = true;
                            inputs.add(pos, author);
                        }
                        else if (option == 4) {
                            pos = inputs.indexOf(org);
                            inputs.remove(org) ;
                            org = getUserStrInfo(scanner, "Organisation");
                            success = true;
                            inputs.add(pos, org);
                        }
                        else if (option == 5) {
                            pos = inputs.indexOf(editionNum);
                            inputs.remove(editionNum) ;
                            editionNum =  getUserNumInfo(scanner,"Numero d'edition") ;
                            if (pos != -1)  inputs.add(pos, editionNum);
                            else inputs.add(editionNum) ;
                            success = true ;
                            scanner.nextLine() ;
                        }
                        else if (option == 6) {
                            pos = inputs.indexOf(pubDate);
                            inputs.remove(pubDate) ;
                            pubDate = getUserStrInfo(scanner, "date de parution (DD/MM/YYYY)");
                            success = true;
                            inputs.add(pos, pubDate);
                        }
                        else if (option == 7) {
                            pos = inputs.indexOf(type);
                            inputs.remove(type) ;
                            System.out.println("Type: ");
                            System.out.println("1. imprime");
                            System.out.println("2. electronique");
                            if(getOption(scanner) == 1) {
                                type = "imprime" ;
                                inputs.add(pos,type) ;
                            }
                            else {
                                type = "electronique" ;
                                inputs.add(pos,type) ;
                            }
                            scanner.nextLine() ;
                            inputs.add(type) ;
                            success = true ;
                            scanner.nextLine() ;
                        }
                        else if (option == 8) {
                            pos = inputs.indexOf(initQuantity);
                            inputs.remove(initQuantity) ;
                            initQuantity =  getUserNumInfo(scanner,"Quantite") ;
                            if (pos != -1)  inputs.add(pos, initQuantity);
                            else inputs.add(initQuantity) ;
                            success = true ;
                            scanner.nextLine() ;
                        }
                        else if (option == 9) {
                            while (!success) {
                                points =  getUserNumInfo(scanner,"bonus/$") ;
                                if (points >= 20) {
                                    System.out.println("point bonus par $ ne peut pas depasser 20");
                                }
                                else success = true ;
                                pos = inputs.indexOf(points);
                                inputs.remove(points) ;
                                inputs.add(pos, points);
                                scanner.nextLine() ;
                            }
                        }
                        else if (option == 10) {
                            pos = inputs.indexOf(price);
                            inputs.remove(price) ;
                            price =  getUserNumInfo(scanner,"Prix") ;
                            inputs.add(pos, price);
                            success = true ;
                            scanner.nextLine() ;
                        }
                        else if (option == 11) {
                            pos = inputs.indexOf(isbn);
                            inputs.remove(isbn) ;
                            isbn = getUserStrInfo(scanner, "ISBN");
                            success = true;
                            inputs.add(pos, isbn);
                        }
                        else {
                            System.out.println("Option invalide");
                        }
                    }
                    success = false ;
                }

            }
        }
    }

    private static void getArticleInfo(Scanner scanner, List<Object> inputs) {
        //To do
    }

    private static void getMaterialInfo(Scanner scanner, List<Object> inputs) {
        //To do
    }

    private static void getEquipmentInfo(Scanner scanner, List<Object> inputs) {
        //To do
    }

    private static void getBookInfo(Scanner scanner, List<Object> inputs) {

        System.out.println("Donnez les informations du Livre/Manuel: ");
        String title = getUserStrInfo(scanner,"Titre") ;
        inputs.add(title) ;
        String desc = getUserStrInfo(scanner,"Description") ;
        inputs.add(desc) ;
        String author = getUserStrInfo(scanner,"Auteur") ;
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

        String genre = getUserStrInfo(scanner,"genre") ;
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

        success = false ;
        while (!success) {

            System.out.println();
            printInfo(inputs);
            System.out.println();

            System.out.println("Valider les informations du produit ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();

            if (getOption(scanner) == 1) {
                System.out.println("produit ajoutes avec succes");
                success = true ;
                System.exit(-1);
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
                        System.out.println("3. Auteur");
                        System.out.println("4. Maison d'edition");
                        System.out.println("5. Numero d'edition");
                        System.out.println("6. Genre");
                        System.out.println("7. date de parution (DD/MM/YYYY): ");
                        System.out.println("8. Numero de Volume");
                        System.out.println("9. Quantite") ;
                        System.out.println("10. bonus/$: ");
                        System.out.println("11. Price: ");
                        System.out.println("12. ISBN: ");

                        int option = getOption(scanner) ;
                        scanner.nextLine() ;
                        int pos = -1;
                        if (option == 1) {
                            pos = inputs.indexOf(title);
                            inputs.remove(title) ;
                            title = getUserStrInfo(scanner, "Title");
                            success = true;
                            inputs.add(pos, title);
                        }
                        else if (option == 2) {
                            pos = inputs.indexOf(desc);
                            inputs.remove(desc) ;
                            desc = getUserStrInfo(scanner, "Description");
                            success = true;
                            inputs.add(pos, desc);
                        }
                        else if (option == 3) {
                            pos = inputs.indexOf(author);
                            inputs.remove(author) ;
                            author = getUserStrInfo(scanner, "Auteur");
                            success = true;
                            inputs.add(pos, author);
                        }
                        else if (option == 4) {
                            pos = inputs.indexOf(editor);
                            inputs.remove(editor) ;
                            editor = getUserStrInfo(scanner, "Maison d'edition");
                            success = true;
                            inputs.add(pos, editor);
                        }
                        else if (option == 5) {
                            pos = inputs.indexOf(editionNum);
                            inputs.remove(editionNum) ;
                            editionNum =  getUserNumInfo(scanner,"Numero d'edition") ;
                            if (pos != -1)  inputs.add(pos, editionNum);
                            else inputs.add(editionNum) ;
                            success = true ;
                            scanner.nextLine() ;
                        }
                        else if (option == 6) {
                            pos = inputs.indexOf(genre);
                            inputs.remove(genre) ;
                            genre = getUserStrInfo(scanner, "Genre");
                            success = true;
                            inputs.add(pos, genre);
                        }
                        else if (option == 7) {
                            pos = inputs.indexOf(pubDate);
                            inputs.remove(pubDate) ;
                            pubDate = getUserStrInfo(scanner, "date de parution (DD/MM/YYYY)");
                            success = true;
                            inputs.add(pos, pubDate);
                        }
                        else if (option == 8) {
                            pos = inputs.indexOf(volNum);
                            inputs.remove(volNum) ;
                            volNum =  getUserNumInfo(scanner,"Numero de volume") ;
                            if (pos != -1)  inputs.add(pos, volNum);
                            else inputs.add(volNum) ;
                            success = true ;
                            scanner.nextLine() ;
                        }
                        else if (option == 9) {
                            pos = inputs.indexOf(initQuantity);
                            inputs.remove(initQuantity) ;
                            initQuantity =  getUserNumInfo(scanner,"Quantite") ;
                            if (pos != -1)  inputs.add(pos, initQuantity);
                            else inputs.add(initQuantity) ;
                            success = true ;
                            scanner.nextLine() ;
                        }
                        else if (option == 10) {
                            pos = inputs.indexOf(points);
                            inputs.remove(points) ;
                            while (!success) {
                                points =  getUserNumInfo(scanner,"bonus/$") ;
                                if (points >= 20) {
                                    System.out.println("point bonus par $ ne peut pas depasser 20");
                                }
                                else success = true ;
                                inputs.add(pos, points);
                                scanner.nextLine() ;
                            }
                        }
                        else if (option == 11) {
                            pos = inputs.indexOf(price);
                            inputs.remove(price) ;
                            price =  getUserNumInfo(scanner,"Prix") ;
                            inputs.add(pos, price);
                            success = true ;
                            scanner.nextLine() ;
                        }
                        else if (option == 12) {
                            pos = inputs.indexOf(isbn);
                            inputs.remove(isbn) ;
                            isbn = getUserStrInfo(scanner, "ISBN");
                            success = true;
                            inputs.add(pos, isbn);
                        }
                        else {
                            System.out.println("Option invalide");
                        }
                    }
                    success = false ;
                }

            }
        }



    }

    private static void getProductInfo(Scanner scanner, List<Object> inputs) {
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
                inputs = new ArrayList<>() ;
                getBookInfo(scanner,inputs);
            case 2:
                inputs = new ArrayList<>() ;
                getLearningResourceInfo(scanner,inputs);
            case 3:
                inputs = new ArrayList<>() ;
                getArticleInfo(scanner,inputs);
            case 4:
                inputs = new ArrayList<>() ;
                getMaterialInfo(scanner,inputs);
            case 5:
                inputs = new ArrayList<>() ;
                getEquipmentInfo(scanner,inputs);

        }
    }

    private static void getUserRegistrationInfo(Scanner scanner, List<Object> inputs) {
        scanner = new Scanner(System.in);
        System.out.println("Saisissez vos informations") ;

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
                                inputs.remove(firstName) ;
                                firstName = getUserStrInfo(scanner,"Prenom") ;
                                success = true ;
                                inputs.add(pos,firstName) ;
                                break;
                            case 2:
                                pos = inputs.indexOf(lastName) ;
                                inputs.remove(lastName) ;
                                lastName = getUserStrInfo(scanner,"Nom") ;
                                success = true ;
                                inputs.add(pos,lastName) ;
                                break;
                            case 3:
                                pos = inputs.indexOf(email) ;
                                inputs.remove(email) ;
                                email = getUserStrInfo(scanner,"Email") ;
                                success = true ;
                                inputs.add(pos,email) ;
                                break;
                            case 4:
                                pos = inputs.indexOf(pseudo) ;
                                inputs.remove(pseudo) ;
                                pseudo = getUserStrInfo(scanner, "Pseudo") ;
                                success = true ;
                                inputs.add(pos,pseudo) ;
                                break;
                            case 5:
                                pos = inputs.indexOf(number) ;
                                inputs.remove(number) ;
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

    private static void getClientServiceInfo(Scanner scanner) {
        //To do
    }
    private static void getSellerServiceInfo(Scanner scanner) {
        List<Object> inputs = new ArrayList<>() ;
         System.out.println("Selectionner la tache que voulez effectuer: ");
        System.out.println("1. Offrir un produit: ") ;
        System.out.println("2. Changer l'etat d'une commande: ");
        System.out.println("3. Afficher les metriques de mes activites: ");
        System.out.println("4. Offrir une promotion sur un produit: ");

        int option = getOption(scanner) ;
        switch (option) {
            case 1:
                inputs = new ArrayList<>() ;
                getProductInfo(scanner,inputs);
        }
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in) ;
        getSRegistrationStream(scanner) ;
        System.out.println("##########################");


    }

}
