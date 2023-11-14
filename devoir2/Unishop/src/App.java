import Data.Entities.Products.*;
import Data.Entities.Type;
import Data.Entities.Users.Client;
import Data.Entities.Users.Seller;
import Data.Entities.Users.User;

import java.util.*;

public class App {

    private static User getRegistrationStream(Scanner scanner) {
        List<Object> inputs = new ArrayList<>() ;

        User user = null;
        System.out.println("Choisissez une option d'inscription");
        System.out.println("1. Vendeur");
        System.out.println("2. Acheteur");
        if (getOption(scanner) == 2) {
            user = getClientRegistrationInfo(scanner, inputs);
        }
        else {
            inputs = new ArrayList<>() ;
            user = getSellerRegistrationInfo(scanner,inputs);
        }
        return user ;
    }

    private static void login(Scanner scanner, List<User> users) {
        while (true) {
            String pseudo = getUserStrInfo(scanner,"Pseudo") ;
            for (User user : users) {
                if (user.getPseudo().equals(pseudo)) {
                    System.out.println("Connexion avec succes !!!");
                    return ;
                }
            }
            System.out.println("pseudo invalide");
        }
    }

    private static LearningResource getLearningResourceInfo(Scanner scanner, List<Object> inputs) {
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
            editionNum =  getNumInfo(scanner,"Numero d'edition") ;
            inputs.add(editionNum) ;
            scanner.nextLine() ;
        }

        String pubDate = getUserStrInfo(scanner,"date de parution (DD/MM/YYYY)") ;
        inputs.add(pubDate) ;

        System.out.println("Type: ");
        System.out.println("1. imprime");
        System.out.println("2. electronique");

        Type type = null ;
        if(getOption(scanner) == 1) {
            type = Type.printed ;
            inputs.add(type) ;
        }
        else {
            type = Type.electronic ;
            inputs.add(type) ;
        }
        scanner.nextLine() ;

        long initQuantity = getNumInfo(scanner,"Quantite") ;
        inputs.add(initQuantity) ;

        System.out.println("offrir des points bonus pour le produit:") ;
        System.out.println("1. oui");
        System.out.println("2. non");

        boolean success = false ;
        long points = 1 ;

        if(getOption(scanner) == 1) {

            while (!success) {
                points = getNumInfo(scanner,"bonus/$") ;
                if (points >= 20) {
                    System.out.println("point bonus par $ ne peut pas depasser 20");
                }
                else success = true ;
            }

        }
        inputs.add(points) ;

