package Controller;


import Data.Entities.*;
import Data.Entities.Products.Product;
import Data.Entities.Products.ProductType;
import Data.Entities.Users.Client;
import Data.Entities.Users.Seller;
import Data.Entities.Users.User;
import Service.UserInteractionService;

import java.util.*;
import java.util.stream.Collectors;
/**
 * Gère les opérations spécifiques aux vendeurs, telles que la confirmation de la réception d'un retour,
 * la gestion des produits, la modification du profil, etc.
 */
public class SellerManager {
    public UserInteractionService input;
    private ProductManager productManager;
    private ClientManager clientManager;
    private List<Seller> sellers;
    private List<Client> clients;
    /**
     * Constructeur de la classe SellerManager.
     *
     * @param sellers Liste des vendeurs.
     */
    public SellerManager(List<Seller> sellers) {
        this.sellers = sellers;
        this.input = new UserInteractionService();
    }
    /**
     * Confirme la réception d'un article retourné par un client.
     *
     * @param returnItem L'article retourné.
     * @param seller Le vendeur qui confirme la réception.
     */
    public void confirmReturnReception(OrderItem returnItem, Seller seller) {
        // need to wipe orderItem/returnItem from both seller and client once confirmation
        // what if need full audit of returItems/OrderItems ?
        returnItem.setDelivered(true);
        Product currentProduct = seller.getProducts().stream().filter(curr -> curr.getId() == returnItem.getProductId()).findAny().orElse(null);
        if (!returnItem.getReason().equals("produit défectueux")) {
            currentProduct.AddQuantity(returnItem.getQuantity());

            Product product = Catalog.getProduct(returnItem.getProductId());
            if (product == null) {
                Catalog.catalogMap.put(currentProduct.getId(), new Object[]{currentProduct, seller});
            } else {
                Object[] obj = Catalog.catalogMap.get(product.getId());
                ((Product) obj[0]).AddQuantity(returnItem.getQuantity());
            }
        }
        clientManager.getClient(returnItem.getClientPseudo()).removePoints((int) currentProduct.getPoints());
    }

    public List<Seller> getSellers() {
        return sellers;
    }

    public void setSellers(ArrayList<Seller> sellers) {
        this.sellers = sellers;
    }

    public void setClientManager(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }
    /**
     * Vérifie si un pseudonyme est déjà utilisé par un client ou un vendeur.
     *
     * @param pseudo Le pseudonyme à vérifier.
     * @return True si le pseudonyme est déjà utilisé, false sinon.
     */
    public boolean isPseudoAlreadyUsed(String pseudo) {
        for (Client client : clientManager.getClients()) {
            if (client.getPseudo().equals(pseudo)) return true;
        }
        return sellers.stream().anyMatch(user -> pseudo.equals(user.getPseudo()));
    }
    /**
     * Recherche des vendeurs par leur nom.
     *
     * @param name Le nom à rechercher parmi les vendeurs.
     * @return Une liste des vendeurs correspondant au nom spécifié.
     */
    public List<Seller> findSellersByName(String name) {
        return this.sellers.stream().filter(user -> name.equals(user.getFirstName())).toList();
    }


    public Seller getSeller(String pseudo) {
        return sellers.stream().filter(seller -> pseudo.equals(seller.getPseudo())).findAny().orElse(null);
    }
    /**
     * Recherche des vendeurs qui proposent des produits du type spécifié.
     *
     * @param type Le type de produit à rechercher parmi les vendeurs.
     * @return Une liste des vendeurs proposant des produits du type spécifié.
     */
    public List<Seller> findSellersByProductType(ProductType type) {
        return this.sellers.stream().filter(seller -> seller.getProducts().stream().anyMatch(product -> product.getCategory().equals(type))).collect(Collectors.toList());
    }

