package Controller;
import Data.Entities.Catalog;
import Data.Entities.Products.*;
import Data.Entities.Type;
import Service.UserInteractionService;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * La classe ProductManager gère les opérations liées aux produits tels que la recherche, la création et la validation.
 */
public class ProductManager {
    /** Service d'interaction utilisateur pour la saisie des données. */
    UserInteractionService input = new UserInteractionService();

    /**
     * Vérifie la disponibilité d'un identifiant de produit.
     *
     * @return Un identifiant de produit disponible.
     */

    public int isIdAvailable() {

        while (true) {
            System.out.println("Entrer le ID du produit: ");
            int productId = input.getUserNumInfo("id", 1, Integer.MAX_VALUE);

            if (Catalog.catalogMap.containsKey(productId)) {
                return productId;
            } else {
                System.out.println("ID invalide. Veuillez reessayer svp.");
            }
        }
    }
    /**
     * Recherche des produits par titre.
     *
     * @param title Le titre à rechercher.
     * @return Une liste de produits correspondant au titre.
     */
    public List<Product> findProductsByTitle(String title) {
        return Catalog.catalogMap.values().stream().filter(obj -> title.equals(((Product) obj[0]).getTitle())).map(obj -> (Product) obj[0]).toList() ;
    }
    /**
     * Recherche des produits par prix.
     *
     * @param minPrice Prix minimum.
     * @param maxPrice Prix maximum.
     * @return Une liste de produits dans la plage de prix spécifiée.
     */
    public List<Product> findProductsByPrice(int minPrice, int maxPrice) {
        return Catalog.catalogMap.values().stream().filter(obj -> ((Product) obj[0]).getPrice() >= minPrice && ((Product) obj[0]).getPrice() <= maxPrice).map(obj -> (Product) obj[0]).toList() ;
    }
    /**
     * Recherche des produits par marque.
     *
     * @param brand La marque à rechercher.
     * @return Une liste de produits de la marque spécifiée.
     */
    public List<Product> findProductsByBrand(String brand) {
        return Catalog.catalogMap.values().stream().filter(obj -> brand.equals(((Product) obj[0]).getBrand())).map(obj -> (Product) obj[0]).toList() ;
    }
    /**
     * Recherche des produits par modèle.
     *
     * @param model Le modèle à rechercher.
     * @return Une liste de produits du modèle spécifié.
     */
    public List<Product> findProductsByModel(String model) {
        return Catalog.catalogMap.values().stream().filter(obj -> model.equals(((Product) obj[0]).getModel())).map(obj -> (Product) obj[0]).toList() ;
    }

    /**
     * Recherche des produits par catégorie.
     *
     * @param type La catégorie de produit à rechercher.
     * @return Une liste de produits de la catégorie spécifiée.
     */
    public List<Product> findProductsByCategory(ProductType type) {
        return Catalog.catalogMap.values().stream().filter(obj -> ((Product) obj[0]).getCategory() == type).map(obj -> (Product) obj[0]).toList() ;
    }
    /**
     * Obtient les informations d'un nouveau produit en demandant à l'utilisateur de choisir une catégorie.
     *
     * @return Le nouveau produit créé en fonction de la catégorie choisie par l'utilisateur.
     */
    public Product getProductInfo() {
        System.out.println("Choisissez une categorie de produit a vendre:");
        System.out.println("1. Livres et Manuels");
        System.out.println("2. Ressource d'apprentissage");
        System.out.println("3. Article de papeterie");
        System.out.println("4. Materiel informatique");
        System.out.println("5. Equipement de bureau");
        Product product = null;

        int option = input.getOption(1, 5);
        product = switch (option) {
            case 1 -> getBookInfo();
            case 2 -> getLearningResourceInfo();
            case 3 -> getArticleInfo();
            case 4 -> getMaterialInfo();
            case 5 -> getEquipmentInfo();
            default -> product;
        };
        return product;
    }

