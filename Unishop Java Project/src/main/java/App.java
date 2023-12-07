import Controller.ClientManager;
import Controller.ProductManager;
import Controller.SellerManager;
import Data.Entities.Catalog;
import Data.Entities.Products.*;
import Data.Entities.Type;
import Data.Entities.Users.Client;
import Data.Entities.Users.Seller;
import Data.Entities.Users.User;
import Service.UserInteractionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class App {
    public static UserInteractionService input = new UserInteractionService();

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

    private static User login(String pseudo,String password, ClientManager clientManager, SellerManager sellerManager) {
        User user = null;
        while ((user = clientManager.getUserByPseudo(pseudo)) == null && (user = sellerManager.getUserByPseudo(pseudo)) == null) {
            System.out.println("Ce compte n'existe pas");
            pseudo = input.getUserStrInfo("Pseudo");
        }
        while (!user.getPassword().equals(password)) {
            System.out.println("Mot de passe incorrecte. Veuillez reessayer");
            password = input.getUserStrInfo("Mot de passe");
        }

        return user;
    }

    public static <Users> void run() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Client client1 = new Client("sidya", "galakho", "sidya.galakho@gmail.ca", "rango", 4385273906L, "9545 Rue Lajeunesse","1");
        Client client2 = new Client("John", "Doe", "john.doe@gmail.com", "password123", 1234567890L, "123 Main Street","2");


        //objectMapper.writeValue(new File("target/client.json"),client1);

        Client client3 = new Client("Alice", "Smith", "alice.smith@outlook.com", "myPass123", 9876543210L, "456 Elm Street","3");
        Client client4 = new Client("Michael", "Johnson", "michael.johnson@gmail.com", "securePwd", 5551112222L, "789 Oak Avenue","4");
        Client client5 = new Client("Emily", "Davis", "emily.davis@hotmail.com", "p@ssw0rd", 3334445555L, "101 Pine Street","5");

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(1951, Calendar.JULY, 16);

        //Book book1=objectMapper.readValue(new File("target/book.json"),Book.class);

        Book book1 = new Book("The Catcher in the Rye", "A novel about teenage angst and rebellion.", ProductType.Book, Calendar.getInstance().getTime().toString(), 5, 15.99, 4, "0316769487", "J.D. Salinger", "Little, Brown and Company", "Coming-of-age", calendar1.getTime().toString(), 1, 1);
        //objectMapper.writeValue(new File("target/book.json"),book1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(1960, Calendar.JULY, 11);
        Book book2 = new Book("To Kill a Mockingbird", "A story that addresses issues of racial injustice and moral growth.", ProductType.Book, Calendar.getInstance().getTime().toString(), 8, 12.49, 1, "0061120081", "Harper Lee", "J.B. Lippincott & Co.", "Southern Gothic", calendar2.getTime().toString(), 1, 1);

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2023, Calendar.JANUARY, 1);
        LearningResource resource = new LearningResource("Introduction to Java Programming", "A comprehensive guide to Java programming language.", ProductType.LearningResource, Calendar.getInstance().getTime().toString(), 50, 29.99, 2, "9780135166307", "John Doe", "Java University", calendar3.getTime().toString(), Type.printed, 1L);


        Article article = new Article("The Benefits of Regular Exercise", "Exploring the positive effects of regular physical activity on health.", ProductType.Article, Calendar.getInstance().getTime().toString(), 30, 9.99, 13, "Fitness", "HealthCo", "Exercise101");

        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(2022, Calendar.FEBRUARY, 1);
        Hardware hardware = new Hardware("Gaming Laptop", "Powerful laptop designed for gaming enthusiasts.", ProductType.Hardware, Calendar.getInstance().getTime().toString(), 15, 1499.99, 18, "Lenovo", "TechCorp", calendar4.getTime().toString(), "Laptops");

        DesktopTool desktopTool = new DesktopTool("Electric Screwdriver Set", "A set of electric screwdrivers for various household tasks.", ProductType.DesktopTool, "2023-05-10", // Assuming date format as a String
                25, 79.99, 1, "ToolCo", "ScrewMaster 2000", "Power Tools");


        ArrayList<Product> products1 = new ArrayList<>();
        products1.add(book1);
        products1.add(resource);
        Seller seller1 = new Seller("Alice", "Johnson", "alice.johnson@example.com", "1", 1234167890L, products1,"6");

        ArrayList<Product> products2 = new ArrayList<>();
        products2.add(book2);
        Seller seller2 = new Seller("David", "Smith", "david.smith@gmail.com", "myPass12", 9876843210L, products2,"7");

        ArrayList<Product> products3 = new ArrayList<>();
        products3.add(hardware);
        Seller seller3 = new Seller("Emma", "Brown", "emma.brown@yahoo.com", "securePw", 5551172222L, products3,"8");

        ArrayList<Product> products4 = new ArrayList<>();
        products4.add(article);
        Seller seller4 = new Seller("Michael", "Garcia", "michael.garcia@hotmail.com", "p@ssw0r", 3334443555L, products4,"9");

        ArrayList<Product> products5 = new ArrayList<>();
        products5.add(desktopTool);
        Seller seller5 = new Seller("Sophia", "Lee", "sophia.lee@outlook.com", "secretPas", 1112223733L, products5,"10");

        ClientManager clientManager = new ClientManager(Arrays.asList(client1, client2, client3, client4, client5));
        SellerManager sellerManager = new SellerManager(Arrays.asList(seller1, seller2, seller3, seller4, seller5));
        ProductManager productManager = new ProductManager();
        clientManager.setSellerManager(sellerManager);
        clientManager.setProductManager(productManager);
        sellerManager.setClientManager(clientManager);
        sellerManager.setProductManager(productManager);

        User user = null;
        objectMapper.writeValue(new File("target/catalogue.json"),Catalog.catalogMap);
        boolean repeat = true ;
        while(repeat) {
            System.out.println("Choisissez une option");
            System.out.println("1. Se connecter");
            System.out.println("2. S'inscrire");
            if (input.getOption(1, 2) == 1) {
                user = login(input.getUserStrInfo("Pseudo"),input.getUserStrInfo("Mot de passe"), clientManager, sellerManager);
            } else {
                user = getRegistrationStream(clientManager, sellerManager);
            }
            System.out.println();
            System.out.println("##########################");
            System.out.println();
            if (user instanceof Client) repeat = !clientManager.getClientServiceInfo((Client) user);
            else repeat = !sellerManager.getSellerServiceInfo((Seller) user);
        }


    }
}