    /**
     * Obtient les informations de service pour un vendeur.
     *
     * @param seller Le vendeur pour lequel obtenir les informations de service.
     * @return True si l'utilisateur souhaite répéter, false sinon.
     */
    public boolean getSellerServiceInfo(Seller seller) {

        boolean repeat = true;
        while (repeat) {
            System.out.println("Selectionner la tache que vous voulez effectuer: ");
            System.out.println("1. Offrir un produit: ");
            System.out.println("2. Changer l'etat d'une commande: ");
            System.out.println("3. Modifier son profile");
            System.out.println("4. Confirmer la reception d'un retour");
            System.out.println("5. Voir les évaluations de mes produits");
            System.out.println("6. Voir les tickets en attente");
            System.out.println("7. Voir ses métriques");
            System.out.println("8. Chercher un produit");
            System.out.println("9. Chercher un vendeur");
            System.out.println("10. Chercher un acheteur");

            System.out.println("11. Quitter");
            int option = input.getOption(1, 11);
            switch (option) {
                case 1:
                    Product product = null;
                    product = productManager.getProductInfo();
                    seller.addProduct(product);
                    break;
                case 2:
                    while (true) {
                        System.out.println("Voici l'état de vos commandes en cours");
                        System.out.println("En production :");
                        ArrayList<OrderItem> inProd = seller.getInProduction();
                        for (int i = 0; i < inProd.size(); i++) {
                            System.out.println(i + ". " + inProd.get(i));
                        }
                        System.out.println("En livraison :");
                        ArrayList<OrderItem> inShipping = seller.getInShipping();
                        for (int i = 0; i < inShipping.size(); i++) {
                            System.out.println(i + ". " + inShipping.get(i));
                        }
                        System.out.println("Voulez-vous expédier un item?");
                        System.out.println("1. Oui");
                        System.out.println("2. Non");

                        if (input.getOption(1, 2) == 1 && !inProd.isEmpty()) {
                            System.out.println("Entrer le # de l'item que vous voulez expédier");
                            int choice = input.getOption(0, inProd.size() - 1);
                            inProd.get(choice).setShipped(true);
                            System.out.println("Confirmation de l'expédition du produit");
                        } else if (inProd.isEmpty()) {
                            System.out.println("Vous n'avez aucun item en production");
                            break;
                        } else {
                            break;
                        }
                    }

                    break;
                case 3:
                    boolean redo = true;
                    while (redo) {
                        modifySellerInfo(seller);
                        System.out.println(seller);
                        System.out.println("Confirmer vos informations: ");
                        System.out.println("1. oui");
                        System.out.println("2. non");
                        System.out.println();
                        option = input.getOption(1, 2);
                        if (option == 1) {
                            redo = false;
                            System.out.println("votre compte a ete modifie avec succes");
                        }
                    }
                case 4:
                    System.out.println("Liste des retours");
                    ArrayList<OrderItem> returnItems = seller.getReturnItems();
                    if (returnItems.isEmpty()) {
                        System.out.println("Vous n'avez aucun retour à confirmer");
                        break;
                    }
                    for (int i = 0; i < returnItems.size(); i++) {
                        System.out.println("#" + i + " " + returnItems.get(i));
                    }
                    System.out.println("Entrer le # du produit dont vous voulez confirmer le retour");
                    int index = input.getUserNumInfo("index", 0, returnItems.size()-1);
                    OrderItem returnItem = returnItems.get(index);
                    if (returnItem.isDelivered()) {
                        System.out.println("Ce retour est déjà confirmé");
                        break;
                    }
                    confirmReturnReception(returnItem, seller);
                    System.out.println("Retour confirmé avec succès");
                    break;
                case 5:
                    System.out.println("Voici les reviews de vos produits");
                    for (Product sellerProduct : seller.getProducts()) {
                        System.out.println("Review de " + sellerProduct.getTitle());
                        System.out.println(sellerProduct.getEvaluations());
                    }
                    break;
                case 6:
                    ArrayList<Ticket> tickets = seller.getTickets();
                    if (tickets.isEmpty()) System.out.println("vous n'avez aucun ticket en attente!");
                    else {
                        System.out.println("Voici vos tickets:");
                        for (int i = 0; i < tickets.size(); i++) {
                            System.out.println("#" + i + " " + tickets.get(i));
                        }
                        System.out.println("Voulez-vous prendre action sur un ticket?");
                        System.out.println("1. Oui");
                        System.out.println("2. Non");
                        if (input.getOption(1, 2) == 1) {
                            System.out.println("Quel est le # du ticket?");
                            Ticket currTicket = tickets.get(input.getOption(0, tickets.size() - 1));
                            System.out.println("Choisissez l'une des options suivantes:");
                            System.out.println("1. Proposer une solution");
                            System.out.println("2. Confirmer l'expédition d'un produit de remplacement");
                            System.out.println("3. Confirmer la réceptiojn du produit défectueux");
                            int choice = input.getOption(1, 3);
                            if (choice == 1) {
                                if (currTicket.getTrackingNumber() != null) {
                                    System.out.println("Veuillez entrer la description de la solution proposé");
                                    currTicket.setSolutionDescription(input.getUserStrInfo("solution"));
                                    currTicket.setTrackingNumber(UUID.randomUUID().toString());
                                    currTicket.setReplacementRequestDate(Calendar.getInstance().getTime().toString());
                                    System.out.println("Confirmation de la création de la demande de retour.");
                                } else System.out.println("Vous avez déjà proposé une solution");
                            } else if (choice == 2) {
                                if (!currTicket.isDeliveryConfirmationBySeller()) {
                                    if (currTicket.isSellerConfirmationOfReturnProductReception()) {
                                        currTicket.setDeliveryConfirmationBySeller(true);
                                        currTicket.setReplacementTrackingNumber(UUID.randomUUID().toString());
                                        System.out.println("Confirmation de l'expédition du produit de remplacement");
                                    } else
                                        System.out.println("Vous n'avez pas confirmé la réception du produit défectueux encore");
                                } else System.out.println("Vous avez déja confirmé l'expédition");
                            } else if (choice == 3) {
                                if (currTicket.isBuyerConfirmationOfReturnProductExpedition()) {
                                    if (!currTicket.isSellerConfirmationOfReturnProductReception()) {
                                        currTicket.setSellerConfirmationOfReturnProductReception(true);
                                        System.out.println("Confirmation de la réception du produit défectueux");
                                    } else System.out.println("Vous avez déjà confirmé la réception de ce produit");
                                } else System.out.println("L'acheteur n'a pas encore expédié le produit défectueux");
                            }
                        }
                    }
                    break;
                case 7:
                    System.out.println("Voici vos métriques:");
                    System.out.println("Votre nombre de ticket est: " + seller.getTickets().size());
                    System.out.println("Votre nombre de produits affichés: " + seller.getProducts().size());
                    System.out.println("Votre nombre de ventes effectués: " + seller.getOrderItems().size());
                    break;
                case 8:
                    redo = true;
                    while (redo) {

                        System.out.println("Choisissez votre option de filtre:");
                        System.out.println("1. Catégorie");
                        System.out.println("2. Prix");
                        System.out.println("3. Marque");
                        System.out.println("4. Titre");
                        System.out.println("5. Modèle");
                        System.out.println("6. Notes");
                        System.out.println("7. Aucune");

                        int filterOption = input.getOption(1, 7);
                        List<Product> products = new ArrayList<>();
                        switch (filterOption) {
                            case 1:
                                boolean redoChoice = true;
                                while (redoChoice) {
                                    System.out.println("Entrer la categorie de produit :");
                                    System.out.println("1. Livres et Manuels");
                                    System.out.println("2. Ressource d'apprentissage");
                                    System.out.println("3. Article de papeterie");
                                    System.out.println("4. Materiel informatique");
                                    System.out.println("5. Equipement de bureau");
                                    int choice = input.getOption(1, 5);
                                    switch (choice) {
                                        case 1:
                                            products = productManager.findProductsByCategory(ProductType.Book);
                                            if (products.isEmpty()) {
                                                System.out.println("Aucun produit dans la catégorie " + ProductType.Book + ". Veuillez reessayer svp");
                                            } else redoChoice = false;
                                            break;
                                        case 2:
                                            products = productManager.findProductsByCategory(ProductType.LearningResource);
                                            if (products.isEmpty()) {
                                                System.out.println("Aucun produit dans la catégorie " + ProductType.LearningResource + ". Veuillez reessayer svp");
                                            } else redoChoice = false;
                                            break;
                                        case 3:
                                            products = productManager.findProductsByCategory(ProductType.Article);
                                            if (products.isEmpty()) {
                                                System.out.println("Aucun produit dans la catégorie " + ProductType.Article + ". Veuillez reessayer svp");
                                            } else redoChoice = false;
                                            break;
                                        case 4:
                                            products = productManager.findProductsByCategory(ProductType.Hardware);
                                            if (products.isEmpty()) {
                                                System.out.println("Aucun produit dans la catégorie " + ProductType.Hardware + ". Veuillez reessayer svp");
                                            } else redoChoice = false;
                                            break;
                                        case 5:
                                            products = productManager.findProductsByCategory(ProductType.DesktopTool);
                                            if (products.isEmpty()) {
                                                System.out.println("Aucun produit dans la catégorie " + ProductType.DesktopTool + ". Veuillez reessayer svp");
                                            } else redoChoice = false;
                                            break;
                                    }
                                }
                                for (Product productSeller : products) System.out.println(productSeller);
                                break;
                            case 2:
                                System.out.println("Specifier les prix max et min");
                                int minPrice = input.getUserNumInfo("Prix min", 1, Integer.MAX_VALUE);
                                int maxPrice = input.getUserNumInfo("Prix max", 1, Integer.MAX_VALUE);
                                products = productManager.findProductsByPrice(minPrice, maxPrice);
                                while (products.isEmpty()) {
                                    System.out.println("Produits avec prix min: " + minPrice + " et prix max: " + maxPrice + " indisponible. Veuillez reessayer svp");
                                    minPrice = input.getUserNumInfo("Prix min", 1, Integer.MAX_VALUE);
                                    maxPrice = input.getUserNumInfo("Prix max", 1, Integer.MAX_VALUE);
                                    products = productManager.findProductsByPrice(minPrice, maxPrice);
                                }
                                for (Product productSeller : products) System.out.println(productSeller);
                                break;
                            case 4:
                                System.out.println("Entrer le titre du produit");
                                String title = input.getUserStrInfo("Titre");
                                products = productManager.findProductsByTitle(title);
                                while (products.isEmpty()) {
                                    System.out.println("Produits avec titre: " + title + " indisponible. Veuillez reessayer svp");
                                    title = input.getUserStrInfo("Titre");
                                    products = productManager.findProductsByTitle(title);
                                }
                                for (Product productSeller : products) System.out.println(productSeller);
                                break;
                            case 3:
                                System.out.println("Entrer la marque du produit");
                                String brand = input.getUserStrInfo("Marque");
                                products = productManager.findProductsByBrand(brand);
                                while (products.isEmpty()) {
                                    System.out.println("Produits avec marque: " + brand + " indisponible. Veuillez reessayer svp");
                                    brand = input.getUserStrInfo("Marque");
                                    products = productManager.findProductsByBrand(brand);
                                }
                                for (Product productSeller : products) System.out.println(productSeller);
                                break;
                            case 5:
                                System.out.println("Entrer le modèle du produit");
                                String model = input.getUserStrInfo("Modèle");
                                products = productManager.findProductsByModel(model);
                                while (products.isEmpty()) {
                                    System.out.println("Produits avec modèle: " + model + " indisponible.Veuillez reessayer svp");
                                    model = input.getUserStrInfo("Modèle");
                                    products = productManager.findProductsByModel(model);
                                }
                                for (Product productSeller : products) System.out.println(productSeller);
                                break;
                            case 6:
                                PriorityQueue<Product> pq = new PriorityQueue<Product>((a, b) -> b.getLikes().size() - a.getLikes().size());
                                for (Product productSeller : Catalog.catalogMap.values().stream().map(obj -> (Product) obj[0]).toList()) {
                                    if (!productSeller.getLikes().isEmpty()) pq.add(productSeller);
                                }
                                for (Product productSeller : pq) {
                                    System.out.println(productSeller);
                                }
                                break;
                            case 7:
                                System.out.println("Liste des produits disponibles :");
                                for (Product productSeller : Catalog.catalogMap.values().stream().map(obj -> (Product) obj[0]).toList()) {
                                    System.out.println(productSeller);
                                }
                                break;
                        }
                        break;
                    }

                case 9:
                    System.out.println("Choisissez votre option de filtre:");
                    System.out.println("1. Nom");
                    System.out.println("2. Adresse");
                    System.out.println("3. Type de produit");
                    System.out.println("4. Aucune");

                    int filterOption = input.getOption(1, 4);
                    List<Seller> sellers = new ArrayList<>();
                    SellerManager sellerManager = null;
                    switch (filterOption) {
                        case 1:
                            System.out.println("Entrer le nom du vendeur");
                            String name = input.getUserStrInfo("Nom");
                            sellers = sellerManager.findSellersByName(name);
                            while (sellers.isEmpty()) {
                                System.out.println("Vendeurs nommés: " + name + " indisponible. Veuillez reessayer svp");
                                name = input.getUserStrInfo("Nom");
                                sellers = sellerManager.findSellersByName(name);
                            }
                            for (Seller seller1 : sellers) System.out.println(seller1);
                            break;
                        case 2:

                            break;
                        case 3:
                            boolean redoChoice = true;
                            while (redoChoice) {
                                System.out.println("Entrer la catégorie du produit vendu par le vendeur");
                                System.out.println("1. Livres et Manuels");
                                System.out.println("2. Ressource d'apprentissage");
                                System.out.println("3. Article de papeterie");
                                System.out.println("4. Materiel informatique");
                                System.out.println("5. Equipement de bureau");
                                int choice = input.getOption(1, 5);
                                switch (choice) {
                                    case 1:
                                        sellers = sellerManager.findSellersByProductType(ProductType.Book);
                                        if (sellers.isEmpty()) {
                                            System.out.println("Vendeurs avec categorie de produit : " + ProductType.Book + " indisponible. Veuillez reessayer svp");
                                        } else redoChoice = false;
                                        break;
                                    case 2:
                                        sellers = sellerManager.findSellersByProductType(ProductType.LearningResource);
                                        if (sellers.isEmpty()) {
                                            System.out.println("Vendeurs avec categorie de produit : " + ProductType.LearningResource + " indisponible. Veuillez reessayer svp");
                                        } else redoChoice = false;
                                        break;
                                    case 3:
                                        sellers = sellerManager.findSellersByProductType(ProductType.Article);
                                        if (sellers.isEmpty()) {
                                            System.out.println("Vendeurs avec categorie de produit : " + ProductType.Article + " indisponible. Veuillez reessayer svp");
                                        } else redoChoice = false;
                                        break;
                                    case 4:
                                        sellers = sellerManager.findSellersByProductType(ProductType.Hardware);
                                        if (sellers.isEmpty()) {
                                            System.out.println("Vendeurs avec categorie de produit : " + ProductType.Hardware + " indisponible. Veuillez reessayer svp");
                                        } else redoChoice = false;
                                        break;
                                    case 5:
                                        sellers = sellerManager.findSellersByProductType(ProductType.DesktopTool);
                                        if (sellers.isEmpty()) {
                                            System.out.println("Vendeurs avec categorie de produit : " + ProductType.DesktopTool + " indisponible. Veuillez reessayer svp");
                                        } else redoChoice = false;
                                        break;
                                }
                            }

                            for (Seller seller1 : sellers) System.out.println(seller1);
                            break;
                        case 4:
                            System.out.println("Liste des vendeurs disponibles");
                            for (Seller seller1 : sellerManager.getSellers()) {
                                System.out.println(seller1);
                            }
                            break;
                    }
                    System.out.println("Voulez-vous voir le profil d'un vendeur?");
                    System.out.println("1. oui");
                    System.out.println("2. non");
                    if (input.getOption(1, 2) == 1) {
                        System.out.println("Entrer le pseudo du vendeur");
                        String pseudo = input.getUserStrInfo("Pseudo");
                        Seller seller1 = sellerManager.getSeller(pseudo);
                        while (seller1 == null) {
                            System.out.println("Pseudo invalide. veuillez reessayer svp");
                            pseudo = input.getUserStrInfo("Pseudo");
                            seller1 = sellerManager.getSeller(pseudo);
                        }
                        System.out.println(seller1);
                        System.out.println("Liste des produits vendus par " + seller1.getFirstName() + ":");
                        for (Product product1 : seller.getProducts()) System.out.println(product1);
                        break;
                    }

            case 10:
                    System.out.println("Choisissez votre option de filtre:");
                    System.out.println("1. Pseudo");
                    System.out.println("2. Parmi la liste des suiveurs d'un acheteur");
                    System.out.println("3. Aucune");
                    filterOption = input.getOption(1, 3);
                    switch (filterOption) {
                        case 1:
                            String recherche = input.getUserStrInfo("Chaine de character à rechercher");
                            ArrayList<Client> clientTrouve = new ArrayList<>();
                            for (Client client : clients) {
                                if (client.getPseudo().toLowerCase().contains(recherche.toLowerCase())) {
                                    clientTrouve.add(client);
                                }
                            }
                            for (Client client : clientTrouve) System.out.println(client);
                            break;
                        case 2:
                            for (Client acheteur : clients) System.out.println(acheteur);
                            System.out.println("Choisir vous voulez voir les suiveurs de quel acheteur");
                            String pseudo = input.getUserStrInfo("Pseudo");
                            Client client = null;
                            while ((client = (Client) getUserByPseudo(pseudo)) == null ) {
                                System.out.println("Ce compte n'existe pas");
                                pseudo = input.getUserStrInfo("Pseudo");
                            }
                            System.out.println("Voici les suiveurs de " + pseudo);
                            for (String suiveur : client.getFollowers()) {
                                System.out.println(getUserByPseudo(suiveur));
                            }
                            break;
                        case 3:
                            for (Client acheteur : clients) System.out.println(acheteur);
                            break;
                    }
                    case 11:
                    System.out.println("Merci d'avoir utilisé notre service. Au revoir!");
                    repeat = false;
                    return repeat;

        }}

        return !repeat;
    }


