package Controller;

import Data.Entities.Catalog;
import Data.Entities.Products.Product;
import Data.Entities.ShoppingCart;
import Data.Entities.Users.Client;
import Data.Entities.Users.Seller;
import Service.UserInteractionService;

import java.util.List;

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

    public void getClientServiceInfo(Client user) {

        while (true) {
            System.out.println("Sélectionnez la tâche que vous voulez effectuer: ");
            System.out.println("1. Chercher un produit");
            System.out.println("2. Chercher un vendeur");
            System.out.println("3. Afficher le panier");
            System.out.println("4. Passer la commande");
            System.out.println("5. Editer les items du panier");
            System.out.println("6. Modifier son profile");
            System.out.println("7. Voir la liste des acheteurs");
            System.out.println("8. Voir mon total de points");
            System.out.println("9. Voir mon classement par rapport à mes suiveurs");
            System.out.println("10.Voir les produits aimés par les gens que je suis");
            System.out.println("11. Quitter");


            int option = input.getOption(1, 11);

            switch (option) {
                case 1:
                    boolean redo = true;
                    while (redo) {
                        System.out.println("Liste des produits disponibles :");
                        for (Object[] objects : Catalog.catalogMap.values()) {
                            Product current = (Product) objects[0];
                            System.out.println(current);
                        }
                        System.out.println("Voulez-vous acheter un produit?");
                        System.out.println("1. Oui");
                        System.out.println("2. Non");
                        if (input.getOption(1, 2) == 1) {
                            Product selectedProduct = productManager.findProductById();
                            user.getShoppingCart().add(selectedProduct);
                            System.out.println("Produit ajouté au panier.");
                            System.out.println("Voulez-vous ajouter un autre produit?");
                            System.out.println("1. Oui");
                            System.out.println("2. Non");
                            if (input.getOption(1, 2) == 2) redo = false;
                        } else redo = false;
                    }
                    break;

                case 2:
                    System.out.println("Liste des vendeurs :");
                    Seller current = sellerManager.findSellerById();
                    System.out.println("Voici la liste des produits de ce vendeur:");
                    for (Product product : current.getProducts()) System.out.println(product);
                    break;

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
                            System.out.println(user.buy(user.getShipAddress()));
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
                    System.out.println(user.getShoppingCart().toString(product));
                    System.out.println("Voulez-vous retirer ou ajuster la quantité de l'item");
                    System.out.println("1. Retirer");
                    System.out.println("2. Editer");
                    if (input.getOption(1, 2) == 1) {
                        user.getShoppingCart().deleteProduct(product);
                        System.out.println("item supprimé");
                        System.out.println(user.getShoppingCart());
                    } else {
                        System.out.println("Rentré la quantité désiré :");
                        int quantity = input.getUserNumInfo("Quantité", 1, Integer.MAX_VALUE);
                        user.getShoppingCart().updateQuantity(product, quantity);
                        System.out.println(user.getShoppingCart());
                        System.out.println("Quantité ajusté");
                    }
                    System.out.println();
                    break;
                case 6:
                    modifyClientInfo(user);
                    break;
                case 7:
                    for (Client client : clients) System.out.println(client);
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
                    user.displayPointsRanking();
                    break;
                case 10:
                    user.displayLikedProductsByFollowing();
                    break;
                case 11:
                    System.out.println("Merci d'avoir utilisé notre service. Au revoir!");
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez choisir une option valide.");
            }
        }
    }

    private boolean isShoppingCartItemsAvailable(ShoppingCart shoppingCart) {
        for (Product product : shoppingCart.getCart().keySet()) {
            if (product.getquantity() < shoppingCart.getCart().get(product)) {
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

    public void followClient(Client follower, Client toFollow) {
        follower.follow(toFollow);
    }

    public void unfollowClient(Client unfollower, Client toUnfollow) {
        unfollower.unfollow(toUnfollow);
    }
}
