import Data.Entities.Catalog;
import Data.Entities.Products.*;
import Data.Entities.ShoppingCart;
import Data.Entities.Type;
import Data.Entities.Users.Client;
import Data.Entities.Users.Seller;
import Data.Entities.Users.User;

import java.sql.SQLOutput;
import java.util.*;

public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<User> clients=new ArrayList<>();
    public static ArrayList<User> sellers=new ArrayList<>();
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
        //scanner.nextLine();

        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        Seller seller=new Seller(firstName, lastName, email, pseudo, number, products);

        modifySellerInfo(seller);

        return seller;
    }

    private static Client getClientRegistrationInfo() {
        System.out.println("Saisissez vos informations");
        String firstName = getUserStrInfo("Prenom");
        String lastName = getUserStrInfo("Nom");
        String email = getUserStrInfo("Email");
        String pseudo = getUserStrInfo("pseudo");

        while (isPseudoAlreadyUsed(pseudo, clients) || isPseudoAlreadyUsed(pseudo, sellers)) {
            System.out.println("Ce pseudo est déjà utilisé. Veuillez entrer un nouveau.");
            pseudo = getUserStrInfo("pseudo");
        }
        long number = getUserNumInfo("Numero");
        scanner.nextLine();
        String shipAddress = getUserStrInfo("Adresse de livraison");
        Client client=new Client(firstName, lastName, email, pseudo, number, shipAddress);

        modifyClientInfo(client);

        return client;
    }

    private static int getOption() {
        int option = 0;
        boolean success = false;
        while (!success) {
            try {
                option = scanner.nextInt();
                scanner.nextLine();
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

    private static String getUserStrInfo(String info) {
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
                    System.err.println("veuillez entrer un nombre plus grand que " + lower + " (inclusif) svp.");
                }
            } catch (InputMismatchException e) {
                // Handle the case where the user enters a non-integer
                System.err.println("Oops! option invale. veuillez entrer un chiffre valide svp.");
                scanner.next(); // Consume the invalid input to prevent an infinite loop
            }
        }

        return option;
    }

    static void getClientServiceInfo(Client user) {


        while (true) {
            System.out.println("Sélectionnez la tâche que vous voulez effectuer: ");
            System.out.println("1. Chercher un produit");
            System.out.println("2. Chercher un vendeur");
            System.out.println("3. Afficher le panier");
            System.out.println("4. Passer la commande");
            System.out.println("5. Modifier son profile");
            System.out.println("6. Quitter");

            int option = getOption(1,5);

            switch (option) {
                case 1:
                    boolean redo=true;
                    while(redo){
                        System.out.println("Liste des produits disponibles :");
                        for (Object[] objects : Catalog.catalogMap.values()) {
                            Product current=(Product) objects[0];
                            System.out.println(current);
                        }
                        System.out.println("Voulez-vous acheter un produit?");
                        System.out.println("1. Oui");
                        System.out.println("2. Non");
                        if(getOption(1,2)==1){
                            Product selectedProduct = findProductById();
                            user.getShoppingCart().add(selectedProduct);
                            System.out.println("Produit ajouté au panier.");
                            System.out.println("Voulez-vous ajouter un autre produit?");
                            System.out.println("1. Oui");
                            System.out.println("2. Non");
                            if(getOption(1,2)==2) redo=false;
                        }
                    }
                    break;

                case 2:
                    System.out.println("Liste des vendeurs :");
                    Seller current=findSellerById();
                    System.out.println("Voici la liste des produits de ce vendeur:");
                    for(Product product:current.getProducts()) System.out.println(product);
                    break;

                case 3:
                    System.out.println("Contenu du panier :");
                    System.out.println(user.getShoppingCart());
                    break;

                case 6:
                    System.out.println("Merci d'avoir utilisé notre service. Au revoir!");
                    break;
                case 5:
                    modifyClientInfo(user);
                    break;
                case 4:
                    System.out.println(user.getShoppingCart());
                    System.out.println("Voulez-vous acheter les items dans votre panier :");
                    System.out.println("1. Oui");
                    System.out.println("2. Non");
                    if(getOption(1,2)==1){
                        if(isShoppingCartItemsAvailable(user.getShoppingCart())){
                            System.out.println(user.buy(user.getShipAddress()));
                        }
                    }
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
            }
        }
    }
    private static boolean isShoppingCartItemsAvailable(ShoppingCart shoppingCart){
        for(Product product:shoppingCart.getCart().keySet()){
            if(product.getquantity()<shoppingCart.getCart().get(product)){
                System.out.println("Vous avez une quantité supérieure à celle disponible pour l'item suivant :");
                System.out.println(product);
                System.out.println("Quantité disponible : "+product.getquantity()+" Quantité dans le panier : "+shoppingCart.getCart().get(product));
            }
        }
        return true;
    }
    private static Product findProductById() {

        while (true) {
            System.out.println("Entrer le ID du produit: ");
            int productId = (int)getUserNumInfo("id");

            Object[] obj = Catalog.catalogMap.get(productId);
            if (obj != null) {
                return (Product) obj[0];
            } else {
                System.out.println("ID invalide. Veuillez reessayer svp.");
            }
        }
    }
    private static Seller findSellerById() {
        for (Object[] objects : Catalog.catalogMap.values()) {
            Seller current=(Seller) objects[1];
            System.out.println(current);
        }
        while (true) {
            System.out.println("Entrer le pseudo du vendeur:");
            String pseudo = getUserStrInfo("Pseudo");

            for (User seller : sellers) {
                if (seller.getPseudo().equalsIgnoreCase(pseudo)) {
                    return (Seller) seller;
                }
            }
            System.out.println("Pas de vendeur avec un tel pseudo. Veuillez reesayer.");
        }
    }
    private static void getSellerServiceInfo(Seller seller) {

        System.out.println("Selectionner la tache que voulez effectuer: ");
        System.out.println("1. Offrir un produit: ");
        System.out.println("2. Changer l'etat d'une commande: ");
        System.out.println("3. Modifier son profile");

        int option = getOption();
        switch (option) {
            case 1:
                getProductInfo();
                break;
            case 3 :
                modifySellerInfo(seller);
        }
    }

    private static void modifySellerInfo(Seller seller) {
        int option=2;
        while (option==2) {
            System.out.println(seller);
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
                            seller.setFirstName(getUserStrInfo("Prenom"));
                            break;
                        case 2:
                            seller.setLastName(getUserStrInfo("Nom"));
                            break;
                        case 3:
                            seller.setEmail(getUserStrInfo("Email"));
                            break;
                        case 4:
                            String pseudo = getUserStrInfo("pseudo");
                            while (isPseudoAlreadyUsed(pseudo, clients)|| isPseudoAlreadyUsed(pseudo, sellers)) {
                                System.out.println("Ce pseudo est déjà utilisé. Veuillez entrer un nouveau.");
                                pseudo = getUserStrInfo("pseudo");
                            }
                            seller.setPseudo(pseudo);
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
    }

    private static void modifyClientInfo(Client client) {
        int option=2;
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
                    System.out.println("6. Adresse de livraison");

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
                            String pseudo = getUserStrInfo("pseudo");
                            while (isPseudoAlreadyUsed(pseudo, clients)|| isPseudoAlreadyUsed(pseudo, sellers)) {
                                System.out.println("Ce pseudo est déjà utilisé. Veuillez entrer un nouveau.");
                                pseudo = getUserStrInfo("pseudo");
                            }
                            client.setPseudo(pseudo);
                            break;
                        case 5:
                            client.setNumber(getUserNumInfo("Numero"));
                            break;
                        case 6:
                            client.setShipAddress(getUserStrInfo("Adresse de livraison"));
                            break;
                        default:
                            System.out.println("option doit etre compris entre 1 et 5");
                            break;
                    }
                        option=2;
                }
            }
        }
    }

    private static boolean isPseudoAlreadyUsed(String pseudo, ArrayList<User> users) {
        return users.stream().anyMatch(user -> pseudo.equals(user.getPseudo()));
    }
    private static User login(String pseudo) {
        User user = null;
        while ((user = getUserByPseudo(pseudo, clients)) == null && (user = getUserByPseudo(pseudo, sellers)) == null) {
            System.out.println("Ce compte n'existe pas");
            pseudo = getUserStrInfo("Pseudo");
        }

        return user;
    }

    private static User getUserByPseudo(String pseudo, List<? extends User> users) {
        return users.stream()
                .filter(u -> pseudo.equals(u.getPseudo()))
                .findAny()
                .orElse(null);
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
                13,
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
                1,
                "ToolCo",
                "ScrewMaster 2000",
                "Power Tools"
        );



        ArrayList<Product> products1 = new ArrayList<>() ;
        products1.add(book1) ;
        products1.add(resource) ;
        Seller seller1 = new Seller(
                "Alice", "Johnson", "alice.johnson@example.com",
                "1", 1234167890L, products1);

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

        clients.addAll(Arrays.asList(client1, client2, client3, client4, client5));
        sellers.addAll(Arrays.asList(seller1, seller2, seller3, seller4, seller5));

        User user = null ;
        System.out.println("Choisissez une option");
        System.out.println("1. Se connecter");
        System.out.println("2. S'inscrire");
        if (getOption(1,2) == 1) {
            user=login(getUserStrInfo("Pseudo"));
        }
        else {
            user = getRegistrationStream() ;
        }
        System.out.println();
        System.out.println("##########################");
        System.out.println();
        if (user instanceof Client) getClientServiceInfo((Client) user);

    }

}