    /**
     * Modifie les informations du vendeur en fonction de l'option choisie.
     *
     * @param seller Le vendeur dont les informations doivent être modifiées.
     */
    public void modifySellerInfo(Seller seller) {
        System.out.println("Choisir information a modifier: ");
        System.out.println("1. Prenom");
        System.out.println("2. Nom");
        System.out.println("3. Email");
        System.out.println("4. Pseudo");
        System.out.println("5. Numero");
        System.out.println("6. Mot de passe");

        int option = input.getOption(1, 6);

        switch (option) {
            case 1:
                seller.setFirstName(input.getUserStrInfo("Prenom"));
                break;
            case 2:
                seller.setLastName(input.getUserStrInfo("Nom"));
                break;
            case 3:
                String email = input.getUserStrInfo("Email");
                while (isEmailAlreadyUsed(email)) {
                    System.out.println("Cet email est déjà utilisé. Veuillez entrer un nouveau.");
                    email = input.getUserStrInfo("email");
                }
                seller.setEmail(email);
                break;
            case 4:
                String pseudo = input.getUserStrInfo("pseudo");
                while (isPseudoAlreadyUsed(pseudo)) {
                    System.out.println("Ce pseudo est déjà utilisé. Veuillez entrer un nouveau.");
                    pseudo = input.getUserStrInfo("pseudo");
                }
                seller.setPseudo(pseudo);
                break;
            case 5:
                seller.setNumber(input.getUserNumInfo("Numero", 1, Integer.MAX_VALUE));
                break;
            case 6:
                String password = input.getUserStrInfo("Mot de passe");
                while (isPasswordAlreadyUsed(password)) {
                    System.out.println("Ce mot de passe est déjà utilisé. Veuillez entrer un nouveau.");
                    password = input.getUserStrInfo("Mot de passe");
                }
                seller.setPseudo(password);
                break;
            default:
                System.out.println("option doit etre compris entre 1 et 5");
                break;
        }
    }
    /**
     * Récupère un utilisateur (Seller) par son pseudo.
     *
     * @param pseudo Le pseudo de l'utilisateur à récupérer.
     * @return L'utilisateur correspondant au pseudo, ou null s'il n'existe pas.
     */
    public User getUserByPseudo(String pseudo) {
        return sellers.stream().filter(u -> pseudo.equals(u.getPseudo())).findAny().orElse(null);
    }
    /**
     * Obtient les informations d'inscription d'un vendeur, y compris la création d'un produit.
     *
     * @return Le vendeur nouvellement créé.
     */
    public Seller getSellerRegistrationInfo() {
        System.out.println("Vous devez offrir au moins un produit à vendre au prealable");
        System.out.println("1. Offrir un produit à vendre:");
        Product product = null;

        if (input.getOption(1, 1) == 1) {
            product = productManager.getProductInfo();
        }
        System.out.println("Saisissez vos informations");

        String firstName = input.getUserStrInfo("Prenom");
        String lastName = input.getUserStrInfo("Nom");
        String email = input.getUserStrInfo("Email");

        while (isEmailAlreadyUsed(email)) {
            System.out.println("Cet email est déjà utilisé. Veuillez entrer un nouveau.");
            email = input.getUserStrInfo("Email");
        }

        String pseudo = input.getUserStrInfo("pseudo");

        while (isPseudoAlreadyUsed(pseudo)) {
            System.out.println("Ce pseudo est déjà utilisé. Veuillez entrer un nouveau.");
            pseudo = input.getUserStrInfo("pseudo");
        }

        long number = input.getUserNumInfo("Numero", 1, Integer.MAX_VALUE);

        String password = input.getUserStrInfo("Mot de passe");
        while (isPasswordAlreadyUsed(password)) {
            System.out.println("Ce mot de passe est déjà utilisé. Veuillez entrer un nouveau.");
            password = input.getUserStrInfo("Mot de passe");
        }
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        Seller seller = new Seller(firstName, lastName, email, pseudo, number, products, password);

        int option = 2;
        while (option == 2) {
            System.out.println(seller);
            System.out.println("Confirmer vos informations: ");
            System.out.println("1. oui");
            System.out.println("2. non");
            System.out.println();
            option = input.getOption(1, 2);
            if (option == 1) {
                System.out.println("votre compte a ete cree avec succes");
            } else {
                System.out.println("Voulez vous modifier vos informations:  ");
                System.out.println("1. oui");
                System.out.println("2. non");
                System.out.println();

                if (input.getOption(1, 2) == 1) {
                    modifySellerInfo(seller);
                }
                option = 2;
            }
        }
        return seller;
    }
    /**
     * Vérifie si l'adresse e-mail est déjà utilisée par un client ou un vendeur.
     *
     * @param email L'adresse e-mail à vérifier.
     * @return true si l'adresse e-mail est déjà utilisée, sinon false.
     */
    public boolean isEmailAlreadyUsed(String email) {
        for (Client client : clientManager.getClients()) {
            if (client.getEmail().equals(email)) return true;
        }
        return sellers.stream().anyMatch(user -> email.equals(user.getEmail()));
    }
    /**
     * Vérifie si le mot de passe est déjà utilisé par un client ou un vendeur.
     *
     * @param password Le mot de passe à vérifier.
     * @return true si le mot de passe est déjà utilisé, sinon false.
     */
    public boolean isPasswordAlreadyUsed(String password) {
        for (Client client : clientManager.getClients()) {
            if (client.getPassword().equals(password)) return true;
        }
        return sellers.stream().anyMatch(user -> password.equals(user.getPassword()));
    }
    /**
     * Met à jour les articles de commande du vendeur en ajoutant les articles de la commande spécifiée.
     *
     * @param order La commande dont les articles doivent être ajoutés au vendeur.
     */
    public void updateSellerOrderItems(Order order) {
        for (OrderItem item : order.getItems()) {
            Seller seller = Catalog.getProductSeller(item.getProductId());
            seller.addOrderItem(item);
        }
    }

    public Seller getSellerFromName(String name) {
        for (Seller seller : sellers) if (seller.getFirstName().equals(name)) return seller;
        return null;
    }
    public Seller getSellerFromAdress(String Adress) {
        for (Seller seller : sellers) if (seller.getEmail().equals(Adress)) return seller;
        return null;
    }
    public Seller getSellerFromProduct(ProductType category) {
         for (Seller seller : sellers)
             for (Product product : seller.getProducts())
                if (product.getCategory().equals(category)) return seller;
        return null;
    }
}