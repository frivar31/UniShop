package Controller;

import Data.Entities.*;
import Data.Entities.Products.Product;
import Data.Entities.Products.ProductType;
import Data.Entities.Users.Client;
import Data.Entities.Users.Seller;
import Service.UserInteractionService;

import java.util.*;
import java.util.stream.Collectors;

public class ClientManager {
    public UserInteractionService input;
    private SellerManager sellerManager;
    private ProductManager productManager;
    private List<Client> clients;

    public ClientManager(List<Client> clients) {
        this.clients = clients;
        this.input = new UserInteractionService();
    }



    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void setSellerManager(SellerManager sellerManager) {
        this.sellerManager = sellerManager;
    }

    public void setProductManager(ProductManager productManager) {
        this.productManager = productManager;
    }

    public void modifyClientInfo(Client client) {

        System.out.println("Choisir information a modifier: ");
        System.out.println("1. Prenom");
        System.out.println("2. Nom");
        System.out.println("3. Email");
        System.out.println("4. Pseudo");
        System.out.println("5. Numero");
        System.out.println("6. Adresse de livraison");
        System.out.println("7. Mot de passe");

        int option = input.getOption(1, 7);

        switch (option) {
            case 1:
                client.setFirstName(input.getUserStrInfo("Prenom"));
                break;
            case 2:
                client.setLastName(input.getUserStrInfo("Nom"));
                break;
            case 3:
                String email = input.getUserStrInfo("Email");
                while (isEmailAlreadyUsed(email)) {
                    System.out.println("Cet email est déjà utilisé. Veuillez entrer un nouveau.");
                    email = input.getUserStrInfo("Email");
                }
                client.setEmail(email);
                break;
            case 4:
                String pseudo = input.getUserStrInfo("Pseudo");
                while (isPseudoAlreadyUsed(pseudo)) {
                    System.out.println("Ce pseudo est déjà utilisé. Veuillez entrer un nouveau.");
                    pseudo = input.getUserStrInfo("Pseudo");
                }
                client.setPseudo(pseudo);
                break;
            case 5:
                client.setNumber(input.getUserNumInfo("Numero", 1, Integer.MAX_VALUE));
                break;
            case 6:
                client.setShipAddress(input.getUserStrInfo("Adresse de livraison"));
                break;
            case 7:
                String password = input.getUserStrInfo("Mot de passe");
                while (isPasswordAlreadyUsed(password)) {
                    System.out.println("Ce mot de passe est déjà utilisé. Veuillez entrer un nouveau.");
                    password = input.getUserStrInfo("Mot de passe");
                }
                client.setPseudo(password);
                break;
            default:
                System.out.println("option doit etre compris entre 1 et 5");
                break;
        }
    }

    public boolean isPseudoAlreadyUsed(String pseudo) {
        for (Seller seller : sellerManager.getSellers()) {
            if(seller.getPseudo().equals(pseudo)) return true ;
        }
        return clients.stream().anyMatch(user -> pseudo.equals(user.getPseudo()));
    }

    public boolean isEmailAlreadyUsed(String email) {
        for (Seller seller : sellerManager.getSellers()) {
            if(seller.getEmail().equals(email)) return true ;
        }
        return clients.stream().anyMatch(user -> email.equals(user.getEmail())) ;
    }

