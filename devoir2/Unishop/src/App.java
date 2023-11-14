import Data.Entities.Products.*;
import Data.Entities.ShoppingCart;
import Data.Entities.Type;
import Data.Entities.Users.Client;
import Data.Entities.Users.Seller;
import Data.Entities.Users.User;

import java.util.*;

public class App {
    public static Scanner scanner = new Scanner(System.in);

    private static User getRegistrationStream() {
        System.out.println("Choisissez une option d'inscription");
        System.out.println("1. Vendeur");
        System.out.println("2. Acheteur");
        if (getOption() == 2) {
            //create a client
            return getClientRegistrationInfo();
        }
        //define a seller
        return getSellerRegistrationInfo();
    }

    private static LearningResource getLearningResourceInfo() {
        ArrayList<Object> inputs = new ArrayList<>();
        System.out.println("Donnez les informations du Livre/Manuel: ");
        String title = getUserStrInfo("Titre");
        inputs.add(title);
        String desc = getUserStrInfo("Description");
        inputs.add(desc);
        String author = getUserStrInfo("Auteur");
        inputs.add(author);
        String isbn = getUserStrInfo("ISBN");
        inputs.add(isbn);
        String org = getUserStrInfo("Organisation");
        inputs.add(org);

        System.out.println("possede numero d'edition: ");
        System.out.println("1. oui");
        System.out.println("2. non");

        long editionNum = -1;
        if (getOption() == 1) {
            editionNum = getUserNumInfo("Numero d'edition");
            inputs.add(editionNum);
            scanner.nextLine();
        }

        String pubDate = getUserStrInfo("date de parution (DD/MM/YYYY)");
        inputs.add(pubDate);

        System.out.println("Type: ");
        System.out.println("1. imprime");
        System.out.println("2. electronique");

        Type type = null;
        if (getOption() == 1) {
            type = Type.printed;
            inputs.add(type);
        } else {
            type = Type.electronic;
            inputs.add(type);
        }
        scanner.nextLine();

        long initQuantity = getUserNumInfo("Quantite");
        inputs.add(initQuantity);

        System.out.println("offrir des points bonus pour le produit:");
        System.out.println("1. oui");
        System.out.println("2. non");

        boolean success = false;
        long points = 1;

        if (getOption() == 1) {

            while (!success) {
                points = getUserNumInfo("bonus/$");
                if (points >= 20) {
                    System.out.println("point bonus par $ ne peut pas depasser 20");
                } else success = true;
            }

        }
        inputs.add(points);

        double price = getUserNumInfo("Prix");
        inputs.add(price);

        System.out.println();
        printInfo(inputs);
        System.out.println();

        success = false;
        while (!success) {

            System.out.println();
            printInfo(inputs);
            System.out.println();

            System.out.println("Valider les informations: du produit ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();

            if (getOption() == 1) {
                System.out.println("produit ajoutes avec succes");
                success = true;
            } else {
                System.out.println("Voulez vous modifier les informations du produit:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (getOption() == 1) {
                    System.out.println("Choisir information a modifier: ");

                    while (!success) {
                        System.out.println("1. Titre");
                        System.out.println("2. Description");
                        System.out.println("3. Autheur");
                        System.out.println("4. Organisation");
                        System.out.println("5. Numero d'edition");
                        System.out.println("6. date de parution (DD/MM/YYYY): ");
                        System.out.println("7. Type");
                        System.out.println("8. Quantite");
                        System.out.println("9. bonus/$: ");
                        System.out.println("10. Price: ");
                        System.out.println("10. ISBN: ");

                        int option = getOption();
                        scanner.nextLine();
                        int pos = -1;
                        if (option == 1) {
                            pos = inputs.indexOf(title);
                            inputs.remove(title);
                            title = getUserStrInfo("Title");
                            success = true;
                            inputs.add(pos, title);
                        } else if (option == 2) {
                            pos = inputs.indexOf(desc);
                            inputs.remove(desc);
                            desc = getUserStrInfo("Description");
                            success = true;
                            inputs.add(pos, desc);
                        } else if (option == 3) {
                            pos = inputs.indexOf(author);
                            inputs.remove(author);
                            author = getUserStrInfo("Auteur");
                            success = true;
                            inputs.add(pos, author);
                        } else if (option == 4) {
                            pos = inputs.indexOf(org);
                            inputs.remove(org);
                            org = getUserStrInfo("Organisation");
                            success = true;
                            inputs.add(pos, org);
                        } else if (option == 5) {
                            pos = inputs.indexOf(editionNum);
                            inputs.remove(editionNum);
                            editionNum = getUserNumInfo("Numero d'edition");
                            if (pos != -1) inputs.add(pos, editionNum);
                            else inputs.add(editionNum);
                            success = true;
                            scanner.nextLine();
                        } else if (option == 6) {
                            pos = inputs.indexOf(pubDate);
                            inputs.remove(pubDate);
                            pubDate = getUserStrInfo("date de parution (DD/MM/YYYY)");
                            success = true;
                            inputs.add(pos, pubDate);
                        } else if (option == 7) {
                            pos = inputs.indexOf(type);
                            inputs.remove(type);
                            System.out.println("Type: ");
                            System.out.println("1. imprime");
                            System.out.println("2. electronique");
                            if (getOption() == 1) {
                                type = Type.printed;
                                inputs.add(pos, type);
                            } else {
                                type = Type.electronic;
                                inputs.add(pos, type);
                            }
                            scanner.nextLine();
                            inputs.add(type);
                            success = true;
                            scanner.nextLine();
                        } else if (option == 8) {
                            pos = inputs.indexOf(initQuantity);
                            inputs.remove(initQuantity);
                            initQuantity = getUserNumInfo("Quantite");
                            if (pos != -1) inputs.add(pos, initQuantity);
                            else inputs.add(initQuantity);
                            success = true;
                            scanner.nextLine();
                        } else if (option == 9) {
                            while (!success) {
                                points = getUserNumInfo("bonus/$");
                                if (points >= 20) {
                                    System.out.println("point bonus par $ ne peut pas depasser 20");
                                } else success = true;
                                pos = inputs.indexOf(points);
                                inputs.remove(points);
                                inputs.add(pos, points);
                                scanner.nextLine();
                            }
                        } else if (option == 10) {
                            pos = inputs.indexOf(price);
                            inputs.remove(price);
                            price = getUserNumInfo("Prix");
                            inputs.add(pos, price);
                            success = true;
                            scanner.nextLine();
                        } else if (option == 11) {
                            pos = inputs.indexOf(isbn);
                            inputs.remove(isbn);
                            isbn = getUserStrInfo("ISBN");
                            success = true;
                            inputs.add(pos, isbn);
                        } else {
                            System.out.println("Option invalide");
                        }
                    }
                    success = false;
                }

            }
        }
        return new LearningResource(title, desc, "Ressource d'apprentissage",
                Calendar.getInstance().getTime().toString(),
                initQuantity, price, points, isbn, author, org, pubDate, type, editionNum);
    }

    private static Product getArticleInfo() {
        ArrayList<Object> inputs = new ArrayList<>();
        System.out.println("Donnez les informations de l'Article: ");
        String title = getUserStrInfo("Titre");
        inputs.add(title);
        String desc = getUserStrInfo("Description");
        inputs.add(desc);
        String brand = getUserStrInfo("Marque");
        inputs.add(brand);
        String model = getUserStrInfo("Model");
        inputs.add(model);
        String subCategory = getUserStrInfo("Sous-catégorie exemple: cahier, crayon, surligneur:");
        inputs.add(subCategory);

        long initQuantity = getUserNumInfo("Quantite");
        inputs.add(initQuantity);

        System.out.println("offrir des points bonus pour le produit:");
        System.out.println("1. oui");
        System.out.println("2. non");

        boolean success = false;
        long points = 1;
        if (getOption() == 1) {

            while (!success) {
                points = getUserNumInfo("bonus/$");
                if (points >= 20) {
                    System.out.println("point bonus par $ ne peut pas depasser 20");
                } else success = true;
            }

        }
        inputs.add(points);

        double price = getUserNumInfo("Prix");
        inputs.add(price);

        success = false;
        while (!success) {
            System.out.println();
            printInfo(inputs);
            System.out.println();

            System.out.println("Valider les informations du produit ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();

            if (getOption() == 1) {
                System.out.println("produit ajoutes avec succes");
            } else {
                System.out.println("Voulez vous modifier les informations du produit:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (getOption() == 1) {
                    System.out.println("Choisir information a modifier: ");

                    while (!success) {
                        System.out.println("1. Titre");
                        System.out.println("2. Description");
                        System.out.println("3. Marque");
                        System.out.println("4. Model");
                        System.out.println("5. sous-categorie");
                        System.out.println("6. Quantite");
                        System.out.println("7. bonus/$: ");
                        System.out.println("8. Price: ");

                        int option = getOption();
                        scanner.nextLine();
                        int pos = -1;
                        if (option == 1) {
                            pos = inputs.indexOf(title);
                            inputs.remove(title);
                            title = getUserStrInfo("Title");
                            success = true;
                            inputs.add(pos, title);
                        } else if (option == 2) {
                            pos = inputs.indexOf(desc);
                            inputs.remove(desc);
                            desc = getUserStrInfo("Description");
                            success = true;
                            inputs.add(pos, desc);
                        } else if (option == 3) {
                            pos = inputs.indexOf(brand);
                            inputs.remove(brand);
                            brand = getUserStrInfo("Marque");
                            success = true;
                            inputs.add(pos, brand);
                        } else if (option == 4) {
                            pos = inputs.indexOf(model);
                            inputs.remove(model);
                            model = getUserStrInfo("Modèle");
                            success = true;
                            inputs.add(pos, model);
                        } else if (option == 5) {
                            pos = inputs.indexOf(subCategory);
                            inputs.remove(subCategory);
                            subCategory = getUserStrInfo("sous-categorie");
                            success = true;
                            inputs.add(pos, subCategory);
                        } else if (option == 6) {
                            pos = inputs.indexOf(initQuantity);
                            inputs.remove(initQuantity);
                            initQuantity = getUserNumInfo("Quantite");
                            if (pos != -1) inputs.add(pos, initQuantity);
                            else inputs.add(initQuantity);
                            success = true;
                            scanner.nextLine();
                        } else if (option == 7) {
                            pos = inputs.indexOf(points);
                            inputs.remove(points);
                            while (!success) {
                                points = getUserNumInfo("bonus/$");
                                if (points >= 20) {
                                    System.out.println("point bonus par $ ne peut pas depasser 20");
                                } else success = true;
                                inputs.add(pos, points);
                                scanner.nextLine();
                            }
                        } else if (option == 8) {
                            pos = inputs.indexOf(price);
                            inputs.remove(price);
                            price = getUserNumInfo("Prix");
                            inputs.add(pos, price);
                            success = true;
                            scanner.nextLine();
                        } else {
                            System.out.println("Option invalide");
                        }

                    }
                }

            }
            success = true;
        }

        return new Article(title, desc, "Article",
                Calendar.getInstance().getTime().toString(),
                initQuantity, price, points, brand, model, subCategory);

    }

    private static Product getMaterialInfo() {
        ArrayList<Object> inputs = new ArrayList<>();
        System.out.println("Donnez les informations du materiel: ");
        String title = getUserStrInfo("Titre");
        inputs.add(title);
        String desc = getUserStrInfo("Description");
        inputs.add(desc);
        String brand = getUserStrInfo("Marque");
        inputs.add(brand);
        String model = getUserStrInfo("Model");
        inputs.add(model);
        String subCategory = getUserStrInfo("Sous-catégorie exemple: ordinateur, souris, clavier, disque dur externe");
        inputs.add(subCategory);
        String launchDate = getUserStrInfo("date de lancement (DD/MM/YYYY)");
        inputs.add(launchDate);

        long initQuantity = getUserNumInfo("Quantite");
        inputs.add(initQuantity);

        System.out.println("offrir des points bonus pour le produit:");
        System.out.println("1. oui");
        System.out.println("2. non");

        boolean success = false;
        long points = 1;
        if (getOption() == 1) {

            while (!success) {
                points = getUserNumInfo("bonus/$");
                if (points >= 20) {
                    System.out.println("point bonus par $ ne peut pas depasser 20");
                } else success = true;
            }

        }
        inputs.add(points);

        double price = getUserNumInfo("Prix");
        inputs.add(price);

        success = false;
        while (!success) {
            System.out.println();
            printInfo(inputs);
            System.out.println();

            System.out.println("Valider les informations du produit ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();

            if (getOption() == 1) {
                System.out.println("produit ajoutes avec succes");
            } else {
                System.out.println("Voulez vous modifier les informations du produit:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (getOption() == 1) {
                    System.out.println("Choisir information a modifier: ");

                    while (!success) {
                        System.out.println("1. Titre");
                        System.out.println("2. Description");
                        System.out.println("3. Marque");
                        System.out.println("4. Model");
                        System.out.println("5. sous-categorie");
                        System.out.println("6. Date de lancement");
                        System.out.println("7. Quantite");
                        System.out.println("8. bonus/$: ");
                        System.out.println("9. Price: ");

                        int option = getOption();
                        scanner.nextLine();
                        int pos = -1;
                        if (option == 1) {
                            pos = inputs.indexOf(title);
                            inputs.remove(title);
                            title = getUserStrInfo("Title");
                            success = true;
                            inputs.add(pos, title);
                        } else if (option == 2) {
                            pos = inputs.indexOf(desc);
                            inputs.remove(desc);
                            desc = getUserStrInfo("Description");
                            success = true;
                            inputs.add(pos, desc);
                        } else if (option == 3) {
                            pos = inputs.indexOf(brand);
                            inputs.remove(brand);
                            brand = getUserStrInfo("Marque");
                            success = true;
                            inputs.add(pos, brand);
                        } else if (option == 4) {
                            pos = inputs.indexOf(model);
                            inputs.remove(model);
                            model = getUserStrInfo("Modèle");
                            success = true;
                            inputs.add(pos, model);
                        } else if (option == 5) {
                            pos = inputs.indexOf(subCategory);
                            inputs.remove(subCategory);
                            subCategory = getUserStrInfo("sous-categorie");
                            success = true;
                            inputs.add(pos, subCategory);
                        } else if (option == 6) {
                            pos = inputs.indexOf(launchDate);
                            inputs.remove(launchDate);
                            launchDate = getUserStrInfo("Date de lancement");
                            success = true;
                            inputs.add(pos, launchDate);
                        } else if (option == 7) {
                            pos = inputs.indexOf(initQuantity);
                            inputs.remove(initQuantity);
                            initQuantity = getUserNumInfo("Quantite");
                            if (pos != -1) inputs.add(pos, initQuantity);
                            else inputs.add(initQuantity);
                            success = true;
                            scanner.nextLine();
                        } else if (option == 8) {
                            pos = inputs.indexOf(points);
                            inputs.remove(points);
                            while (!success) {
                                points = getUserNumInfo("bonus/$");
                                if (points >= 20) {
                                    System.out.println("point bonus par $ ne peut pas depasser 20");
                                } else success = true;
                                inputs.add(pos, points);
                                scanner.nextLine();
                            }
                        } else if (option == 9) {
                            pos = inputs.indexOf(price);
                            inputs.remove(price);
                            price = getUserNumInfo("Prix");
                            inputs.add(pos, price);
                            success = true;
                            scanner.nextLine();
                        } else {
                            System.out.println("Option invalide");
                        }

                    }
                }

            }
            success = true;
        }

        return new Hardware(title, desc, "Matériel informatique",
                Calendar.getInstance().getTime().toString(),
                initQuantity, price, points, brand, model, launchDate, subCategory);
    }

    private static Product getEquipmentInfo() {
        ArrayList<Object> inputs = new ArrayList<>();
        System.out.println("Donnez les informations de l'equipement");
        String title = getUserStrInfo("Titre");
        inputs.add(title);
        String desc = getUserStrInfo("Description");
        inputs.add(desc);
        String brand = getUserStrInfo("Marque");
        inputs.add(brand);
        String model = getUserStrInfo("Model");
        inputs.add(model);
        String subCategory = getUserStrInfo("Sous-catégorie exemple: table, chaise, lampe:");
        inputs.add(subCategory);

        long initQuantity = getUserNumInfo("Quantite");
        inputs.add(initQuantity);

        System.out.println("offrir des points bonus pour le produit:");
        System.out.println("1. oui");
        System.out.println("2. non");

        boolean success = false;
        long points = 1;
        if (getOption() == 1) {

            while (!success) {
                points = getUserNumInfo("bonus/$");
                if (points >= 20) {
                    System.out.println("point bonus par $ ne peut pas depasser 20");
                } else success = true;
            }

        }
        inputs.add(points);

        double price = getUserNumInfo("Prix");
        inputs.add(price);

        success = false;
        while (!success) {
            System.out.println();
            printInfo(inputs);
            System.out.println();

            System.out.println("Valider les informations du produit ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();

            if (getOption() == 1) {
                System.out.println("produit ajoutes avec succes");
            } else {
                System.out.println("Voulez vous modifier les informations du produit:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (getOption() == 1) {
                    System.out.println("Choisir information a modifier: ");

                    while (!success) {
                        System.out.println("1. Titre");
                        System.out.println("2. Description");
                        System.out.println("3. Marque");
                        System.out.println("4. Model");
                        System.out.println("5. sous-categorie");
                        System.out.println("5. Quantite");
                        System.out.println("6. bonus/$: ");
                        System.out.println("7. Price: ");

                        int option = getOption();
                        scanner.nextLine();
                        int pos = -1;
                        if (option == 1) {
                            pos = inputs.indexOf(title);
                            inputs.remove(title);
                            title = getUserStrInfo("Title");
                            success = true;
                            inputs.add(pos, title);
                        } else if (option == 2) {
                            pos = inputs.indexOf(desc);
                            inputs.remove(desc);
                            desc = getUserStrInfo("Description");
                            success = true;
                            inputs.add(pos, desc);
                        } else if (option == 3) {
                            pos = inputs.indexOf(brand);
                            inputs.remove(brand);
                            brand = getUserStrInfo("Marque");
                            success = true;
                            inputs.add(pos, brand);
                        } else if (option == 4) {
                            pos = inputs.indexOf(model);
                            inputs.remove(model);
                            model = getUserStrInfo("Modèle");
                            success = true;
                            inputs.add(pos, model);
                        } else if (option == 5) {
                            pos = inputs.indexOf(subCategory);
                            inputs.remove(subCategory);
                            subCategory = getUserStrInfo("sous-categorie");
                            success = true;
                            inputs.add(pos, subCategory);
                        } else if (option == 6) {
                            pos = inputs.indexOf(initQuantity);
                            inputs.remove(initQuantity);
                            initQuantity = getUserNumInfo("Quantite");
                            if (pos != -1) inputs.add(pos, initQuantity);
                            else inputs.add(initQuantity);
                            success = true;
                            scanner.nextLine();
                        } else if (option == 7) {
                            pos = inputs.indexOf(points);
                            inputs.remove(points);
                            while (!success) {
                                points = getUserNumInfo("bonus/$");
                                if (points >= 20) {
                                    System.out.println("point bonus par $ ne peut pas depasser 20");
                                } else success = true;
                                inputs.add(pos, points);
                                scanner.nextLine();
                            }
                        } else if (option == 8) {
                            pos = inputs.indexOf(price);
                            inputs.remove(price);
                            price = getUserNumInfo("Prix");
                            inputs.add(pos, price);
                            success = true;
                            scanner.nextLine();
                        } else {
                            System.out.println("Option invalide");
                        }

                    }
                }

            }
            success = true;
        }

        return new DesktopTool(title, desc, "Article",
                Calendar.getInstance().getTime().toString(),
                initQuantity, price, points, brand, model, subCategory);
    }

    private static Product getBookInfo() {
        ArrayList<Object> inputs = new ArrayList<>();
        System.out.println("Donnez les informations du Livre/Manuel: ");
        String title = getUserStrInfo("Titre");
        inputs.add(title);
        String desc = getUserStrInfo("Description");
        inputs.add(desc);
        String author = getUserStrInfo("Auteur");
        inputs.add(author);
        String isbn = getUserStrInfo("ISBN");
        inputs.add(isbn);
        String editor = getUserStrInfo("Maison d'edition");
        inputs.add(editor);

        System.out.println("possede numero d'edition: ");
        System.out.println("1. oui");
        System.out.println("2. non");

        long editionNum = -1;
        if (getOption() == 1) {
            editionNum = getUserNumInfo("Numero d'edition");
            inputs.add(editionNum);
            scanner.nextLine();
        }

        String genre = getUserStrInfo("genre");
        inputs.add(genre);
        String pubDate = getUserStrInfo("date de parution (DD/MM/YYYY)");
        inputs.add(pubDate);

        System.out.println("possede numero de volume");
        System.out.println("1. oui");
        System.out.println("2. non");

        long volNum = -1;
        if (getOption() == 1) {
            volNum = getUserNumInfo("Numero de volume");
            inputs.add(volNum);
            scanner.nextLine();
        }

        long initQuantity = getUserNumInfo("Quantite");
        inputs.add(initQuantity);

        System.out.println("offrir des points bonus pour le produit:");
        System.out.println("1. oui");
        System.out.println("2. non");

        boolean success = false;
        long points = 1;
        if (getOption() == 1) {

            while (!success) {
                points = getUserNumInfo("bonus/$");
                if (points >= 20) {
                    System.out.println("point bonus par $ ne peut pas depasser 20");
                } else success = true;
            }

        }
        inputs.add(points);

        double price = getUserNumInfo("Prix");
        inputs.add(price);

        success = false;
        while (!success) {
            System.out.println();
            printInfo(inputs);
            System.out.println();

            System.out.println("Valider les informations du produit ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();

            if (getOption() == 1) {
                System.out.println("produit ajoutes avec succes");
            } else {
                System.out.println("Voulez vous modifier les informations du produit:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (getOption() == 1) {
                    System.out.println("Choisir information a modifier: ");

                    while (!success) {
                        System.out.println("1. Titre");
                        System.out.println("2. Description");
                        System.out.println("3. Auteur");
                        System.out.println("4. Maison d'edition");
                        System.out.println("5. Numero d'edition");
                        System.out.println("6. Genre");
                        System.out.println("7. date de parution (DD/MM/YYYY): ");
                        System.out.println("8. Numero de Volume");
                        System.out.println("9. Quantite");
                        System.out.println("10. bonus/$: ");
                        System.out.println("11. Price: ");
                        System.out.println("12. ISBN: ");

                        int option = getOption();
                        scanner.nextLine();
                        int pos = -1;
                        if (option == 1) {
                            pos = inputs.indexOf(title);
                            inputs.remove(title);
                            title = getUserStrInfo("Title");
                            success = true;
                            inputs.add(pos, title);
                        } else if (option == 2) {
                            pos = inputs.indexOf(desc);
                            inputs.remove(desc);
                            desc = getUserStrInfo("Description");
                            success = true;
                            inputs.add(pos, desc);
                        } else if (option == 3) {
                            pos = inputs.indexOf(author);
                            inputs.remove(author);
                            author = getUserStrInfo("Auteur");
                            success = true;
                            inputs.add(pos, author);
                        } else if (option == 4) {
                            pos = inputs.indexOf(editor);
                            inputs.remove(editor);
                            editor = getUserStrInfo("Maison d'edition");
                            success = true;
                            inputs.add(pos, editor);
                        } else if (option == 5) {
                            pos = inputs.indexOf(editionNum);
                            inputs.remove(editionNum);
                            editionNum = getUserNumInfo("Numero d'edition");
                            if (pos != -1) inputs.add(pos, editionNum);
                            else inputs.add(editionNum);
                            success = true;
                            scanner.nextLine();
                        } else if (option == 6) {
                            pos = inputs.indexOf(genre);
                            inputs.remove(genre);
                            genre = getUserStrInfo("Genre");
                            success = true;
                            inputs.add(pos, genre);
                        } else if (option == 7) {
                            pos = inputs.indexOf(pubDate);
                            inputs.remove(pubDate);
                            pubDate = getUserStrInfo("date de parution (DD/MM/YYYY)");
                            success = true;
                            inputs.add(pos, pubDate);
                        } else if (option == 8) {
                            pos = inputs.indexOf(volNum);
                            inputs.remove(volNum);
                            volNum = getUserNumInfo("Numero de volume");
                            if (pos != -1) inputs.add(pos, volNum);
                            else inputs.add(volNum);
                            success = true;
                            scanner.nextLine();
                        } else if (option == 9) {
                            pos = inputs.indexOf(initQuantity);
                            inputs.remove(initQuantity);
                            initQuantity = getUserNumInfo("Quantite");
                            if (pos != -1) inputs.add(pos, initQuantity);
                            else inputs.add(initQuantity);
                            success = true;
                            scanner.nextLine();
                        } else if (option == 10) {
                            pos = inputs.indexOf(points);
                            inputs.remove(points);
                            while (!success) {
                                points = getUserNumInfo("bonus/$");
                                if (points >= 20) {
                                    System.out.println("point bonus par $ ne peut pas depasser 20");
                                } else success = true;
                                inputs.add(pos, points);
                                scanner.nextLine();
                            }
                        } else if (option == 11) {
                            pos = inputs.indexOf(price);
                            inputs.remove(price);
                            price = getUserNumInfo("Prix");
                            inputs.add(pos, price);
                            success = true;
                            scanner.nextLine();
                        } else if (option == 12) {
                            pos = inputs.indexOf(isbn);
                            inputs.remove(isbn);
                            isbn = getUserStrInfo("ISBN");
                            success = true;
                            inputs.add(pos, isbn);
                        } else {
                            System.out.println("Option invalide");
                        }

                    }
                }

            }
            success = true;
        }

        return new Book(title, desc, "Livre ou Manuel",
                Calendar.getInstance().getTime().toString(),
                initQuantity, price, points, isbn, author, editor, genre, pubDate, editionNum, volNum);

    }

    private static Product getProductInfo() {
        System.out.println("Choisissez une categorie de produit a vendre:");
        System.out.println("1. Livres et Manuels");
        System.out.println("2. Ressource d'apprentissage");
        System.out.println("3. Article de papeterie");
        System.out.println("4. Materiel informatique");
        System.out.println("5. Equipement de bureau");
        Product product = null;

        int option = getOption();
        scanner.nextLine();
        switch (option) {
            case 1:
                product = getBookInfo();
                break;
            case 2:
                product = getLearningResourceInfo();
                break;
            case 3:
                getArticleInfo();
                break;
            case 4:
                getMaterialInfo();
                break;
            case 5:
                getEquipmentInfo();
                break;
        }
        return product;
    }

    private static Seller getSellerRegistrationInfo() {
        ArrayList<Object> inputs = new ArrayList<>();
        System.out.println("Vous devez offrir au moins un produit à vendre au prealable");
        System.out.println("1. Offrir un produit à vendre:");
        Product product = null;

        if (getOption() == 1) {
            inputs = new ArrayList<>();
            product = getProductInfo();
        }
        scanner = new Scanner(System.in);
        System.out.println("Saisissez vos informations");

        String firstName = getUserStrInfo("Prenom");
        inputs.add(firstName);
        String lastName = getUserStrInfo("Nom");
        inputs.add(lastName);
        String email = getUserStrInfo("Email");
        inputs.add(email);
        String pseudo = getUserStrInfo("pseudo");
        inputs.add(pseudo);
        long number = getUserNumInfo("Numero");
        scanner.nextLine();
        inputs.add(number);
        String shipAddress = getUserStrInfo("Adresse de livraison");
        inputs.add(shipAddress);

        boolean success = false;
        while (!success) {

            System.out.println();
            printInfo(inputs);
            System.out.println();

            System.out.println("Confirmer vos informations: ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();

            if (getOption() == 1) {
                System.out.println("votre compte a ete cree avec succes");
                success = true;
            } else {
                System.out.println("Voulez vous modifier vos informations:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (getOption() == 1) {
                    System.out.println("Choisir information a modifier: ");


                    while (!success) {
                        System.out.println("1. Prenom");
                        System.out.println("2. Nom");
                        System.out.println("3. Email");
                        System.out.println("4. Pseudo");
                        System.out.println("5. Number");

                        int option = getOption();
                        scanner.nextLine();

                        switch (option) {
                            case 1:
                                int pos = inputs.indexOf(firstName);
                                inputs.remove(firstName);
                                firstName = getUserStrInfo("Prenom");
                                success = true;
                                inputs.add(pos, firstName);
                                break;
                            case 2:
                                pos = inputs.indexOf(lastName);
                                inputs.remove(lastName);
                                lastName = getUserStrInfo("Nom");
                                success = true;
                                inputs.add(pos, lastName);
                                break;
                            case 3:
                                pos = inputs.indexOf(email);
                                inputs.remove(email);
                                email = getUserStrInfo("Email");
                                success = true;
                                inputs.add(pos, email);
                                break;
                            case 4:
                                pos = inputs.indexOf(pseudo);
                                inputs.remove(pseudo);
                                pseudo = getUserStrInfo("Pseudo");
                                success = true;
                                inputs.add(pos, pseudo);
                                break;
                            case 5:
                                pos = inputs.indexOf(number);
                                inputs.remove(number);
                                number = getUserNumInfo("Numero");
                                success = true;
                                inputs.add(pos, number);
                                scanner.nextLine();
                                break;
                            default:
                                System.out.println("option doit etre compris entre 1 et 5");
                                break;
                        }
                    }
                    success = false;
                }

            }
        }
        ArrayList<Product> products = new ArrayList<Product>();
        products.add(product);
        return new Seller(firstName, lastName, email, pseudo, number, products);
    }

    private static Client getClientRegistrationInfo() {
        ArrayList<Object> inputs = new ArrayList<>();
        System.out.println("Saisissez vos informations");

        String firstName = getUserStrInfo("Prenom");
        inputs.add(firstName);
        String lastName = getUserStrInfo("Nom");
        inputs.add(lastName);
        String email = getUserStrInfo("Email");
        inputs.add(email);
        String pseudo = getUserStrInfo("pseudo");
        inputs.add(pseudo);
        long number = getUserNumInfo("Numero");
        scanner.nextLine();
        inputs.add(number);
        String shipAddress = getUserStrInfo("Adresse de livraison");
        inputs.add(shipAddress);

        boolean success = false;
        while (!success) {

            System.out.println();
            printInfo(inputs);
            System.out.println();

            System.out.println("Confirmer vos informations: ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();

            if (getOption() == 1) {
                System.out.println("votre compte a ete cree avec succes");
                success = true;
            } else {
                System.out.println("Voulez vous modifier vos informations:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (getOption() == 1) {
                    System.out.println("Choisir information a modifier: ");


                    while (!success) {
                        System.out.println("1. Prenom");
                        System.out.println("2. Nom");
                        System.out.println("3. Email");
                        System.out.println("4. Pseudo");
                        System.out.println("5. Number");

                        int option = getOption();
                        scanner.nextLine();

                        switch (option) {
                            case 1:
                                int pos = inputs.indexOf(firstName);
                                inputs.remove(firstName);
                                firstName = getUserStrInfo("Prenom");
                                success = true;
                                inputs.add(pos, firstName);
                                break;
                            case 2:
                                pos = inputs.indexOf(lastName);
                                inputs.remove(lastName);
                                lastName = getUserStrInfo("Nom");
                                success = true;
                                inputs.add(pos, lastName);
                                break;
                            case 3:
                                pos = inputs.indexOf(email);
                                inputs.remove(email);
                                email = getUserStrInfo("Email");
                                success = true;
                                inputs.add(pos, email);
                                break;
                            case 4:
                                pos = inputs.indexOf(pseudo);
                                inputs.remove(pseudo);
                                pseudo = getUserStrInfo("Pseudo");
                                success = true;
                                inputs.add(pos, pseudo);
                                break;
                            case 5:
                                pos = inputs.indexOf(number);
                                inputs.remove(number);
                                number = getUserNumInfo("Numero");
                                success = true;
                                inputs.add(pos, number);
                                scanner.nextLine();
                                break;
                            default:
                                System.out.println("option doit etre compris entre 1 et 5");
                                break;
                        }
                        success = false;
                    }
                }
            }
            success = true;
        }
        return new Client(firstName, lastName, email, pseudo, number, shipAddress);
    }

    private static int getOption() {
        int option = 0;
        boolean success = false;
        while (!success) {
            try {
                option = scanner.nextInt();
                success = true;
            } catch (InputMismatchException e) {
                System.err.println("Ooops! option doit etre un chiffre");
                scanner.next();
            }
        }
        return option;
    }

    private static String getUserStrInfo(String info) {
        System.out.print(info + ": ");
        String input = null;
        boolean success = false;
        while (!success) {
            try {
                input = scanner.nextLine();
                success = true;
            } catch (InputMismatchException e) {
                System.err.println("Ooops! " + info + " doit etre une chaine de caracteres");
            }
        }
        return input;
    }

    //To fix
    private static long getUserNumInfo(String info) {
        System.out.print(info + ": ");
        long input = 0;
        boolean success = true;
        while (success) {
            try {
                input = scanner.nextLong();
                success = false;
            } catch (InputMismatchException e) {
                System.err.println("Ooops! " + info + " doit etre un nombre");
                scanner.next();
            }
        }
        return input;
    }

    private static void printInfo(List<Object> inputs) {
        for (Object input : inputs) {
            System.out.println("- " + input);
        }
    }

    static void getClientServiceInfo(Scanner scanner, List<Product> products, List<Seller> sellers) {
        ShoppingCart shoppingCart = new ShoppingCart();

        while (true) {
            List<Object> inputs = new ArrayList<>();

            System.out.println("Sélectionnez la tâche que vous voulez effectuer: ");
            System.out.println("1. Chercher un produit");
            System.out.println("2. Chercher un vendeur");
            System.out.println("3. Afficher le panier");
            System.out.println("4. Quitter");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Liste des produits disponibles :");
                    for (Product product : products) {
                        System.out.println("- " + product.getTitle());
                    }

                    System.out.print("Entrez le titre du produit : ");
                    String productTitle = scanner.nextLine();


                    Product selectedProduct = findProductByTitle(productTitle, products);

                    if (selectedProduct != null) {
                        inputs.add(selectedProduct);
                        shoppingCart.add(selectedProduct);
                        System.out.println("Produit ajouté au panier.");


                        System.out.println("Que souhaitez-vous faire maintenant?");
                        System.out.println("1. Ajouter un autre produit");
                        System.out.println("2. Procéder au paiement");

                        int addOrProceed = scanner.nextInt();
                        scanner.nextLine();

                        if (addOrProceed == 2) {

                            System.out.println("Contenu du panier :");
                            System.out.println(shoppingCart.toString());
                            System.out.println("Total à payer : " + shoppingCart.getTotal());


                            System.out.println("Paiement effectué. Merci!");
                            return;
                        }

                    } else {
                        System.out.println("Produit non trouvé.");
                    }
                    break;

                case 2:
                    System.out.println("Liste des vendeurs :");
                    for (Seller seller : sellers) {
                        System.out.println("- " + seller.getFirstName());
                    }

                    System.out.print("Entrez le nom du vendeur : ");
                    String sellerName = scanner.nextLine();

                    Seller selectedSeller = findSellerByName(sellerName, sellers);
                    if (selectedSeller != null) {

                        System.out.println("Produits du vendeur " + selectedSeller.getFirstName() + " :");
                        for (Product product : selectedSeller.getProducts()) {
                            System.out.println("- " + product.getTitle());
                        }

                        System.out.print("Entrez le titre du produit du vendeur : ");
                        productTitle = scanner.nextLine();

                        selectedProduct = findProductByTitle(productTitle, selectedSeller.getProducts());

                        if (selectedProduct != null) {
                            inputs.add(selectedProduct);
                            shoppingCart.add(selectedProduct);
                            System.out.println("Produit ajouté au panier.");

                            System.out.println("Que souhaitez-vous faire maintenant?");
                            System.out.println("1. Ajouter un autre produit");
                            System.out.println("2. Procéder au paiement");

                            int addOrProceed = scanner.nextInt();
                            scanner.nextLine();

                            if (addOrProceed == 2) {
                                System.out.println("Contenu du panier :");
                                System.out.println(shoppingCart.toString());
                                System.out.println("Total à payer : " + shoppingCart.getTotal());

                                System.out.println("Paiement effectué. Merci!");
                                return;
                            }
                        } else {
                            System.out.println("Produit non trouvé.");
                        }
                    } else {
                        System.out.println("Vendeur non trouvé.");
                    }
                    break;

                case 3:
                    System.out.println("Contenu du panier :");
                    System.out.println(shoppingCart.toString());
                    break;

                case 4:
                    System.out.println("Merci d'avoir utilisé notre service. Au revoir!");
                    return;

                default:
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
            }
        }
    }

    private static Product findProductByTitle(String title, List<Product> products) {
        for (Product product : products) {
            if (product.getTitle().equalsIgnoreCase(title)) {
                return product;
            }
        }
        return null;
    }
    private static Seller findSellerByName(String name, List<Seller> sellers) {
        for (Seller seller : sellers) {
            if (seller.getFirstName().equalsIgnoreCase(name)) {
                return seller;
            }
        }
        return null;
    }

    private static void getSellerServiceInfo(Scanner scanner) {
        List<Object> inputs = new ArrayList<>();
        System.out.println("Selectionner la tache que voulez effectuer: ");
        System.out.println("1. Offrir un produit: ");
        System.out.println("2. Changer l'etat d'une commande: ");

        int option = getOption();
        switch (option) {
            case 1:
                inputs = new ArrayList<>();
                getProductInfo();
                break;
            case 2:
                inputs = new ArrayList<>();
                //getSearchInfo(scanner,inputs);
        }
    }

    private static void searchProduct(Scanner scanner, List<Object> inputs) {

    }

    public static <Users> void run() {
        List<Users> users = new ArrayList<>();
        User user = getRegistrationStream();
        if (user instanceof Seller) System.out.println(((Seller) user).getProducts());
        System.out.println("##########################");


    }

}
