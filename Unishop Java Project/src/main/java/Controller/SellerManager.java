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

public class SellerManager {
    public UserInteractionService input;
    private ProductManager productManager;
    private ClientManager clientManager;
    private List<Seller> sellers;

    public SellerManager(List<Seller> sellers) {
        this.sellers = sellers;
        this.input = new UserInteractionService();
    }

    public void confirmReturnReception(ReturnItem returnItem, Seller seller) {
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
        String orderNumber = "";
        for (Order order : clientManager.getClient(returnItem.getClientPseudo()).getOrders().values()) {
            for (OrderItem orderItem : order.getItems()) {
                if (orderItem.getProductId() == returnItem.getProductId()) {
                    orderNumber = order.getOrderNumber();
                    break;
                }
            }
        }
        clientManager.getClient(returnItem.getClientPseudo()).getOrder(orderNumber).update(returnItem.getProductId(), returnItem.getQuantity());
        clientManager.getClient(returnItem.getClientPseudo()).getOrder(orderNumber).getItem(returnItem.getProductId()).setReturned(true);
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

    public boolean isPseudoAlreadyUsed(String pseudo) {
        for (Client client : clientManager.getClients()) {
            if (client.getPseudo().equals(pseudo)) return true;
        }
        return sellers.stream().anyMatch(user -> pseudo.equals(user.getPseudo()));
    }

    public List<Seller> findSellersByName(String name) {
        return this.sellers.stream().filter(user -> name.equals(user.getFirstName())).toList();
    }


    public Seller getSeller(String pseudo) {
        return sellers.stream().filter(seller -> pseudo.equals(seller.getPseudo())).findAny().orElse(null);
    }

    public List<Seller> findSellersByProductType(ProductType type) {
        return this.sellers.stream().filter(seller -> seller.getProducts().stream().anyMatch(product -> product.getCategory().equals(type))).collect(Collectors.toList());
    }

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
            System.out.println("8. Quitter");
            int option = input.getOption(1, 8);
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
                    ArrayList<ReturnItem> returnItems = seller.getReturnItems();
                    if (returnItems == null) {
                        System.out.println("Vous n'avez aucun retour à confirmer");
                        break;
                    }
                    for (int i = 0; i < returnItems.size(); i++) {
                        System.out.println("#" + i + " " + returnItems.get(i));
                    }
                    System.out.println("Entrer le # du produit dont vous voulez confirmer le retour");
                    int index = input.getUserNumInfo("index", 0, returnItems.size() - 1);
                    ReturnItem returnItem = returnItems.get(index);
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
                    System.out.println("Merci d'avoir utilisé notre service. Au revoir!");
                    repeat = false;
                    return repeat;
            }
        }
        return !repeat;
    }

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

    public User getUserByPseudo(String pseudo) {
        return sellers.stream().filter(u -> pseudo.equals(u.getPseudo())).findAny().orElse(null);
    }

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

    public boolean isEmailAlreadyUsed(String email) {
        for (Client client : clientManager.getClients()) {
            if (client.getEmail().equals(email)) return true;
        }
        return sellers.stream().anyMatch(user -> email.equals(user.getEmail()));
    }

    public boolean isPasswordAlreadyUsed(String password) {
        for (Client client : clientManager.getClients()) {
            if (client.getPassword().equals(password)) return true;
        }
        return sellers.stream().anyMatch(user -> password.equals(user.getPassword()));
    }

    public void updateSellerOrderItems(Order order) {
        for (OrderItem item : order.getItems()) {
            //to fix
            Seller seller = Catalog.getProductSeller(item.getProductId());
            seller.addOrderItem(item);
        }
    }

}