    public boolean principalMenu(Client user) {

        boolean repeat = true ;
        while (repeat) {
            System.out.println("Sélectionnez la tâche que vous voulez effectuer: ");
            System.out.println("1. Chercher un produit");
            System.out.println("2. Chercher un vendeur");
            System.out.println("3. Afficher le panier");
            System.out.println("4. Passer la commande");
            System.out.println("5. Editer les items du panier");
            System.out.println("6. Modifier son profile");
            System.out.println("7. Chercher un acheteur");
            System.out.println("8. Consulter mon total de points");
            System.out.println("9. Consulter les produits aimés par les gens que je suis");
            System.out.println("10. Gérer ses commandes");
            System.out.println("11. Quitter");


            int option = input.getOption(1, 11);

            switch (option) {
                case 1:
                    boolean redo = true;
                    while (redo) {

                        System.out.println("Choisissez votre option de filtre:");
                        System.out.println("1. Catégorie");
                        System.out.println("2. Prix") ;
                        System.out.println("3. Marque");
                        System.out.println("4. Titre");
                        System.out.println("5. Modèle");
                        System.out.println("6. Notes");
                        System.out.println("7. Aucune");

                        int filterOption = input.getOption(1,7);
                        List<Product> products = new ArrayList<>() ;
                        switch (filterOption){
                            case 1 :
                                boolean redoChoice = true ;
                                while(redoChoice) {
                                    System.out.println("Entrer la categorie de produit :");
                                    System.out.println("1. Livres et Manuels");
                                    System.out.println("2. Ressource d'apprentissage");
                                    System.out.println("3. Article de papeterie");
                                    System.out.println("4. Materiel informatique");
                                    System.out.println("5. Equipement de bureau");
                                    int choice = input.getOption(1,5) ;
                                    switch (choice) {
                                        case 1:
                                            products = productManager.findProductsByCategory(ProductType.Book) ;
                                            if(products.isEmpty()) {
                                                System.out.println("Aucun produit dans la catégorie "+ProductType.Book+". Veuillez reessayer svp");
                                            }
                                            else redoChoice = false ;
                                            break;
                                        case 2:
                                            products = productManager.findProductsByCategory(ProductType.LearningResource) ;
                                            if(products.isEmpty()) {
                                                System.out.println("Aucun produit dans la catégorie "+ProductType.LearningResource+". Veuillez reessayer svp");
                                            }
                                            else redoChoice = false ;
                                            break;
                                        case 3:
                                            products = productManager.findProductsByCategory(ProductType.Article) ;
                                            if(products.isEmpty()) {
                                                System.out.println("Aucun produit dans la catégorie "+ProductType.Article+". Veuillez reessayer svp");
                                            }
                                            else redoChoice = false ;
                                            break;
                                        case 4:
                                            products = productManager.findProductsByCategory(ProductType.Hardware) ;
                                            if(products.isEmpty()) {
                                                System.out.println("Aucun produit dans la catégorie "+ProductType.Hardware+". Veuillez reessayer svp");
                                            }
                                            else redoChoice = false ;
                                            break;
                                        case 5:
                                            products = productManager.findProductsByCategory(ProductType.DesktopTool) ;
                                            if(products.isEmpty()) {
                                                System.out.println("Aucun produit dans la catégorie "+ProductType.DesktopTool+". Veuillez reessayer svp");
                                            }
                                            else redoChoice = false ;
                                            break;
                                    }
                                }
                                for (Product product : products) System.out.println(product);
                                break ;
                            case 2 :
                                System.out.println("Specifier les prix max et min");
                                int minPrice = input.getUserNumInfo("Prix min",1,Integer.MAX_VALUE) ;
                                int maxPrice = input.getUserNumInfo("Prix max",1,Integer.MAX_VALUE) ;
                                products = productManager.findProductsByPrice(minPrice,maxPrice) ;
                                while(products.isEmpty()) {
                                    System.out.println("Produits avec prix min: "+minPrice+" et prix max: "+maxPrice+" indisponible. Veuillez reessayer svp");
                                    minPrice = input.getUserNumInfo("Prix min",1,Integer.MAX_VALUE) ;
                                    maxPrice = input.getUserNumInfo("Prix max",1,Integer.MAX_VALUE) ;
                                    products = productManager.findProductsByPrice(minPrice,maxPrice) ;
                                }
                                for (Product product : products) System.out.println(product);
                                break ;
                            case 4 :
                                System.out.println("Entrer le titre du produit");
                                String title = input.getUserStrInfo("Titre") ;
                                products = productManager.findProductsByTitle(title) ;
                                while (products.isEmpty()) {
                                    System.out.println("Produits avec titre: "+title+" indisponible. Veuillez reessayer svp");
                                    title = input.getUserStrInfo("Titre") ;
                                    products = productManager.findProductsByTitle(title) ;
                                }
                                for (Product product : products) System.out.println(product);
                                break ;
                            case 3 :
                                System.out.println("Entrer la marque du produit");
                                String brand = input.getUserStrInfo("Marque") ;
                                products = productManager.findProductsByBrand(brand) ;
                                while (products.isEmpty()) {
                                    System.out.println("Produits avec marque: "+brand+" indisponible. Veuillez reessayer svp");
                                    brand = input.getUserStrInfo("Marque") ;
                                    products = productManager.findProductsByBrand(brand) ;
                                }
                                for (Product product : products) System.out.println(product);
                                break ;
                            case 5 :
                                System.out.println("Entrer le modèle du produit");
                                String model = input.getUserStrInfo("Modèle") ;
                                products = productManager.findProductsByModel(model) ;
                                while (products.isEmpty()) {
                                    System.out.println("Produits avec modèle: "+model+" indisponible.Veuillez reessayer svp");
                                    model = input.getUserStrInfo("Modèle") ;
                                    products = productManager.findProductsByModel(model) ;
                                }
                                for (Product product : products) System.out.println(product);
                                break ;
                            case 6:
                                PriorityQueue<Product> pq=new PriorityQueue<Product>((a,b)-> b.getLikes().size()-a.getLikes().size());
                                for (Product product : Catalog.catalogMap.values().stream().map(obj -> (Product) obj[0]).toList()) {
                                    if(!product.getLikes().isEmpty()) pq.add(product);
                                }
                                for(Product product:pq){
                                    System.out.println(product);
                                }
                                break;
                            case 7:
                                System.out.println("Liste des produits disponibles :");
                                for (Product product : Catalog.catalogMap.values().stream().map(obj -> (Product) obj[0]).toList()) {
                                    System.out.println(product);
                                }
                                break ;
                        }
                        System.out.println("Choisissez une des options suivantes");
                        System.out.println("1. Acheter un produit");
                        System.out.println("2. Voir les commentaires et note d'un produit");
                        System.out.println("3. Liker un produit");
                        System.out.println("4. Retour au menu principal");
                        int choice=input.getOption(1,4);
                        if (choice == 1) {
                            user.getShoppingCart().add(productManager.isIdAvailable());
                            System.out.println("Produit ajouté au panier.");
                            System.out.println("Voulez-vous ajouter un autre produit?");
                            System.out.println("1. Oui");
                            System.out.println("2. Non");
                            if (input.getOption(1, 2) == 2) redo = false;
                        }
                        else if(choice==2){
                            Product product=Catalog.getProduct(productManager.isIdAvailable());
                            for(ProductEvaluation productEvaluation:product.getEvaluations()){
                                System.out.println(productEvaluation);
                            }
                            System.out.println("Note moyenne de "+ product.averageRating()+"/5");
                        }
                        else if(choice==3){
                            Product product=Catalog.getProduct(productManager.isIdAvailable());
                            if(!product.getLikes().contains(user.getPseudo())){
                                product.addLikes(user.getPseudo());
                                user.addLikedProduct(product.getId());
                                System.out.println("Item liké, merci");
                            }
                            else System.out.println("Vous avez déja liké ce produit");
                        }
                        else redo = false;
                    }
                    break;

                case 2:

                    System.out.println("Choisissez votre option de filtre:");
                    System.out.println("1. Nom");
                    System.out.println("2. Adresse") ;
                    System.out.println("3. Type de produit");
                    System.out.println("4. Aucune");

                    int filterOption = input.getOption(1,4) ;
                    List<Seller> sellers = new ArrayList<>() ;
                    switch(filterOption) {
                        case 1:
                            System.out.println("Entrer le nom du vendeur");
                            String name = input.getUserStrInfo("Nom") ;
                            sellers = sellerManager.findSellersByName(name) ;
                            while (sellers.isEmpty()) {
                                System.out.println("Vendeurs nommés: "+name+" indisponible. Veuillez reessayer svp");
                                name = input.getUserStrInfo("Nom");
                                sellers = sellerManager.findSellersByName(name) ;
                            }
                            for (Seller seller : sellers) System.out.println(seller);
                            break ;
                        case 2:
                            break;
                        case 3:
                            boolean redoChoice = true ;
                            while(redoChoice) {
                                System.out.println("Entrer la catégorie du produit vendu par le vendeur");
                                System.out.println("1. Livres et Manuels");
                                System.out.println("2. Ressource d'apprentissage");
                                System.out.println("3. Article de papeterie");
                                System.out.println("4. Materiel informatique");
                                System.out.println("5. Equipement de bureau");
                                int choice = input.getOption(1,5) ;
                                switch (choice) {
                                    case 1:
                                        sellers = sellerManager.findSellersByProductType(ProductType.Book) ;
                                        if(sellers.isEmpty()) {
                                            System.out.println("Vendeurs avec categorie de produit : "+ProductType.Book+" indisponible. Veuillez reessayer svp");
                                        }
                                        else redoChoice = false ;
                                        break;
                                    case 2:
                                        sellers = sellerManager.findSellersByProductType(ProductType.LearningResource) ;
                                        if(sellers.isEmpty()) {
                                            System.out.println("Vendeurs avec categorie de produit : "+ProductType.LearningResource+" indisponible. Veuillez reessayer svp");
                                        }
                                        else redoChoice = false ;
                                        break;
                                    case 3:
                                        sellers = sellerManager.findSellersByProductType(ProductType.Article) ;
                                        if(sellers.isEmpty()) {
                                            System.out.println("Vendeurs avec categorie de produit : "+ProductType.Article+" indisponible. Veuillez reessayer svp");
                                        }
                                        else redoChoice = false ;
                                        break;
                                    case 4:
                                        sellers = sellerManager.findSellersByProductType(ProductType.Hardware) ;
                                        if(sellers.isEmpty()) {
                                            System.out.println("Vendeurs avec categorie de produit : "+ProductType.Hardware+" indisponible. Veuillez reessayer svp");
                                        }
                                        else redoChoice = false ;
                                        break;
                                    case 5:
                                        sellers = sellerManager.findSellersByProductType(ProductType.DesktopTool) ;
                                        if(sellers.isEmpty()) {
                                            System.out.println("Vendeurs avec categorie de produit : "+ProductType.DesktopTool+" indisponible. Veuillez reessayer svp");
                                        }
                                        else redoChoice = false ;
                                        break;
                                }
                            }

                            for (Seller seller : sellers) System.out.println(seller);
                            break ;
                        case 4:
                            System.out.println("Liste des vendeurs disponibles") ;
                            for (Seller seller : sellerManager.getSellers()) {
                                System.out.println(seller);
                            }
                            break ;
                    }
                    System.out.println("Voulez-vous voir le profil d'un vendeur?");
                    System.out.println("1. oui");
                    System.out.println("2. non");
                    if (input.getOption(1,2) == 1) {
                        System.out.println("Entrer le pseudo du vendeur");
                        String pseudo = input.getUserStrInfo("Pseudo") ;
                        Seller seller = sellerManager.getSeller(pseudo) ;
                        while (seller == null) {
                            System.out.println("Pseudo invalide. veuillez reessayer svp");
                            pseudo = input.getUserStrInfo("Pseudo") ;
                            seller = sellerManager.getSeller(pseudo) ;
                        }
                        System.out.println(seller);
                        System.out.println("Liste des produits vendus par "+seller.getFirstName()+":");
                        for (Product product : seller.getProducts()) System.out.println(product);
                        System.out.println("Voulez-vous acheter un produit de "+seller.getFirstName()+" ?");
                        System.out.println("1. Oui");
                        System.out.println("2. Non");
                        if (input.getOption(1, 2) == 1) {
                            user.getShoppingCart().add(productManager.isIdAvailable());
                            System.out.println("Produit ajouté au panier.");
                        }
                    }
                    break ;
                case 3:
                    System.out.println("Contenu du panier :");
                    System.out.println(user.getShoppingCart());
                    break;
                case 4:
                    if (user.getShoppingCart().getNumberItems() == 0) {
                        System.out.println("Votre panier est vide");
                        break;
                    }
                    System.out.println(user.getShoppingCart());
                    System.out.println("Voulez-vous acheter les items dans votre panier :");
                    System.out.println("1. Oui");
                    System.out.println("2. Non");
                    if (input.getOption(1, 2) == 1) {
                        if (isShoppingCartItemsAvailable(user.getShoppingCart())) {
                            System.out.println(buy(user.getShipAddress(),user));
                        }
                    }
                    break;
                case 5:
                    if (user.getShoppingCart().getNumberItems() == 0) {
                        System.out.println("Votre panier est vide");
                        break;
                    }
                    System.out.println(user.getShoppingCart());
                    System.out.println("Entrer le id du produit que vous voulez editer");
                    int id = input.getOption(1, Integer.MAX_VALUE);
                    while (!user.getShoppingCart().containsItem(id)) {
                        System.out.println("Cette id n'est pas dans votre panier, rentrez un nouveau");
                        id = input.getUserNumInfo("Id", 1, Integer.MAX_VALUE);
                    }
                    Product product = Catalog.getProduct(id);
                    System.out.println(user.getShoppingCart().toString(product,id));
                    System.out.println("Voulez-vous retirer ou ajuster la quantité de l'item");
                    System.out.println("1. Retirer");
                    System.out.println("2. Editer");
                    if (input.getOption(1, 2) == 1) {
                        user.getShoppingCart().deleteProduct(id);
                        System.out.println("item supprimé");
                        System.out.println(user.getShoppingCart());
                    } else {
                        System.out.println("Rentrer la quantité désirée :");
                        int quantity = input.getUserNumInfo("Quantité", 1, Integer.MAX_VALUE);
                        user.getShoppingCart().updateQuantity(id, quantity);
                        System.out.println(user.getShoppingCart());
                        System.out.println("Quantité ajusté");
                    }
                    System.out.println();
                    break;
                case 6:
                    modifyClientInfo(user);
                    break;
                case 7:
                    System.out.println("Choisissez votre option de filtre:");
                    System.out.println("1. Pseudo");
                    System.out.println("2. Parmi la liste des suiveurs d'un acheteur");
                    System.out.println("3. Aucune");
                    filterOption = input.getOption(1,3) ;
                    switch (filterOption){
                        case 1:
                            String recherche=input.getUserStrInfo("Chaine de character à rechercher");
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
                            while ((client = getUserByPseudo(pseudo)) == null || client == user) {
                                System.out.println("Ce compte n'existe pas");
                                pseudo = input.getUserStrInfo("Pseudo");
                            }
                            System.out.println("Voici les suiveurs de "+pseudo);
                            for(String suiveur:client.getFollowers()){
                                System.out.println(getUserByPseudo(suiveur));
                            }
                            break;
                        case 3:
                            for (Client acheteur : clients) System.out.println(acheteur);
                            break;
                    }
                    System.out.println("Choisissez une option");
                    System.out.println("Voulez-vous suivre, arrêter de suivre ou revenir au menu principal");
                    System.out.println("1. Suivre");
                    System.out.println("2. Arrêter de suivre");
                    System.out.println("3. Revenir au menu");
                    int choice = input.getOption(1, 3);
                    if (choice == 1) {
                        System.out.println("Entrer le pseudo de la personne que vous voulez suivre:");
                        String pseudo = input.getUserStrInfo("Pseudo");
                        Client toFollow = null;
                        while ((toFollow = getUserByPseudo(pseudo)) == null || toFollow == user) {
                            System.out.println("Ce compte n'existe pas");
                            pseudo = input.getUserStrInfo("Pseudo");
                        }
                        followClient(user, toFollow);
                    } else if (choice == 2) {
                        System.out.println("Vous suivez actuellement :");
                        for(String following:user.getFollowing()) System.out.println(getUserByPseudo(following));
                        System.out.println("Entrer le pseudo de la personne que vous voulez arrêter de suivre:");
                        String pseudo = input.getUserStrInfo("Pseudo");
                        Client toFollow = null;
                        while ((toFollow = getUserByPseudo(pseudo)) == null || toFollow == user) {
                            System.out.println("Ce compte n'existe pas");
                            pseudo = input.getUserStrInfo("Pseudo");
                        }
                        unfollowClient(user, toFollow);
                    }
                    break;
                case 8:
                    System.out.println(user.getPoints() + " points");
                    break;
                case 9:
                    displayLikedProductsByFollowing(user);
                    break;
                case 10:
                    for(Order order:user.getOrders().values()){
                        System.out.println(order);
                    }
                    System.out.println("Voulez-vous voir une commande en détail?");
                    System.out.println("1. Oui");
                    System.out.println("2. Non");
                    choice=input.getOption(1,2);
                    if(choice==1){
                        System.out.println("Entrer le numéro de la commande");
                        String orderNumber = input.getUserStrInfo("#");
                        Order order = null;
                        while ((order = getOrderFromNumber(orderNumber,user)) == null) {
                            System.out.println("Ce numéro de commande n'existe pas");
                            orderNumber = input.getUserStrInfo("#");
                        }
                        for (OrderItem orderItem:order.getItems()) {
                            System.out.println(orderItem);
                        }
                        System.out.println("Choisissez une option");
                        System.out.println("1. Confirmer la réception d'une commande");
                        System.out.println("2. Évaluer un produit");
                        System.out.println("3. Retourner un produit");
                        System.out.println("4. Echanger un produit");
                        choice=input.getOption(1,3);
                        switch(choice){
                            case 1:
                                if(user.getOrder(orderNumber).isShipped()){
                                    if(!user.getOrder(orderNumber).isDelivered()){
                                        System.out.println("Vous confirmez la réception de la commande "+orderNumber);
                                        System.out.println("1. oui");
                                        System.out.println("2. non");
                                        if(input.getOption(1,2)==1){
                                            user.getOrder(orderNumber).setDelivered(true);
                                            user.getOrder(orderNumber).setDeliveryDate(Calendar.getInstance().getTime());
                                            System.out.println("Confirmation de réception confirmé");
                                        }
                                    }
                                    else System.out.println("Vous avez déja confirmé la réception de cette commande");
                                }
                                else System.out.println("Votre commande n'a pas été livré encore");
                                break;
                            case 2:
                                if(!user.getOrder(orderNumber).isDelivered()) {
                                    System.out.println("Vous n'avez pas confirmer la reception de cette commande");
                                    System.out.println("Voulez vous confirmer la reception de la commande ?");
                                    System.out.println("1. oui");
                                    System.out.println("2. non") ;
                                    if(input.getOption(1,2) == 1) {
                                        user.getOrder(orderNumber).setDelivered(true);
                                        user.getOrder(orderNumber).setDeliveryDate(Calendar.getInstance().getTime());
                                        System.out.println("Confirmation de réception confirmé");
                                    }
                                    else break ;
                                }
                                System.out.println("Entrer le numéro de l'item");
                                int productId = input.getOption(0);
                                product = null;
                                while ((product = getProductInOrder(order,productId)) == null) {
                                    System.out.println("Cette item n'est pas dans la commande");
                                    productId = input.getOption(0);
                                }
                                if(product.getEvaluations().stream().anyMatch(e -> e.getPseudoOp().equals(user.getPseudo()))){
                                    System.out.println("Vous avez déjà évalué ce produit.");
                                    break;
                                }
                                System.out.println("Veuillez entrer votre évalution de 1 à 5 étoiles pour le produit");
                                int nbetoiles=input.getOption(1,5);
                                System.out.println("Veuillez entrer votre commentaire sur le produit");
                                String comment=input.getUserStrInfo("Commentaire");
                                ProductEvaluation productEvaluation=new ProductEvaluation(nbetoiles,comment,user.getPseudo(),productId);
                                user.addEvaluation(productEvaluation);
                                product.addEvaluation(productEvaluation);
                                System.out.println("Évaluation ajouté merci!");
                                break;
                            case 3:
                                if(!user.getOrder(orderNumber).isDelivered()) {
                                    System.out.println("Vous n'avez pas confirmer la reception de cette commande");
                                    System.out.println("Voulez vous confirmer la reception de la commande ?");
                                    System.out.println("1. oui");
                                    System.out.println("2. non") ;
                                    if(input.getOption(1,2) == 1) {
                                        user.getOrder(orderNumber).setDelivered(true);
                                        user.getOrder(orderNumber).setDeliveryDate(Calendar.getInstance().getTime());
                                        System.out.println("Confirmation de réception confirmé");
                                    }
                                    else break ;
                                }
                                if(!user.getOrder(orderNumber).isReturnable()) {
                                    System.out.println("La période de retour pour les articles est limitée à 30 jours à partir de la date de la commande.");
                                    break ;
                                }
                                boolean rep = true ;
                                ArrayList<OrderItem> returnItems = new ArrayList<>() ;
                                while(rep) {
                                    System.out.println("Saisir le id du produit que vous voulez retourner");
                                    productId = input.getUserNumInfo("Id",0,Integer.MAX_VALUE) ;
                                    OrderItem orderItem = user.getOrder(orderNumber).getItem(productId) ;
                                    while(orderItem == null) {
                                        System.out.println("Aucune de vos commandes ne contient ce produit. veuillez reessayer svp");
                                        productId = input.getUserNumInfo("Id",0,Integer.MAX_VALUE) ;
                                        orderItem = user.getOrder(orderNumber).getItem(productId) ;
                                    }
                                    if(orderItem.isReturned()) {
                                        System.out.println("Ce produit est dèjà retourné");
                                        break ;
                                    }
                                    System.out.println("Saisir la quantité de ce produit que vous voulez retourner");
                                    int returnQuantity = input.getUserNumInfo("Quantité",1,Integer.MAX_VALUE) ;
                                    while(!user.getOrder(orderNumber).containsQuantity(productId,returnQuantity)) {
                                        System.out.println("Vous ne pouvez pas retourner plus que vous avez commandé. veuillez reessayer svp");
                                        returnQuantity = input.getUserNumInfo("Quantité",1,Integer.MAX_VALUE) ;
                                    }
                                    System.out.println("Specifier la raison du retour");
                                    String reason = "" ;
                                    System.out.println("1. mauvais produit reçu");
                                    System.out.println("2. produit défectueux");
                                    System.out.println("3. changement d'idée");
                                    option = input.getOption(1,3) ;
                                    switch(option) {
                                        case 1 :
                                            reason = "mauvais produit reçu" ;
                                            break;
                                        case 2 :
                                            reason = "produit défectueux" ;
                                            break;
                                        case 3 :
                                            reason = "changement d'idée" ;
                                            break ;
                                    }
                                    String sellerPseudo = user.getOrder(orderNumber).getItem(productId).getSellerPseudo() ;
                                    ReturnItem returnItem = new ReturnItem(productId,returnQuantity,sellerPseudo,user.getPseudo(),reason,false,false,false) ;
                                    returnItems.add(returnItem) ;
                                    for(OrderItem retItem : returnItems) System.out.println(retItem);
                                    System.out.println("Confirmer le retour: ");
                                    System.out.println("1. oui");
                                    System.out.println("2. non");
                                    if(input.getOption(1,2) == 1) {
                                        returns(returnItems,user) ;
                                        System.out.println("Retour initié ave succes");
                                        System.out.println("Voulez vous retourner un autre item de cette commande ?");
                                        System.out.println("1. oui");
                                        System.out.println("2. non");
                                        if(input.getOption(1,2) == 2) rep = false ;
                                    }
                                    else break ; // is it enough ?
                                }
                                break;
                        }
                    }
                    break;
                case 11:
                    System.out.println("Merci d'avoir utilisé notre service. Au revoir!") ;
                    repeat = false ;
                    return repeat ;
                default:
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
            }
        }
        return !repeat ;
    }

