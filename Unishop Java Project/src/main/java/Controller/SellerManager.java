package Controller;

import Data.Entities.Catalog;
import Data.Entities.Products.Product;
import Data.Entities.Users.Seller;
import Data.Entities.Users.User;
import Service.UserInteractionService;

import java.util.ArrayList;
import java.util.List;

public class SellerManager {
    public UserInteractionService input;
    private ProductManager productManager;
    private ClientManager clientManager;
    private List<Seller> sellers;

    public SellerManager(List<Seller> sellers) {
        this.sellers = sellers;
        this.input = new UserInteractionService();
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
        return sellers.stream().anyMatch(user -> pseudo.equals(user.getPseudo())) || clientManager.isPseudoAlreadyUsed(pseudo);
    }

    public Seller findSellerById() {
        for (Object[] objects : Catalog.catalogMap.values()) {
            Seller current = (Seller) objects[1];
            System.out.println(current);
        }
        while (true) {
            System.out.println("Entrer le pseudo du vendeur:");
            String pseudo = input.getUserStrInfo("Pseudo");

            for (User seller : sellers) {
                if (seller.getPseudo().equalsIgnoreCase(pseudo)) {
                    return (Seller) seller;
                }
            }
            System.out.println("Pas de vendeur avec un tel pseudo. Veuillez reesayer.");
        }
    }

    public void getSellerServiceInfo(Seller seller) {

        System.out.println("Selectionner la tache que voulez effectuer: ");
        System.out.println("1. Offrir un produit: ");
        System.out.println("2. Changer l'etat d'une commande: ");
        System.out.println("3. Modifier son profile");
        int option = input.getOption(1, 3);
        switch (option) {
            case 1:
                Product product = null;
                product = productManager.getProductInfo();
                seller.addProduct(product);
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
        }
    }

    public void modifySellerInfo(Seller seller) {
        System.out.println("Choisir information a modifier: ");
        System.out.println("1. Prenom");
        System.out.println("2. Nom");
        System.out.println("3. Email");
        System.out.println("4. Pseudo");
        System.out.println("5. Numero");
        System.out.println("6. Mot de passe");

        int option = input.getOption(1, 5);

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

        String password = input.getUserStrInfo("Mot de passe") ;
        while (isPasswordAlreadyUsed(password)) {
            System.out.println("Ce mot de passe est déjà utilisé. Veuillez entrer un nouveau.");
            password = input.getUserStrInfo("Mot de passe");
        }

        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        Seller seller = new Seller(firstName, lastName, email, pseudo, number, products);

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
        return sellers.stream().anyMatch(user -> email.equals(user.getEmail())) || clientManager.isEmailAlreadyUsed(email);
    }

    public boolean isPasswordAlreadyUsed(String password) {
        return sellers.stream().anyMatch(user -> password.equals(user.getPassword())) || clientManager.isPasswordAlreadyUsed(password);
    }
}
