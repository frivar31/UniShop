import Data.Entities.Products.*;
import Data.Entities.ShoppingCart;
import Data.Entities.Type;
import Data.Entities.Users.Client;
import Data.Entities.Users.Seller;
import Data.Entities.Users.User;

import java.util.*;

public class App {
    public static Scanner scanner = new Scanner(System.in);
    //tested
    private static User getRegistrationStream() {
        System.out.println("Choisissez une option d'inscription");
        System.out.println("1. Vendeur");
        System.out.println("2. Acheteur");
        if (getOption(1,2) == 2) {
            //create a client
            return getClientRegistrationInfo();
        }
        //define a seller
        return getSellerRegistrationInfo();
    }
    //tested
    private static LearningResource getLearningResourceInfo() {
        System.out.println("Donnez les informations du Livre/Manuel: ");
        String title = getUserStrInfo("Titre");
        String desc = getUserStrInfo("Description");
        String author = getUserStrInfo("Auteur");
        String isbn = getUserStrInfo("ISBN");
        String org = getUserStrInfo("Organisation");

        System.out.println("possede numero d'edition: ");
        System.out.println("1. oui");
        System.out.println("2. non");

        long editionNum = -1;
        if (getOption(1,2) == 1) {
            editionNum = getUserNumInfo("Numero d'edition");
            scanner.nextLine();
        }

        String pubDate = getUserStrInfo("date de parution (DD/MM/YYYY)");

        System.out.println("Type: ");
        System.out.println("1. imprime");
        System.out.println("2. electronique");

        Type type = null;
        if (getOption(1,2) == 1) {
            type = Type.printed;
        } else {
            type = Type.electronic;
        }
        scanner.nextLine();

        long initQuantity = getUserNumInfo("Quantite");

        System.out.println("offrir des points bonus pour le produit:");
        System.out.println("1. oui");
        System.out.println("2. non");
        long points = 1;
        if (getOption(1,2) == 1) {
            points = getUserNumInfo("bonus/$",1,20);
        }

        double price = getUserNumInfo("Prix",1);

        LearningResource product=new LearningResource(title, desc, "Ressource d'apprentissage",
                Calendar.getInstance().getTime().toString(),
                initQuantity, price, points, isbn, author, org, pubDate, type, editionNum);

        int option=2;
        while (option==2) {
            System.out.println(product);
            System.out.println("Valider les informations: du produit ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();
            option=getOption(1,2);
            if (option== 1) {
                System.out.println("produit ajoutes avec succes");
            } else {
                System.out.println("Voulez vous modifier les informations du produit:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (getOption(1,2) == 1) {
                    System.out.println("Choisir information a modifier: ");
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
                    System.out.println("11. ISBN: ");

                    option = getOption(1,11);
                    scanner.nextLine();
                    if (option == 1) {
                        product.setTitle(getUserStrInfo("Title"));
                    } else if (option == 2) {
                        product.setDesc(getUserStrInfo("Description"));
                    } else if (option == 3) {
                        product.setAuteur(author);
                    } else if (option == 4) {
                        product.setOrganisation(getUserStrInfo("Organisation"));
                    } else if (option == 5) {
                        product.setEditionNumber(getUserNumInfo("Numero d'edition"));
                    } else if (option == 6) {
                        product.setPublishDate(getUserStrInfo("date de parution (DD/MM/YYYY)"));
                    } else if (option == 7) {
                        System.out.println("Type: ");
                        System.out.println("1. imprime");
                        System.out.println("2. electronique");
                        if (getOption(1,2) == 1) {
                            type = Type.printed;
                        } else {
                            type = Type.electronic;
                        }
                        product.setType(type);
                    } else if (option == 8) {
                        product.setquantity(getUserNumInfo("Quantite",1));
                    } else if (option == 9) {
                        points = getUserNumInfo("bonus/$",1,20);
                        product.setPoints(points);
                    } else if (option == 10) {
                        product.setPrice(getUserNumInfo("Price",1));
                    } else if (option == 11) {

                        product.setISBN(getUserStrInfo("ISBN"));
                    } else {
                        System.out.println("Option invalide");
                    }
                    option=2;
                }

            }
        }
        return product;
    }
    //tested
    private static Product getArticleInfo() {
        System.out.println("Donnez les informations de l'Article: ");
        String title = getUserStrInfo("Titre");
        String desc = getUserStrInfo("Description");
        String brand = getUserStrInfo("Marque");
        String model = getUserStrInfo("Model");
        String subCategory = getUserStrInfo("Sous-catégorie exemple: cahier, crayon, surligneur:");

        long initQuantity = getUserNumInfo("Quantite",1);

        System.out.println("offrir des points bonus pour le produit:");
        System.out.println("1. oui");
        System.out.println("2. non");

        long points = 1;
        if (getOption(1,2) == 1) {
            points = getUserNumInfo("bonus/$",1,20);
        }
        double price = getUserNumInfo("Prix",1);
        Article product=new Article(title, desc, "Article",
                Calendar.getInstance().getTime().toString(),
                initQuantity, price, points, brand, model, subCategory);

        int option = 2;
        while (option==2) {
            System.out.println(product);
            System.out.println("Validez-vous les informations du produit ?");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();
            option=getOption(1,2);
            if (option == 1) {
                System.out.println("produit ajoutes avec succes");
            } else {
                System.out.println("Voulez vous modifier les informations du produit:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (getOption(1,2) == 1) {
                    System.out.println("Choisir information a modifier: ");
                        System.out.println("1. Titre");
                        System.out.println("2. Description");
                        System.out.println("3. Marque");
                        System.out.println("4. Model");
                        System.out.println("5. sous-categorie");
                        System.out.println("6. Quantite");
                        System.out.println("7. bonus/$: ");
                        System.out.println("8. Price: ");

                        option = getOption(1,8);
                        scanner.nextLine();
                        if (option == 1) {
                            product.setTitle(getUserStrInfo("Title"));
                        } else if (option == 2) {
                            product.setDesc(getUserStrInfo("Description"));
                        } else if (option == 3) {
                            product.setBrand(getUserStrInfo("Marque"));
                        } else if (option == 4) {
                            product.setBrand(getUserStrInfo("Modèle"));
                        } else if (option == 5) {
                            product.setSubCategory(getUserStrInfo("sous-categorie"));
                        } else if (option == 6) {
                            product.setquantity(getUserNumInfo("Quantite",1));
                        } else if (option == 7) {
                            product.setPoints(getUserNumInfo("bonus/$",1,20));
                        } else if (option == 8) {
                            product.setPrice(getUserNumInfo("Prix",1));
                        } else {
                            System.out.println("Option invalide");
                        }
                }
                option=2;
            }
        }

        return product;

    }
    //tested
    private static Product getMaterialInfo() {
        System.out.println("Donnez les informations du materiel: ");
        String title = getUserStrInfo("Titre");
        String desc = getUserStrInfo("Description");
        String brand = getUserStrInfo("Marque");
        String model = getUserStrInfo("Model");
        String subCategory = getUserStrInfo("Sous-catégorie exemple: ordinateur, souris, clavier, disque dur externe");
        String launchDate = getUserStrInfo("date de lancement (DD/MM/YYYY)");
        long initQuantity = getUserNumInfo("Quantite",1);

        System.out.println("offrir des points bonus pour le produit:");
        System.out.println("1. oui");
        System.out.println("2. non");

        long points = 1;
        if (getOption(1,2) == 1) {
            points = getUserNumInfo("bonus/$",1,20);
        }
        double price = getUserNumInfo("Prix",1);
        Hardware product=new Hardware(title, desc, "Matériel informatique",
                Calendar.getInstance().getTime().toString(),
                initQuantity, price, points, brand, model, launchDate, subCategory);
        int option=2;
        while (option==2) {
            System.out.println(product);
            System.out.println("Valider les informations du produit ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();
            option=getOption(1,2);
            if (option == 1) {
                System.out.println("produit ajoutes avec succes");
            } else {
                System.out.println("Voulez vous modifier les informations du produit:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (getOption(1,2) == 1) {
                    System.out.println("Choisir information a modifier: ");
                    System.out.println("1. Titre");
                    System.out.println("2. Description");
                    System.out.println("3. Marque");
                    System.out.println("4. Model");
                    System.out.println("5. sous-categorie");
                    System.out.println("6. Date de lancement");
                    System.out.println("7. Quantite");
                    System.out.println("8. bonus/$: ");
                    System.out.println("9. Price: ");

                    option = getOption(1,9);
                    if (option == 1) {
                        product.setTitle(getUserStrInfo("Title"));
                    } else if (option == 2) {
                        product.setDesc(getUserStrInfo("Description"));
                    } else if (option == 3) {
                        product.setBrand(getUserStrInfo("Marque"));
                    } else if (option == 4) {
                        product.setModel(getUserStrInfo("Modèle"));
                    } else if (option == 5) {
                        product.setSubCategory(getUserStrInfo("sous-categorie"));
                    } else if (option == 6) {
                        product.setLauchDate(getUserStrInfo("Date de lancement"));
                    } else if (option == 7) {
                        product.setquantity(getUserNumInfo("Quantite",1));
                    } else if (option == 8) {
                        product.setPoints(getUserNumInfo("bonus/$",1,20));
                    } else if (option == 9) {
                        product.setPrice(getUserNumInfo("Prix",1));
                    } else {
                        System.out.println("Option invalide");
                    }
                    option=2;
                }
            }
        }

        return product;
    }
    //tested
    private static Product getEquipmentInfo() {
        System.out.println("Donnez les informations de l'equipement");
        String title = getUserStrInfo("Titre");
        String desc = getUserStrInfo("Description");
        String brand = getUserStrInfo("Marque");
        String model = getUserStrInfo("Model");
        String subCategory = getUserStrInfo("Sous-catégorie exemple: table, chaise, lampe:");

        long initQuantity = getUserNumInfo("Quantite",1);

        System.out.println("offrir des points bonus pour le produit:");
        System.out.println("1. oui");
        System.out.println("2. non");

        long points = 1;
        if (getOption() == 1) {
            points=getUserNumInfo("bonus/$",1,20);
        }

        double price = getUserNumInfo("Prix",1);
        DesktopTool product= new  DesktopTool(title, desc, "Article",
                Calendar.getInstance().getTime().toString(),
                initQuantity, price, points, brand, model, subCategory);

        int option=2;
        while (option==2) {
            System.out.println(product);

            System.out.println("Valider les informations du produit ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();
            option=getOption(1,2);
            if (option == 1) {
                System.out.println("produit ajoutes avec succes");
            } else {
                System.out.println("Voulez vous modifier les informations du produit:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (getOption(1,2) == 1) {
                    System.out.println("Choisir information a modifier: ");
                        System.out.println("1. Titre");
                        System.out.println("2. Description");
                        System.out.println("3. Marque");
                        System.out.println("4. Model");
                        System.out.println("5. sous-categorie");
                        System.out.println("5. Quantite");
                        System.out.println("6. bonus/$: ");
                        System.out.println("7. Price: ");
                        option = getOption(1,7);
                        if (option == 1) {
                            product.setTitle(getUserStrInfo("Title"));
                        } else if (option == 2) {
                            product.setDesc(getUserStrInfo("Description"));
                        } else if (option == 3) {
                            product.setBrand(getUserStrInfo("Marque"));
                        } else if (option == 4) {
                            product.setModel(getUserStrInfo("Modèle"));
                        } else if (option == 5) {
                            product.setSubCategory(getUserStrInfo("sous-categorie"));
                        } else if (option == 6) {
                            product.setquantity(getUserNumInfo("Quantite",1));
                        } else if (option == 7) {
                            product.setPoints(getUserNumInfo("bonus/$",1,20));
                        } else if (option == 8) {
                            product.setPrice(getUserNumInfo("Prix",1));
                        } else {
                            System.out.println("Option invalide");
                        }
                        option=2;
                }
            }
        }

        return product;
    }
    //tested
    private static Product getBookInfo() {
        System.out.println("Donnez les informations du Livre/Manuel: ");
        String title = getUserStrInfo("Titre");
        String desc = getUserStrInfo("Description");
        String author = getUserStrInfo("Auteur");
        String isbn = getUserStrInfo("ISBN");
        String editor = getUserStrInfo("Maison d'edition");

        System.out.println("possede numero d'edition: ");
        System.out.println("1. oui");
        System.out.println("2. non");

        long editionNum = -1;
        if (getOption(1,2) == 1) {
            editionNum = getUserNumInfo("Numero d'edition");
        }

        String genre = getUserStrInfo("genre");
        String pubDate = getUserStrInfo("date de parution (DD/MM/YYYY)");

        System.out.println("possede numero de volume");
        System.out.println("1. oui");
        System.out.println("2. non");

        long volNum = -1;
        if (getOption(1,2) == 1) {
            volNum = getUserNumInfo("Numero de volume");
        }

        long initQuantity = getUserNumInfo("Quantite",1);

        System.out.println("offrir des points bonus pour le produit:");
        System.out.println("1. oui");
        System.out.println("2. non");

        long points = 1;
        if (getOption(1,2) == 1) {
            points=getUserNumInfo("bonus/$",1,20);
        }
        double price = getUserNumInfo("Prix",1);
        Book product=new Book(title, desc, "Livre ou Manuel",
                Calendar.getInstance().getTime().toString(),
                initQuantity, price, points, isbn, author, editor, genre, pubDate, editionNum, volNum);
        int option=2;
        while (option==2) {
            System.out.println(product);

            System.out.println("Valider les informations du produit ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();
            option=getOption(1,2);
            if (option == 1) {
                System.out.println("produit ajoutes avec succes");
            } else {
                System.out.println("Voulez vous modifier les informations du produit:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (getOption(1,2) == 1) {
                    System.out.println("Choisir information a modifier: ");

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

                    option = getOption();
                    scanner.nextLine();
                    if (option == 1) {
                        product.setTitle(getUserStrInfo("Title"));
                    } else if (option == 2) {
                        product.setDesc(getUserStrInfo("Description"));
                    } else if (option == 3) {
                        product.setAuthor(getUserStrInfo("Auteur"));
                    } else if (option == 4) {
                        product.setEditor(getUserStrInfo("Maison d'edition"));
                    } else if (option == 5) {
                        product.setEditorNum(getUserNumInfo("Numero d'edition"));
                    } else if (option == 6) {
                        product.setGenre(getUserStrInfo("Genre"));
                    } else if (option == 7) {
                        product.setPubDate(getUserStrInfo("date de parution (DD/MM/YYYY)"));
                    } else if (option == 8) {
                        product.setVolNum(getUserNumInfo("Numero de volume"));
                    } else if (option == 9) {
                        product.setquantity(getUserNumInfo("Quantite",1));
                    } else if (option == 10) {
                        product.setPoints(getUserNumInfo("bonus/$",1,20));
                    } else if (option == 11) {
                        product.setPrice(getUserNumInfo("Prix",1));
                    } else if (option == 12) {
                        product.setISBN(getUserStrInfo("ISBN"));
                    } else {
                        System.out.println("Option invalide");
                    }
                    option=2;
                }
            }
        }

        return product;

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
        System.out.println("Vous devez offrir au moins un produit à vendre au prealable");
        System.out.println("1. Offrir un produit à vendre:");
        Product product = null;

        if (getOption(1,1) == 1) {
            product = getProductInfo();
        }
        scanner = new Scanner(System.in);
        System.out.println("Saisissez vos informations");

        String firstName = getUserStrInfo("Prenom");
        String lastName = getUserStrInfo("Nom");
        String email = getUserStrInfo("Email");
        String pseudo = getUserStrInfo("pseudo");
        long number = getUserNumInfo("Numero");
        scanner.nextLine();
        String shipAddress = getUserStrInfo("Adresse de livraison");

        ArrayList<Product> products = new ArrayList<Product>();
        products.add(product);
        Seller seller=new Seller(firstName, lastName, email, pseudo, number, products);
        int option =2;
        while (option==2) {
            System.out.println(seller);
            System.out.println("Confirmer vos informations: ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();
            option=getOption(1,2);
            if ( option== 1) {
                System.out.println("votre compte a ete cree avec succes");
            } else {
                System.out.println("Voulez vous modifier vos informations:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (getOption(1,2) == 1) {
                    System.out.println("Choisir information a modifier: ");
                        System.out.println("1. Prenom");
                        System.out.println("2. Nom");
                        System.out.println("3. Email");
                        System.out.println("4. Pseudo");
                        System.out.println("5. Number");

                        option = getOption(1,5);
                        scanner.nextLine();
                        switch (option) {
                            case 1:
                                seller.setFirstName(getUserStrInfo("Prenom"));
                                break;
                            case 2:
                                seller.setLastName(getUserStrInfo("Nom"));
                                break;
                            case 3:
                                seller.setEmail(getUserStrInfo("Email"));
                                break;
                            case 4:
                                seller.setPseudo(getUserStrInfo("Pseudo"));
                                break;
                            case 5:
                                seller.setNumber(getUserNumInfo("Numero"));
                                break;
                            default:
                                System.out.println("option doit etre compris entre 1 et 5");
                                break;
                        }
                        option=2;
                    }

            }
        }
        return seller;
    }

    private static Client getClientRegistrationInfo() {
        System.out.println("Saisissez vos informations");
        String firstName = getUserStrInfo("Prenom");
        String lastName = getUserStrInfo("Nom");
        String email = getUserStrInfo("Email");
        String pseudo = getUserStrInfo("pseudo");
        long number = getUserNumInfo("Numero");
        scanner.nextLine();
        String shipAddress = getUserStrInfo("Adresse de livraison");

        int option=2;
        Client client=new Client(firstName, lastName, email, pseudo, number, shipAddress);
        while (option==2) {
            System.out.println(client);
            System.out.println("Confirmer vos informations: ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();
            option=getOption(1,2);
            if (option== 1) {
                System.out.println("votre compte a ete cree avec succes");
            } else {
                System.out.println("Voulez vous modifier vos informations:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (getOption(1,2) == 1) {
                    System.out.println("Choisir information a modifier: ");
                    System.out.println("1. Prenom");
                    System.out.println("2. Nom");
                    System.out.println("3. Email");
                    System.out.println("4. Pseudo");
                    System.out.println("5. Number");

                    option = getOption(1,5);
                    scanner.nextLine();
                    switch (option) {
                        case 1:
                            client.setFirstName(getUserStrInfo("Prenom"));
                            break;
                        case 2:
                            client.setLastName(getUserStrInfo("Nom"));
                            break;
                        case 3:
                            client.setEmail(getUserStrInfo("Email"));
                            break;
                        case 4:
                            client.setPseudo(getUserStrInfo("Pseudo"));
                            break;
                        case 5:
                            client.setNumber(getUserNumInfo("Numero"));
                            break;
                        default:
                            System.out.println("option doit etre compris entre 1 et 5");
                            break;
                    }
                        option=2;
                }
            }
        }
        return client;
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
    private static int getOption(int lower, int upper) {
        int option = 0;
        boolean success = false;

        while (!success) {
            try {
                option = scanner.nextInt();

                // Check if the entered option is within the specified bounds
                if (option >= lower && option <= upper) {
                    success = true;
                } else {
                    System.err.println("Please enter a number between " + lower + " and " + upper + " (inclusive).");
                }
            } catch (InputMismatchException e) {
                // Handle the case where the user enters a non-integer
                System.err.println("Oops! Invalid option. Please enter a valid number.");
                scanner.next(); // Consume the invalid input to prevent an infinite loop
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

    private static int getUserNumInfo(String info,int lower, int upper) {
        System.out.print(info + ": ");
        int option = 0;
        boolean success = false;

        while (!success) {
            try {
                option = scanner.nextInt();

                // Check if the entered option is within the specified bounds
                if (option >= lower && option <= upper) {
                    success = true;
                } else {
                    System.err.println("Please enter a number between " + lower + " and " + upper + " (inclusive).");
                }
            } catch (InputMismatchException e) {
                // Handle the case where the user enters a non-integer
                System.err.println("Oops! Invalid option. Please enter a valid number.");
                scanner.next(); // Consume the invalid input to prevent an infinite loop
            }
        }

        return option;
    }
    private static int getUserNumInfo(String info,int lower) {
        System.out.print(info + ": ");
        int option = 0;
        boolean success = false;

        while (!success) {
            try {
                option = scanner.nextInt();

                // Check if the entered option is within the specified bounds
                if (option >= lower) {
                    success = true;
                } else {
                    System.err.println("Please enter a number larger than " + lower + " (inclusive).");
                }
            } catch (InputMismatchException e) {
                // Handle the case where the user enters a non-integer
                System.err.println("Oops! Invalid option. Please enter a valid number.");
                scanner.next(); // Consume the invalid input to prevent an infinite loop
            }
        }

        return option;
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