    private boolean isShoppingCartItemsAvailable(ShoppingCart shoppingCart) {
        for (int id : shoppingCart.getCart().keySet()) {
            Product product=Catalog.getProduct(id);
            if (product.getquantity() < shoppingCart.getCart().get(id)) {
                System.out.println("Vous avez une quantité supérieure à celle disponible pour l'item suivant :");
                System.out.println(product);
                System.out.println("Quantité disponible : " + product.getquantity() + " Quantité dans le panier : " + shoppingCart.getCart().get(product));
                return false;
            }
        }
        return true;
    }

    public Client getUserByPseudo(String pseudo) {
        return clients.stream().filter(u -> pseudo.equals(u.getPseudo())).findAny().orElse(null);
    }
    public Order getOrderFromNumber(String orderNumber,Client user){
        return user.getOrders().getOrDefault(orderNumber,null);
    }
    public Product getProductInOrder(Order order,int productId){
        if(order.getItems().stream().anyMatch(item -> item.getProductId() == productId)){
            return Catalog.getProduct(productId);
        }
        return null;
    }

    public Client getClientRegistrationInfo() {
        System.out.println("Saisissez vos informations");
        String firstName = input.getUserStrInfo("Prenom");
        String lastName = input.getUserStrInfo("Nom");

        String email = input.getUserStrInfo("Email");

        while (isEmailAlreadyUsed(email)) {
            System.out.println("Cet email est déjà utilisé. Veuillez entrer un nouveau.");
            email = input.getUserStrInfo("email");
        }

        String pseudo = input.getUserStrInfo("pseudo");

        while (isPseudoAlreadyUsed(pseudo)) {
            System.out.println("Ce pseudo est déjà utilisé. Veuillez entrer un nouveau.");
            pseudo = input.getUserStrInfo("pseudo");
        }
        long number = input.getUserNumInfo("Numero", 1, Integer.MAX_VALUE);
        String shipAddress = input.getUserStrInfo("Adresse de livraison");

        String password = input.getUserStrInfo("Mot de passe") ;
        while (isPasswordAlreadyUsed(password)) {
            System.out.println("Ce mot de passe est déjà utilisé. Veuillez entrer un nouveau.");
            password = input.getUserStrInfo("Mot de passe");
        }

        Client client = new Client(firstName, lastName, email, pseudo, number, shipAddress,password);

        int option = 2;
        while (option == 2) {
            System.out.println(client);
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
                    modifyClientInfo(client);
                }
                option = 2;
            }
        }
        return client;
    }

    public boolean isPasswordAlreadyUsed(String password) {
        for (Seller seller : sellerManager.getSellers()) {
            if(seller.getPassword().equals(password)) return true ;
        }
        return clients.stream().anyMatch(user -> password.equals(user.getPassword()));
    }

    public Client getClient(String pseudo) {
        return clients.stream().filter(client -> pseudo.equals(client.getPseudo())).findAny().orElse(null) ;
    }

    public void followClient(Client follower, Client toFollow) {
        follower.follow(toFollow);
    }

    public void unfollowClient(Client unfollower, Client toUnfollow) {
        unfollower.unfollow(toUnfollow);
    }
    public Client getClientFromPseudo(String pseudo){
        for(Client client:clients) if(client.getPseudo().equals(pseudo)) return client;
        return null;
    }
    public void displayLikedProductsByFollowing(Client user) {
        for (String followingClient : user.getFollowing()) {
            System.out.println("Items aimés par: " + followingClient);
            if(getClientFromPseudo(followingClient).getLikedProduct().isEmpty()) System.out.println("Rien");
            else{
                for(int productId: getClientFromPseudo(followingClient).getLikedProduct()){
                    System.out.println(Catalog.getProduct(productId));
                }
            }

        }
    }

    public Return returns(ArrayList<OrderItem> returnItems, Client user) {
        String returnID = UUID.randomUUID().toString();
        for (OrderItem returnItem : returnItems) {
            Seller seller = sellerManager.getSeller(returnItem.getSellerPseudo()) ;
            seller.addReturnItem((ReturnItem) returnItem);
        }
        Return newReturn = new Return(returnID,returnItems, Calendar.getInstance().getTime(),false,false,null,null,"") ;
        user.addReturn(newReturn);
        return newReturn ;
    }
    public Order buy(String address,Client user) {
        // Generate a unique order ID
        String orderID = UUID.randomUUID().toString();
        // Create an order using the current cart and customer information
        Order newOrder = new Order(orderID, user.getShoppingCart().convertToOrderItems(), Calendar.getInstance().getTime(), false, false, null, null, address);
        user.addOrder(newOrder);
        user.addPoints((int)user.getShoppingCart().getNumberPoints());

        // Update the product quantity
        Catalog.update(user.getShoppingCart().getCart());
        // Update the orderItem for sellers
        sellerManager.updateSellerOrderItems(newOrder);

        // Clear the cart after the purchase
        user.setShoppingCart(new ShoppingCart());
        return newOrder;
    }
}
