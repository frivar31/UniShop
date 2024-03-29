import Controller.ClientManager;
import Controller.ProductManager;
import Controller.SellerManager;
import Data.Entities.*;
import Data.Entities.Products.*;
import Data.Entities.Users.Client;
import Data.Entities.Users.Seller;
import Data.Entities.Users.User;
import Service.UserInteractionService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;



/**
 * Cette classe représente l'application principale qui gère l'interaction entre les clients et les vendeurs.
 * Elle inclut des fonctionnalités d'inscription, de connexion et de navigation dans les menus principaux.
 */
public class App {

    /** Service d'interaction utilisateur pour la saisie depuis la console. */
    public static UserInteractionService input = new UserInteractionService();

    /**
     * Obtient les informations d'inscription pour un nouveau client ou vendeur.
     * @param clientManager Gestionnaire des clients.
     * @param sellerManager Gestionnaire des vendeurs.
     * @return Une instance de la classe User représentant le nouvel utilisateur.
     */
    private static User getRegistrationStream(ClientManager clientManager, SellerManager sellerManager) {
        System.out.println("Choisissez une option d'inscription");
        System.out.println("1. Vendeur");
        System.out.println("2. Acheteur");
        if (input.getOption(1, 2) == 2) {
            //create a client
            return clientManager.getClientRegistrationInfo();
        }
        //define a seller
        return sellerManager.getSellerRegistrationInfo();
    }
    /**
     * Gère le processus de connexion pour un utilisateur existant.
     * @param clientManager Gestionnaire des clients.
     * @param sellerManager Gestionnaire des vendeurs.
     * @return L'utilisateur connecté.
     */
    private static User login(ClientManager clientManager, SellerManager sellerManager) {
        User user = null;
        String pseudo=input.getUserStrInfo("Pseudo");
        while ((user = clientManager.getUserByPseudo(pseudo)) == null && (user = sellerManager.getUserByPseudo(pseudo)) == null) {
            System.out.println("Ce compte n'existe pas");
            pseudo = input.getUserStrInfo("Pseudo");
        }
        String password = input.getUserStrInfo("Mot de passe");
        while (!user.getPassword().equals(password)) {
            System.out.println("Mot de passe incorrecte. Veuillez reessayer");
            password = input.getUserStrInfo("Mot de passe");
        }

        return user;
    }
    /**
     * Le point d'entrée principal de l'application.
     * @param args Les arguments de la ligne de commande.
     * @throws IOException En cas d'erreur lors de la lecture/écriture des fichiers.
     */
    public static void main(String[] args) throws IOException {
        System.out.println("-----------------------------------------------------");
        System.out.println("-----------------------------------------------------");
        System.out.println("-----------------WELCOME TO UNISHOP------------------");
        System.out.println("-----------------------------------------------------");
        System.out.println("-----------------------------------------------------");
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfBaseType(Product.class)
                .build();
        ObjectMapper objectMapper = new ObjectMapper().setPolymorphicTypeValidator(ptv);
        ClientManager clientManager = new ClientManager(new ArrayList<>());
        SellerManager sellerManager = new SellerManager(new ArrayList<>());

        File clientsFile = new File("target/clients.json");
        File sellersFile = new File("target/sellers.json");


        if (clientsFile.exists()) {
            List<Client> clients = objectMapper.readValue(clientsFile, new TypeReference<List<Client>>() {});
            clientManager.setClients(new ArrayList<>(clients));
        }


        if (sellersFile.exists()) {
            List<Seller> sellers = objectMapper.readValue(sellersFile, new TypeReference<List<Seller>>() {});
            sellerManager.setSellers(new ArrayList<>(sellers));
            for (Seller seller:sellers) seller.updateCatalog();
        }
        //init orderItem with link in sellers and ticket with link in sellers
        for(Client client:clientManager.getClients()){
            for(Ticket ticket:client.getTickets()){
                Seller seller=sellerManager.getSeller(ticket.getSellerPseudo());
                seller.addTicket(ticket);
            }
            for(Order order:client.getOrders().values()){
                for (OrderItem orderItem:order.getItems()){
                    Seller seller=sellerManager.getSeller(orderItem.getSellerPseudo());
                    seller.addOrderItem(orderItem);
                }
            }
        }

        ProductManager productManager = new ProductManager();
        clientManager.setSellerManager(sellerManager);
        clientManager.setProductManager(productManager);
        sellerManager.setClientManager(clientManager);
        sellerManager.setProductManager(productManager);

        User user = null;

        final ClientManager finalClientManager = clientManager;
        final SellerManager finalSellerManager = sellerManager;

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                // Serialize clients and sellers to JSON files
                objectMapper.writeValue(new File("target/clients.json"), finalClientManager.getClients());
                objectMapper.writeValue(new File("target/sellers.json"), finalSellerManager.getSellers());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        boolean repeat = true ;
        while(repeat) {
            System.out.println("Choisissez une option");
            System.out.println("1. Se connecter");
            System.out.println("2. S'inscrire");
            System.out.println("3. Quitter");
            int option  = input.getOption(1, 3) ;
            switch (option) {
                case 1:
                    user = login(clientManager, sellerManager);
                    break;
                case 2:
                    user = getRegistrationStream(clientManager, sellerManager);
                    break;
                case 3:
                    System.exit(1);
            }
            System.out.println();
            System.out.println("##########################");
            if (user instanceof Client) repeat = !clientManager.principalMenu((Client) user);
            else repeat = !sellerManager.getSellerServiceInfo((Seller) user);
        }
    }
}