        double price = getNumInfo(scanner,"Prix") ;
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
                            editionNum =  getNumInfo(scanner,"Numero d'edition") ;
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
                                type = Type.printed ;
                                inputs.add(pos,type) ;
                            }
                            else {
                                type = Type.electronic;
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
                            initQuantity =  getNumInfo(scanner,"Quantite") ;
                            if (pos != -1)  inputs.add(pos, initQuantity);
                            else inputs.add(initQuantity) ;
                            success = true ;
                            scanner.nextLine() ;
                        }
                        else if (option == 9) {
                            while (!success) {
                                points =  getNumInfo(scanner,"bonus/$") ;
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
                            price =  getNumInfo(scanner,"Prix") ;
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
        return new LearningResource(title,desc,"Ressource d'apprentissage",
                Calendar.getInstance().getTime().toString(),
                initQuantity,price,points,isbn,author,org,pubDate,type,editionNum) ;
    }

    private static Product getArticleInfo(Scanner scanner, List<Object> inputs) {

        System.out.println("Donnez les informations de l'Article: ");
        String title = getUserStrInfo(scanner,"Titre") ;
        inputs.add(title) ;
        String desc = getUserStrInfo(scanner,"Description") ;
        inputs.add(desc) ;
        String brand = getUserStrInfo(scanner,"Marque") ;
        inputs.add(brand) ;
        String model = getUserStrInfo(scanner,"Model") ;
        inputs.add(model) ;
        String subCategory = getUserStrInfo(scanner,"Sous-catégorie exemple: cahier, crayon, surligneur:") ;
        inputs.add(subCategory) ;

        long initQuantity = getNumInfo(scanner,"Quantite") ;
        inputs.add(initQuantity) ;

        System.out.println("offrir des points bonus pour le produit:") ;
        System.out.println("1. oui");
        System.out.println("2. non");

        boolean success = false ;
        long points = 1 ;
        if(getOption(scanner) == 1) {

            while (!success) {
                points = getNumInfo(scanner,"bonus/$") ;
                if (points >= 20) {
                    System.out.println("point bonus par $ ne peut pas depasser 20");
                }
                else success = true ;
            }

        }
        inputs.add(points) ;

        double price = getNumInfo(scanner,"Prix") ;
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
                        System.out.println("3. Marque");
                        System.out.println("4. Model");
                        System.out.println("5. sous-categorie");
                        System.out.println("6. Quantite") ;
                        System.out.println("7. bonus/$: ");
                        System.out.println("8. Price: ");

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
                            pos = inputs.indexOf(brand);
                            inputs.remove(brand) ;
                            brand = getUserStrInfo(scanner, "Marque");
                            success = true;
                            inputs.add(pos, brand);
                        }
                        else if (option == 4) {
                            pos = inputs.indexOf(model);
                            inputs.remove(model) ;
                            model = getUserStrInfo(scanner, "Modèle");
                            success = true;
                            inputs.add(pos, model);
                        }
                        else if (option == 5) {
                            pos = inputs.indexOf(subCategory);
                            inputs.remove(subCategory) ;
                            subCategory = getUserStrInfo(scanner, "sous-categorie");
                            success = true;
                            inputs.add(pos, subCategory);
                        }
                        else if (option == 6) {
                            pos = inputs.indexOf(initQuantity);
                            inputs.remove(initQuantity) ;
                            initQuantity =  getNumInfo(scanner,"Quantite") ;
                            if (pos != -1)  inputs.add(pos, initQuantity);
                            else inputs.add(initQuantity) ;
                            success = true ;
                            scanner.nextLine() ;
                        }
                        else if (option == 7) {
                            pos = inputs.indexOf(points);
                            inputs.remove(points) ;
                            while (!success) {
                                points =  getNumInfo(scanner,"bonus/$") ;
                                if (points >= 20) {
                                    System.out.println("point bonus par $ ne peut pas depasser 20");
                                }
                                else success = true ;
                                inputs.add(pos, points);
                                scanner.nextLine() ;
                            }
                        }
                        else if (option == 8) {
                            pos = inputs.indexOf(price);
                            inputs.remove(price) ;
                            price =  getNumInfo(scanner,"Prix") ;
                            inputs.add(pos, price);
                            success = true ;
                            scanner.nextLine() ;
                        }
                        else {
                            System.out.println("Option invalide");
                        }

                    }
                }

            }
            success = true ;
        }

        return new Article(title,desc,"Article",
                Calendar.getInstance().getTime().toString(),
                initQuantity,price,points,brand,model,subCategory) ;

    }

        private static Product getMaterialInfo(Scanner scanner, List<Object> inputs) {
        System.out.println("Donnez les informations du materiel: ");
        String title = getUserStrInfo(scanner,"Titre") ;
        inputs.add(title) ;
        String desc = getUserStrInfo(scanner,"Description") ;
        inputs.add(desc) ;
        String brand = getUserStrInfo(scanner,"Marque") ;
        inputs.add(brand) ;
        String model = getUserStrInfo(scanner,"Model") ;
        inputs.add(model) ;
        String subCategory = getUserStrInfo(scanner,"Sous-catégorie exemple: ordinateur, souris, clavier, disque dur externe") ;
        inputs.add(subCategory) ;
        String launchDate = getUserStrInfo(scanner,"date de lancement (DD/MM/YYYY)") ;
        inputs.add(launchDate) ;

        long initQuantity = getNumInfo(scanner,"Quantite") ;
        inputs.add(initQuantity) ;

        System.out.println("offrir des points bonus pour le produit:") ;
        System.out.println("1. oui");
        System.out.println("2. non");

        boolean success = false ;
        long points = 1 ;
        if(getOption(scanner) == 1) {

            while (!success) {
                points = getNumInfo(scanner,"bonus/$") ;
                if (points >= 20) {
                    System.out.println("point bonus par $ ne peut pas depasser 20");
                }
                else success = true ;
            }

        }
        inputs.add(points) ;

        double price = getNumInfo(scanner,"Prix") ;
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
                        System.out.println("3. Marque");
                        System.out.println("4. Model");
                        System.out.println("5. sous-categorie");
                        System.out.println("6. Date de lancement");
                        System.out.println("7. Quantite") ;
                        System.out.println("8. bonus/$: ");
                        System.out.println("9. Price: ");

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
                            pos = inputs.indexOf(brand);
                            inputs.remove(brand) ;
                            brand = getUserStrInfo(scanner, "Marque");
                            success = true;
                            inputs.add(pos, brand);
                        }
                        else if (option == 4) {
                            pos = inputs.indexOf(model);
                            inputs.remove(model) ;
                            model = getUserStrInfo(scanner, "Modèle");
                            success = true;
                            inputs.add(pos, model);
                        }
                        else if (option == 5) {
                            pos = inputs.indexOf(subCategory);
                            inputs.remove(subCategory) ;
                            subCategory = getUserStrInfo(scanner, "sous-categorie");
                            success = true;
                            inputs.add(pos, subCategory);
                        }
                        else if (option == 6) {
                            pos = inputs.indexOf(launchDate);
                            inputs.remove(launchDate) ;
                            launchDate = getUserStrInfo(scanner, "Date de lancement");
                            success = true;
                            inputs.add(pos, launchDate);
                        }

                        else if (option == 7) {
                            pos = inputs.indexOf(initQuantity);
                            inputs.remove(initQuantity) ;
                            initQuantity =  getNumInfo(scanner,"Quantite") ;
                            if (pos != -1)  inputs.add(pos, initQuantity);
                            else inputs.add(initQuantity) ;
                            success = true ;
                            scanner.nextLine() ;
                        }
                        else if (option == 8) {
                            pos = inputs.indexOf(points);
                            inputs.remove(points) ;
                            while (!success) {
                                points =  getNumInfo(scanner,"bonus/$") ;
                                if (points >= 20) {
                                    System.out.println("point bonus par $ ne peut pas depasser 20");
                                }
                                else success = true ;
                                inputs.add(pos, points);
                                scanner.nextLine() ;
                            }
                        }
                        else if (option == 9) {
                            pos = inputs.indexOf(price);
                            inputs.remove(price) ;
                            price =  getNumInfo(scanner,"Prix") ;
                            inputs.add(pos, price);
                            success = true ;
                            scanner.nextLine() ;
                        }
                        else {
                            System.out.println("Option invalide");
                        }

                    }
                }

            }
            success = true ;
        }

        return new Hardware(title,desc,"Matériel informatique",
                Calendar.getInstance().getTime().toString(),
                initQuantity,price,points,brand,model,launchDate,subCategory);
    }

    private static Product getEquipmentInfo(Scanner scanner, List<Object> inputs) {

        System.out.println("Donnez les informations de l'equipement");
        String title = getUserStrInfo(scanner,"Titre") ;
        inputs.add(title) ;
        String desc = getUserStrInfo(scanner,"Description") ;
        inputs.add(desc) ;
        String brand = getUserStrInfo(scanner,"Marque") ;
        inputs.add(brand) ;
        String model = getUserStrInfo(scanner,"Model") ;
        inputs.add(model) ;
        String subCategory = getUserStrInfo(scanner,"Sous-catégorie exemple: table, chaise, lampe:") ;
        inputs.add(subCategory) ;

        long initQuantity = getNumInfo(scanner,"Quantite") ;
        inputs.add(initQuantity) ;

        System.out.println("offrir des points bonus pour le produit:") ;
        System.out.println("1. oui");
        System.out.println("2. non");

        boolean success = false ;
        long points = 1 ;
        if(getOption(scanner) == 1) {

            while (!success) {
                points = getNumInfo(scanner,"bonus/$") ;
                if (points >= 20) {
                    System.out.println("point bonus par $ ne peut pas depasser 20");
                }
                else success = true ;
            }

        }
        inputs.add(points) ;

        double price = getNumInfo(scanner,"Prix") ;
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
                        System.out.println("3. Marque");
                        System.out.println("4. Model");
                        System.out.println("5. sous-categorie");
                        System.out.println("5. Quantite") ;
                        System.out.println("6. bonus/$: ");
                        System.out.println("7. Price: ");

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
                            pos = inputs.indexOf(brand);
                            inputs.remove(brand) ;
                            brand = getUserStrInfo(scanner, "Marque");
                            success = true;
                            inputs.add(pos, brand);
                        }
                        else if (option == 4) {
                            pos = inputs.indexOf(model);
                            inputs.remove(model) ;
                            model = getUserStrInfo(scanner, "Modèle");
                            success = true;
                            inputs.add(pos, model);
                        }
                        else if (option == 5) {
                            pos = inputs.indexOf(subCategory);
                            inputs.remove(subCategory) ;
                            subCategory = getUserStrInfo(scanner, "sous-categorie");
                            success = true;
                            inputs.add(pos, subCategory);
                        }
                        else if (option == 6) {
                            pos = inputs.indexOf(initQuantity);
                            inputs.remove(initQuantity) ;
                            initQuantity =  getNumInfo(scanner,"Quantite") ;
                            if (pos != -1)  inputs.add(pos, initQuantity);
                            else inputs.add(initQuantity) ;
                            success = true ;
                            scanner.nextLine() ;
                        }
                        else if (option == 7) {
                            pos = inputs.indexOf(points);
                            inputs.remove(points) ;
                            while (!success) {
                                points =  getNumInfo(scanner,"bonus/$") ;
                                if (points >= 20) {
                                    System.out.println("point bonus par $ ne peut pas depasser 20");
                                }
                                else success = true ;
                                inputs.add(pos, points);
                                scanner.nextLine() ;
                            }
                        }
                        else if (option == 8) {
                            pos = inputs.indexOf(price);
                            inputs.remove(price) ;
                            price =  getNumInfo(scanner,"Prix") ;
                            inputs.add(pos, price);
                            success = true ;
                            scanner.nextLine() ;
                        }
                        else {
                            System.out.println("Option invalide");
                        }

                    }
                }

            }
            success = true ;
        }

        return new DesktopTool(title,desc,"Article",
                Calendar.getInstance().getTime().toString(),
                initQuantity,price,points,brand,model,subCategory) ;
    }

    private static Product getBookInfo(Scanner scanner, List<Object> inputs) {

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
            editionNum =  getNumInfo(scanner,"Numero d'edition") ;
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
            volNum = getNumInfo(scanner,"Numero de volume") ;
            inputs.add(volNum) ;
            scanner.nextLine() ;
        }

        long initQuantity = getNumInfo(scanner,"Quantite") ;
        inputs.add(initQuantity) ;

        System.out.println("offrir des points bonus pour le produit:") ;
        System.out.println("1. oui");
        System.out.println("2. non");

        boolean success = false ;
        long points = 1 ;
        if(getOption(scanner) == 1) {

            while (!success) {
                points = getNumInfo(scanner,"bonus/$") ;
                if (points >= 20) {
                    System.out.println("point bonus par $ ne peut pas depasser 20");
                }
                else success = true ;
            }

        }
        inputs.add(points) ;

        double price = getNumInfo(scanner,"Prix") ;
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
                            editionNum =  getNumInfo(scanner,"Numero d'edition") ;
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
                            volNum =  getNumInfo(scanner,"Numero de volume") ;
                            if (pos != -1)  inputs.add(pos, volNum);
                            else inputs.add(volNum) ;
                            success = true ;
                            scanner.nextLine() ;
                        }
                        else if (option == 9) {
                            pos = inputs.indexOf(initQuantity);
                            inputs.remove(initQuantity) ;
                            initQuantity =  getNumInfo(scanner,"Quantite") ;
                            if (pos != -1)  inputs.add(pos, initQuantity);
                            else inputs.add(initQuantity) ;
                            success = true ;
                            scanner.nextLine() ;
                        }
                        else if (option == 10) {
                            pos = inputs.indexOf(points);
                            inputs.remove(points) ;
                            while (!success) {
                                points =  getNumInfo(scanner,"bonus/$") ;
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
                            price =  getNumInfo(scanner,"Prix") ;
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
                }

            }
            success = true ;
        }

        return new Book(title,desc,"Livre ou Manuel",
                Calendar.getInstance().getTime().toString(),
                initQuantity,price,points,isbn,author,editor,genre,pubDate,editionNum,volNum) ;

    }

    private static Product getProductInfo(Scanner scanner, List<Object> inputs) {
        System.out.println("Choisissez une categorie de produit a vendre:") ;
        System.out.println("1. Livres et Manuels");
        System.out.println("2. Ressource d'apprentissage");
        System.out.println("3. Article de papeterie") ;
        System.out.println("4. Materiel informatique");
        System.out.println("5. Equipement de bureau") ;
        Product product = null;

        int option = getOption(scanner) ;
        scanner.nextLine() ;

        switch (option) {
            case 1:
                inputs = new ArrayList<>() ;
                product = getBookInfo(scanner,inputs);
                break ;
            case 2:
                inputs = new ArrayList<>() ;
                product = getLearningResourceInfo(scanner,inputs);
                break;
            case 3:
                inputs = new ArrayList<>() ;
                product = getArticleInfo(scanner,inputs);
                break;
            case 4:
                inputs = new ArrayList<>() ;
                product = getMaterialInfo(scanner,inputs);
                break;
            case 5:
                inputs = new ArrayList<>() ;
                product = getEquipmentInfo(scanner,inputs);
                break;
        }
        return product ;
    }

    private static Seller getSellerRegistrationInfo(Scanner scanner, List<Object> inputs) {
        System.out.println("Vous devez offrir au moins un produit à vendre au prealable") ;
        System.out.println("1. Offrir un produit à vendre:") ;
        Product product = null ;

        if (getOption(scanner) == 1) {
            inputs = new ArrayList<>() ;
            product = getProductInfo(scanner,inputs);
        }

        System.out.println("Saisissez vos informations") ;

        String firstName = getUserStrInfo(scanner,"Prenom") ;
        inputs.add(firstName) ;
        String lastName = getUserStrInfo(scanner,"Nom") ;
        inputs.add(lastName) ;
        String email = getUserStrInfo(scanner,"Email") ;
        inputs.add(email) ;
        String pseudo = getUserStrInfo(scanner, "pseudo") ;
        inputs.add(pseudo) ;
        long number = getNumInfo(scanner,"Numero") ;
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
                                number = getNumInfo(scanner,"Numero") ;
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
        ArrayList<Product> products = new ArrayList<Product>() ;
        products.add(product) ;
        return new Seller(firstName,lastName,email,pseudo,number, products) ;
    }

    private static Client getClientRegistrationInfo(Scanner scanner, List<Object> inputs) {
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
        long number = getNumInfo(scanner,"Numero") ;
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
                                number = getNumInfo(scanner,"Numero") ;
                                success = true ;
                                inputs.add(pos,number) ;
                                scanner.nextLine() ;
                                break;
                            default:
                                System.out.println("option doit etre compris entre 1 et 5");
                                break;
                        }
                        success = false ;
                    }
                }
            }
            success = true ;
        }
        return new Client(firstName,lastName,email,pseudo,number,shipAddress) ;
    }

    private static int getOption(Scanner scanner) {
        int option = 0;
        boolean success = false ;
        while (!success) {
            try {
                option = scanner.nextInt();
                success = true ;
            } catch (InputMismatchException e) {
                System.err.println("Ooops! option doit etre un chiffre");
                scanner.next();
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

    //To fix
    private static long getNumInfo(Scanner scanner, String info) {
        System.out.print(info+": ");
        long input = 0;
        boolean success = true ;
            try {
                    input = scanner.nextLong();
                    success = false;

            } catch (InputMismatchException e) {
                System.err.println("Ooops! "+info+" doit etre un nombre");
                scanner.next();
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
        List<Object> inputs = new ArrayList<>() ;
        System.out.println("Selectionner la tache que voulez effectuer: ");
        System.out.println("1. Chercher un produit: ") ;




    }
    private static void getSellerServiceInfo(Scanner scanner) {
        List<Object> inputs = new ArrayList<>() ;
        System.out.println("Selectionner la tache que voulez effectuer: ");
        System.out.println("1. Offrir un produit: ") ;
        System.out.println("2. Changer l'etat d'une commande: ");

        int option = getOption(scanner) ;
        switch (option) {
            case 1:
                inputs = new ArrayList<>() ;
                getProductInfo(scanner,inputs);
                break ;
            case 2 :
                inputs = new ArrayList<>() ;
                //getSearchInfo(scanner,inputs);
        }
    }

    private static void searchProduct(Scanner scanner , List<Object> inputs) {

    }



    public static <Users> void run() {
        Scanner scanner = new Scanner(System.in) ;

        Client client1 = new Client("sidya","galakho",
                "sidya.galakho@gmail.ca","rango",
                4385273906L,"9545 Rue Lajeunesse");
        Client client2 = new Client("John", "Doe"
                , "john.doe@gmail.com", "password123", 1234567890L, "123 Main Street");
        System.out.println(client2.getFirstName());
        Client client3 = new Client("Alice", "Smith",
                "alice.smith@outlook.com", "myPass123",
                9876543210L, "456 Elm Street");
        Client client4 = new Client("Michael", "Johnson",
                "michael.johnson@gmail.com", "securePwd",
                5551112222L, "789 Oak Avenue");
        Client client5 = new Client("Emily", "Davis",
                "emily.davis@hotmail.com",
                "p@ssw0rd", 3334445555L, "101 Pine Street");

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(1951, Calendar.JULY, 16);
        Book book1 = new Book(
                "The Catcher in the Rye",
                "A novel about teenage angst and rebellion.",
                "Fiction",
                Calendar.getInstance().getTime().toString(),
                5,
                15.99,
                4,
                "0316769487",
                "J.D. Salinger",
                "Little, Brown and Company",
                "Coming-of-age",
                calendar1.getTime().toString(),
                1,
                1
        );

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(1960, Calendar.JULY, 11);
        Book book2 = new Book(
                "To Kill a Mockingbird",
                "A story that addresses issues of racial injustice and moral growth.",
                "Fiction",
                Calendar.getInstance().getTime().toString(),
                8,
                12.49,
                1,
                "0061120081",
                "Harper Lee",
                "J.B. Lippincott & Co.",
                "Southern Gothic",
                calendar2.getTime().toString(),
                1,
                1
        );

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2023, Calendar.JANUARY, 1);
        LearningResource resource = new LearningResource(
                "Introduction to Java Programming",
                "A comprehensive guide to Java programming language.",
                "Programming",
                Calendar.getInstance().getTime().toString(),
                50,
                29.99,
                2,
                "9780135166307",
                "John Doe",
                "Java University",
                calendar3.getTime().toString(),
                Type.printed,
                1L
        );


        Article article = new Article(
                "The Benefits of Regular Exercise",
                "Exploring the positive effects of regular physical activity on health.",
                "Health & Wellness",
                Calendar.getInstance().getTime().toString(),
                30,
                9.99,
                50,
                "Fitness",
                "HealthCo",
                "Exercise101"
        );

        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(2022, Calendar.FEBRUARY, 1);
        Hardware hardware = new Hardware(
                "Gaming Laptop",
                "Powerful laptop designed for gaming enthusiasts.",
                "Computers & Electronics",
                Calendar.getInstance().getTime().toString(),
                15,
                1499.99,
                18,
                "Lenovo",
                "TechCorp",
                calendar4.getTime().toString(),
                "Laptops"
        );

        DesktopTool desktopTool = new DesktopTool(
                "Electric Screwdriver Set",
                "A set of electric screwdrivers for various household tasks.",
                "Power Tools",
                "2023-05-10", // Assuming date format as a String
                25,
                79.99,
                40,
                "ToolCo",
                "ScrewMaster 2000",
                "Power Tools"
        );



        ArrayList<Product> products1 = new ArrayList<>() ;
        products1.add(book1) ;
        products1.add(resource) ;
        Seller seller1 = new Seller(
                "Alice", "Johnson", "alice.johnson@example.com",
                "password12", 1234167890L, products1);

        ArrayList<Product> products2 = new ArrayList<>() ;
        products2.add(book2) ;
        Seller seller2 = new Seller(
                "David", "Smith", "david.smith@gmail.com",
                "myPass12", 9876843210L, products2);

        ArrayList<Product> products3 = new ArrayList<>() ;
        products3.add(hardware) ;
        Seller seller3 = new Seller(
                "Emma", "Brown", "emma.brown@yahoo.com",
                "securePw", 5551172222L, products3);

        ArrayList<Product> products4 = new ArrayList<>() ;
        products4.add(article) ;
        Seller seller4 = new Seller(
                "Michael", "Garcia", "michael.garcia@hotmail.com",
                "p@ssw0r", 3334443555L, products4);

        ArrayList<Product> products5 = new ArrayList<>() ;
        products5.add(desktopTool) ;
        Seller seller5 = new Seller(
                "Sophia", "Lee", "sophia.lee@outlook.com",
                "secretPas", 1112223733L, products5);

        List<Client> clients = new ArrayList<>(Arrays.asList(client1, client2, client3, client4, client5));
        List<Seller> sellers = new ArrayList<>(Arrays.asList(seller1, seller2, seller3, seller4, seller5));
        List<Client> clients2=new ArrayList<>();
        clients2.add(client2);


        /*
        User user = null ;

        System.out.println("Choisissez une option");
        System.out.println("1. Se connecter");
        System.out.println("2. S'inscrire");

        if (getOption(scanner) == 1) {
            scanner.nextLine() ;
            login(scanner, (List<User>) users) ;
        }
        else {
            user = getRegistrationStream(scanner) ;
        }
        if (user instanceof Seller) System.out.println(((Seller) user).getProducts());
        System.out.println("##########################");

         */

        for (Client client: clients) {
            System.out.println(client);
        }

        for (Seller seller: sellers) {
            System.out.println(seller);
        }
        for(Client client:clients2){
            System.out.println(client);
        }
        System.out.println(client2);
        System.out.println(client2.getFirstName());

    }

}