    /**
     * Collecte les informations d'un livre ou manuel à ajouter au catalogue.
     *
     * @return Une instance de la classe Book représentant le livre ou manuel avec les informations fournies.
     */
    public Product getBookInfo() {
        System.out.println("Donnez les informations du Livre/Manuel: ");
        String title = input.getUserStrInfo("Titre");
        String desc = input.getUserStrInfo("Description");
        String author = input.getUserStrInfo("Auteur");
        String isbn = input.getUserStrInfo("ISBN");
        String editor = input.getUserStrInfo("Maison d'edition");

        System.out.println("possede numero d'edition: ");
        System.out.println("1. oui");
        System.out.println("2. non");

        long editionNum = -1;
        if (input.getOption(1, 2) == 1) {
            editionNum = input.getUserNumInfo("Numero d'edition", 1, Integer.MAX_VALUE);
        }

        String genre = input.getUserStrInfo("genre");
        String pubDate = input.getUserStrInfo("date de parution (DD/MM/YYYY)");

        System.out.println("possede numero de volume");
        System.out.println("1. oui");
        System.out.println("2. non");

        long volNum = -1;
        if (input.getOption(1, 2) == 1) {
            volNum = input.getUserNumInfo("Numero de volume", 1, Integer.MAX_VALUE);
        }

        long initQuantity = input.getUserNumInfo("Quantite", 1, Integer.MAX_VALUE);

        System.out.println("offrir des points bonus pour le produit:");
        System.out.println("1. oui");
        System.out.println("2. non");

        long points = 1;
        if (input.getOption(1, 2) == 1) {
            points = input.getUserNumInfo("bonus/$", 1, 20);
        }
        double price = input.getUserNumInfo("Prix", 1, Integer.MAX_VALUE);
        Book product = new Book(title, desc, Calendar.getInstance().getTime().toString(), initQuantity, price, points, isbn, author, editor, genre, pubDate, editionNum, volNum,new ArrayList<>(),new ArrayList<>());
        int option = 2;
        while (option == 2) {
            System.out.println(product);

            System.out.println("Valider les informations du produit ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();
            option = input.getOption(1, 2);
            if (option == 1) {
                System.out.println("produit ajoutes avec succes");
            } else {
                System.out.println("Voulez vous modifier les informations du produit:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (input.getOption(1, 2) == 1) {
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

                    option = input.getOption(1, 12);

                    if (option == 1) {
                        product.setTitle(input.getUserStrInfo("Title"));
                    } else if (option == 2) {
                        product.setDesc(input.getUserStrInfo("Description"));
                    } else if (option == 3) {
                        product.setAuthor(input.getUserStrInfo("Auteur"));
                    } else if (option == 4) {
                        product.setEditor(input.getUserStrInfo("Maison d'edition"));
                    } else if (option == 5) {
                        product.setEditorNum(input.getUserNumInfo("Numero d'edition", 1, Integer.MAX_VALUE));
                    } else if (option == 6) {
                        product.setGenre(input.getUserStrInfo("Genre"));
                    } else if (option == 7) {
                        product.setPubDate(input.getUserStrInfo("date de parution (DD/MM/YYYY)"));
                    } else if (option == 8) {
                        product.setVolNum(input.getUserNumInfo("Numero de volume", 1, Integer.MAX_VALUE));
                    } else if (option == 9) {
                        product.setquantity(input.getUserNumInfo("Quantite", 1, Integer.MAX_VALUE));
                    } else if (option == 10) {
                        product.setPoints(input.getUserNumInfo("bonus/$", 1, 20));
                    } else if (option == 11) {
                        product.setPrice(input.getUserNumInfo("Prix", 1, Integer.MAX_VALUE));
                    } else if (option == 12) {
                        product.setISBN(input.getUserStrInfo("ISBN"));
                    }
                    option = 2;
                }
            }
        }

        return product;

    }

    public LearningResource getLearningResourceInfo() {
        System.out.println("Donnez les informations du Livre/Manuel: ");
        String title = input.getUserStrInfo("Titre");
        String desc = input.getUserStrInfo("Description");
        String author = input.getUserStrInfo("Auteur");
        String isbn = input.getUserStrInfo("ISBN");
        String org = input.getUserStrInfo("Organisation");

        System.out.println("possede numero d'edition: ");
        System.out.println("1. oui");
        System.out.println("2. non");

        long editionNum = -1;
        if (input.getOption(1, 2) == 1) {
            editionNum = input.getUserNumInfo("Numero d'edition", 1, Integer.MAX_VALUE);

        }

        String pubDate = input.getUserStrInfo("date de parution (DD/MM/YYYY)");

        System.out.println("Type: ");
        System.out.println("1. imprime");
        System.out.println("2. electronique");

        Type type = null;
        if (input.getOption(1, 2) == 1) {
            type = Type.printed;
        } else {
            type = Type.electronic;
        }

        long initQuantity = input.getUserNumInfo("Quantite", 1, Integer.MAX_VALUE);

        System.out.println("offrir des points bonus pour le produit:");
        System.out.println("1. oui");
        System.out.println("2. non");
        long points = 1;
        if (input.getOption(1, 2) == 1) {
            points = input.getUserNumInfo("bonus/$", 1, 20);
        }

        double price = input.getUserNumInfo("Prix", 1, Integer.MAX_VALUE);

        LearningResource product = new LearningResource(title, desc, Calendar.getInstance().getTime().toString(), initQuantity, price, points, isbn, author, org, pubDate, type, editionNum,new ArrayList<>(),new ArrayList<>());

        int option = 2;
        while (option == 2) {
            System.out.println(product);
            System.out.println("Valider les informations: du produit ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();
            option = input.getOption(1, 2);
            if (option == 1) {
                System.out.println("produit ajoutes avec succes");
            } else {
                System.out.println("Voulez vous modifier les informations du produit:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (input.getOption(1, 2) == 1) {
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

                    option = input.getOption(1, 11);

                    if (option == 1) {
                        product.setTitle(input.getUserStrInfo("Title"));
                    } else if (option == 2) {
                        product.setDesc(input.getUserStrInfo("Description"));
                    } else if (option == 3) {
                        product.setAuteur(author);
                    } else if (option == 4) {
                        product.setOrganisation(input.getUserStrInfo("Organisation"));
                    } else if (option == 5) {
                        product.setEditionNumber((long) input.getUserNumInfo("Numero d'edition", 1, Integer.MAX_VALUE));
                    } else if (option == 6) {
                        product.setPublishDate(input.getUserStrInfo("date de parution (DD/MM/YYYY)"));
                    } else if (option == 7) {
                        System.out.println("Type: ");
                        System.out.println("1. imprime");
                        System.out.println("2. electronique");
                        if (input.getOption(1, 2) == 1) {
                            type = Type.printed;
                        } else {
                            type = Type.electronic;
                        }
                        product.setType(type);
                    } else if (option == 8) {
                        product.setquantity(input.getUserNumInfo("Quantite", 1, Integer.MAX_VALUE));
                    } else if (option == 9) {
                        points = input.getUserNumInfo("bonus/$", 1, 20);
                        product.setPoints(points);
                    } else if (option == 10) {
                        product.setPrice(input.getUserNumInfo("Price", 1, Integer.MAX_VALUE));
                    } else if (option == 11) {

                        product.setISBN(input.getUserStrInfo("ISBN"));
                    } else {
                        System.out.println("Option invalide");
                    }
                    option = 2;
                }

            }
        }
        return product;
    }
    /**
     * Obtient les informations d'un nouvel article en demandant à l'utilisateur de fournir les détails nécessaires.
     *
     * @return Le nouvel article créé avec les informations fournies par l'utilisateur.
     */
    public Product getArticleInfo() {
        System.out.println("Donnez les informations de l'Article: ");
        String title = input.getUserStrInfo("Titre");
        String desc = input.getUserStrInfo("Description");
        String brand = input.getUserStrInfo("Marque");
        String model = input.getUserStrInfo("Model");
        String subCategory = input.getUserStrInfo("Sous-catégorie exemple: cahier, crayon, surligneur:");

        long initQuantity = input.getUserNumInfo("Quantite", 1, Integer.MAX_VALUE);

        System.out.println("offrir des points bonus pour le produit:");
        System.out.println("1. oui");
        System.out.println("2. non");

        long points = 1;
        if (input.getOption(1, 2) == 1) {
            points = input.getUserNumInfo("bonus/$", 1, 20);
        }
        double price = input.getUserNumInfo("Prix", 1, Integer.MAX_VALUE);
        Article product = new Article(title, desc, Calendar.getInstance().getTime().toString(), initQuantity, price, points, brand, model, subCategory,new ArrayList<>(),new ArrayList<>());

        int option = 2;
        while (option == 2) {
            System.out.println(product);
            System.out.println("Validez-vous les informations du produit ?");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();
            option = input.getOption(1, 2);
            if (option == 1) {
                System.out.println("produit ajoutes avec succes");
            } else {
                System.out.println("Voulez vous modifier les informations du produit:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (input.getOption(1, 2) == 1) {
                    System.out.println("Choisir information a modifier: ");
                    System.out.println("1. Titre");
                    System.out.println("2. Description");
                    System.out.println("3. Marque");
                    System.out.println("4. Model");
                    System.out.println("5. sous-categorie");
                    System.out.println("6. Quantite");
                    System.out.println("7. bonus/$: ");
                    System.out.println("8. Price: ");

                    option = input.getOption(1, 8);

                    if (option == 1) {
                        product.setTitle(input.getUserStrInfo("Title"));
                    } else if (option == 2) {
                        product.setDesc(input.getUserStrInfo("Description"));
                    } else if (option == 3) {
                        product.setBrand(input.getUserStrInfo("Marque"));
                    } else if (option == 4) {
                        product.setBrand(input.getUserStrInfo("Modèle"));
                    } else if (option == 5) {
                        product.setSubCategory(input.getUserStrInfo("sous-categorie"));
                    } else if (option == 6) {
                        product.setquantity(input.getUserNumInfo("Quantite", 1, Integer.MAX_VALUE));
                    } else if (option == 7) {
                        product.setPoints(input.getUserNumInfo("bonus/$", 1, 20));
                    } else if (option == 8) {
                        product.setPrice(input.getUserNumInfo("Prix", 1, Integer.MAX_VALUE));
                    }
                }
                option = 2;
            }
        }

        return product;

    }
    /**
     * Obtient les informations d'un nouveau matériel en demandant à l'utilisateur de fournir les détails nécessaires.
     *
     * @return Le nouveau matériel créé avec les informations fournies par l'utilisateur.
     */
    public Product getMaterialInfo() {
        System.out.println("Donnez les informations du materiel: ");
        String title = input.getUserStrInfo("Titre");
        String desc = input.getUserStrInfo("Description");
        String brand = input.getUserStrInfo("Marque");
        String model = input.getUserStrInfo("Model");
        String subCategory = input.getUserStrInfo("Sous-catégorie exemple: ordinateur, souris, clavier, disque dur externe");
        String launchDate = input.getUserStrInfo("date de lancement (DD/MM/YYYY)");
        long initQuantity = input.getUserNumInfo("Quantite", 1, Integer.MAX_VALUE);

        System.out.println("offrir des points bonus pour le produit:");
        System.out.println("1. oui");
        System.out.println("2. non");

        long points = 1;
        if (input.getOption(1, 2) == 1) {
            points = input.getUserNumInfo("bonus/$", 1, 20);
        }
        double price = input.getUserNumInfo("Prix", 1, Integer.MAX_VALUE);
        Hardware product = new Hardware(title, desc, Calendar.getInstance().getTime().toString(), initQuantity, price, points, brand, model, launchDate, subCategory,new ArrayList<>(),new ArrayList<>());
        int option = 2;
        while (option == 2) {
            System.out.println(product);
            System.out.println("Valider les informations du produit ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();
            option = input.getOption(1, 2);
            if (option == 1) {
                System.out.println("produit ajoutes avec succes");
            } else {
                System.out.println("Voulez vous modifier les informations du produit:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (input.getOption(1, 2) == 1) {
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

                    option = input.getOption(1, 9);
                    if (option == 1) {
                        product.setTitle(input.getUserStrInfo("Title"));
                    } else if (option == 2) {
                        product.setDesc(input.getUserStrInfo("Description"));
                    } else if (option == 3) {
                        product.setBrand(input.getUserStrInfo("Marque"));
                    } else if (option == 4) {
                        product.setModel(input.getUserStrInfo("Modèle"));
                    } else if (option == 5) {
                        product.setSubCategory(input.getUserStrInfo("sous-categorie"));
                    } else if (option == 6) {
                        product.setLaunchDate(input.getUserStrInfo("Date de lancement"));
                    } else if (option == 7) {
                        product.setquantity(input.getUserNumInfo("Quantite", 1, Integer.MAX_VALUE));
                    } else if (option == 8) {
                        product.setPoints(input.getUserNumInfo("bonus/$", 1, 20));
                    } else if (option == 9) {
                        product.setPrice(input.getUserNumInfo("Prix", 1, Integer.MAX_VALUE));
                    }
                    option = 2;
                }
            }
        }

        return product;
    }
    /**
     * Obtient les informations d'un nouvel équipement en demandant à l'utilisateur de fournir les détails nécessaires.
     *
     * @return Le nouvel équipement créé avec les informations fournies par l'utilisateur.
     */
    public Product getEquipmentInfo() {
        System.out.println("Donnez les informations de l'equipement");
        String title = input.getUserStrInfo("Titre");
        String desc = input.getUserStrInfo("Description");
        String brand = input.getUserStrInfo("Marque");
        String model = input.getUserStrInfo("Model");
        String subCategory = input.getUserStrInfo("Sous-catégorie exemple: table, chaise, lampe:");

        long initQuantity = input.getUserNumInfo("Quantite", 1, Integer.MAX_VALUE);

        System.out.println("offrir des points bonus pour le produit:");
        System.out.println("1. oui");
        System.out.println("2. non");

        long points = 1;
        if (input.getOption(1, 2) == 1) {
            points = input.getUserNumInfo("bonus/$", 1, 20);
        }

        double price = input.getUserNumInfo("Prix", 1, Integer.MAX_VALUE);
        DesktopTool product = new DesktopTool(title, desc, Calendar.getInstance().getTime().toString(), initQuantity, price, points, brand, model, subCategory,new ArrayList<>(),new ArrayList<>());

        int option = 2;
        while (option == 2) {
            System.out.println(product);

            System.out.println("Valider les informations du produit ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();
            option = input.getOption(1, 2);
            if (option == 1) {
                System.out.println("produit ajoutes avec succes");
            } else {
                System.out.println("Voulez vous modifier les informations du produit:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (input.getOption(1, 2) == 1) {
                    System.out.println("Choisir information a modifier: ");
                    System.out.println("1. Titre");
                    System.out.println("2. Description");
                    System.out.println("3. Marque");
                    System.out.println("4. Model");
                    System.out.println("5. sous-categorie");
                    System.out.println("5. Quantite");
                    System.out.println("6. bonus/$: ");
                    System.out.println("7. Price: ");
                    option = input.getOption(1, 7);
                    if (option == 1) {
                        product.setTitle(input.getUserStrInfo("Title"));
                    } else if (option == 2) {
                        product.setDesc(input.getUserStrInfo("Description"));
                    } else if (option == 3) {
                        product.setBrand(input.getUserStrInfo("Marque"));
                    } else if (option == 4) {
                        product.setModel(input.getUserStrInfo("Modèle"));
                    } else if (option == 5) {
                        product.setSubCategory(input.getUserStrInfo("sous-categorie"));
                    } else if (option == 6) {
                        product.setquantity(input.getUserNumInfo("Quantite", 1, Integer.MAX_VALUE));
                    } else if (option == 7) {
                        product.setPoints(input.getUserNumInfo("bonus/$", 1, 20));
                    } else if (option == 8) {
                        product.setPrice(input.getUserNumInfo("Prix", 1, Integer.MAX_VALUE));
                    }
                    option = 2;
                }
            }
        }

        return product;
    }

}
