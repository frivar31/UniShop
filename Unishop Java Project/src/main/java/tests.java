import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import Controller.ClientManager;
import Controller.ProductManager;
import Controller.SellerManager;
import Data.Entities.*;
import Data.Entities.Products.Product;
import Data.Entities.Products.ProductType;
import Data.Entities.Users.Client;
import Data.Entities.Users.Seller;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;


public class tests {
    public Client client;
    public SellerManager sellerManager;
    public Order order;

  /*  @BeforeEach
    void setUp() {
        Product product1 = new Product("Smartphone", "High-performance smartphone with a sleek design", "2023-03-10", 20, 699.99, 1, ProductType.Hardware, "Model456", "BrandTech", new ArrayList<>(),  // No evaluations initially
                new ArrayList<>(List.of("user5", "user6")));
        Product product2 = new Product("Book", "Interesting book on a specific topic", "2023-02-15", 50, 19.99, 1, ProductType.Book, "BookModel456", "BookBrandABC", new ArrayList<>(),
                new ArrayList<>(List.of("user3", "user4")));
        ArrayList<Product> productsList = new ArrayList<>();
        productsList.add(product1);
        productsList.add(product2);
        List<Seller> sellers = new ArrayList<>();
        sellers.add(new Seller("John1", "Doe1", "john.doe@example.com", "john_doe", 123456789L, productsList, "password123"));
        sellers.add( new Seller("John", "Doe", "john.doe@example.com", "john_doe", 123456789L, productsList, "password123"));
        sellerManager = new SellerManager(sellers);
    }*/
        @Test
        public void addOrder() {// on peux avoir une commande sans produit? mais sinon marche bien
            client = new Client("John", "Doe", "john@example.com", "johnny", 123456789L, "123 Main St", "password");
            order = new Order("123", new ArrayList<>(), new Date(), false, false, null, null, "456 Main St");
            order.toString();
            System.out.println(client.toString());
            System.out.println(client.getOrders());
            client.addOrder(order);
            System.out.println(client.getOrders());
            assertNotNull(client.getOrders());

        }
    @Test
    public void testAddProductToSeller() {
        Product product1 = new Product("Smartphone", "High-performance smartphone with a sleek design", "2023-03-10", 20, 699.99, 1, ProductType.Hardware, "Model456", "BrandTech", new ArrayList<>(), new ArrayList<>(List.of("user5", "user6")));
        Product product2 = new Product("Book", "Interesting book on a specific topic", "2023-02-15", 50, 19.99, 1, ProductType.Book, "BookModel456", "BookBrandABC", new ArrayList<>(), new ArrayList<>(List.of("user3", "user4")));
        ArrayList<Product> productsList = new ArrayList<>(); productsList.add(product1); productsList.add(product2);
        List<Seller> sellers = new ArrayList<>();
        Seller seller1 = new Seller("John1", "Doe1", "john.doe@example.com", "john_doe", 123456789L, productsList, "password123");
        Seller seller2 = new Seller("John", "Doe", "john.doe@example.com", "john_doe", 123456789L, productsList, "password123");
        sellers.add(seller1);
        sellers.add(seller2);
        sellerManager = new SellerManager(sellers);
        Product product = new Product("New Product", "Description", "2023-01-01", 5, 99.99, 1, ProductType.Hardware, "Model123", "BrandXYZ", new ArrayList<>(), new ArrayList<>());
        seller1.addProduct(product);
    }
    @Test
    public void testisPseudoAlreadyUsed() {// meme probleme que addproduct
        Product product1 = new Product("Smartphone", "High-performance smartphone with a sleek design", "2023-03-10", 20, 699.99, 1, ProductType.Hardware, "Model456", "BrandTech", new ArrayList<>(), new ArrayList<>(List.of("user5", "user6")));
        Product product2 = new Product("Book", "Interesting book on a specific topic", "2023-02-15", 50, 19.99, 1, ProductType.Book, "BookModel456", "BookBrandABC", new ArrayList<>(), new ArrayList<>(List.of("user3", "user4")));
        ArrayList<Product> productsList = new ArrayList<>(); productsList.add(product1); productsList.add(product2);

        List<Seller> sellers = new ArrayList<>();
        Seller seller1 = new Seller("John1", "Doe1", "john.doe@example.com", "john_doe1", 123456789L, productsList, "password123");
        Seller seller2 = new Seller("John", "Doe", "john.doe@example.com", "john_doe", 123456789L, productsList, "password123");
        sellers.add(seller1);
        sellers.add(seller2);
        SellerManager sellerManager = new SellerManager(new ArrayList<>());
        ClientManager clientManager = new ClientManager(new ArrayList<>());
        sellerManager.setSellers(new ArrayList<>(sellers));

        client = new Client("John33", "Doe33", "john@example.com", "johnny2", 123456789L, "123 Main St", "password");
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        clientManager.setClients(new ArrayList<>(clients));
        clientManager.setSellerManager(sellerManager);
        sellerManager.setClientManager(clientManager);

        assertTrue(clientManager.isPseudoAlreadyUsed("john_doe1"));
        assertTrue(clientManager.isPseudoAlreadyUsed("johnny2"));
    }
    @Test
    public void testifindSellersByName(){// marche bien
        Product product1 = new Product("Smartphone", "High-performance smartphone with a sleek design", "2023-03-10", 20, 699.99, 1, ProductType.Hardware, "Model456", "BrandTech", new ArrayList<>(), new ArrayList<>(List.of("user5", "user6")));
        Product product2 = new Product("Book", "Interesting book on a specific topic", "2023-02-15", 50, 19.99, 1, ProductType.Book, "BookModel456", "BookBrandABC", new ArrayList<>(), new ArrayList<>(List.of("user3", "user4")));
        ArrayList<Product> productsList = new ArrayList<>(); productsList.add(product1); productsList.add(product2);

        List<Seller> sellers = new ArrayList<>();
        Seller seller1 = new Seller("John1", "Doe1", "john.doe@example.com", "john_doe", 123456789L, productsList, "password123");
        Seller seller2 = new Seller("John", "Doe", "john.doe@example.com", "john_doe", 123456789L, productsList, "password123");
        sellers.add(seller1);
        sellers.add(seller2);
        SellerManager s = new SellerManager(sellers);
        assertEquals(2, s.getSellers().size());
        assertEquals("["+ seller1.toString()+"]",s.findSellersByName("John1").toString());
    }
    @Test
    public void testfindProductsByTitle() {
        Product product1 = new Product("Smartphone", "High-performance smartphone with a sleek design", "2023-03-10", 20, 699.99, 1, ProductType.Hardware, "Model456", "BrandTech", new ArrayList<>(), new ArrayList<>(List.of("user5", "user6")));
        Product product2 = new Product("Book", "Interesting book on a specific topic", "2023-02-15", 50, 19.99, 1, ProductType.Book, "BookModel456", "BookBrandABC", new ArrayList<>(), new ArrayList<>(List.of("user3", "user4")));
      ArrayList<Product> productsList = new ArrayList<>(); productsList.add(product1); productsList.add(product2);
        List<Seller> sellers = new ArrayList<>();
        Seller seller1 = new Seller("John1", "Doe1", "john.doe@example.com", "john_doe1", 123456789L, productsList, "password123");
        Seller seller2 = new Seller("John", "Doe", "john.doe@example.com", "john_doe", 123456789L, productsList, "password123");
        sellers.add(seller1);
        sellers.add(seller2);
        ProductManager productManager = new ProductManager();
        for (Seller seller:sellers) seller.updateCatalog();
        System.out.println(seller1.getProducts());
        System.out.println(productManager.toString());
        //assertFalse(productManager.findProductsByTitle("Book").isEmpty());

    }
    @Test
    public void testProductEvaluation() {// marche bien
        ProductEvaluation evaluation = new ProductEvaluation(4, "Great product!", "user1", 1);
        Product product = new Product("Smartphone", "Description", "2023-01-01", 5, 99.99, 1, ProductType.Hardware, "Model123", "BrandXYZ", new ArrayList<>(List.of(evaluation)), new ArrayList<>());
        product.addEvaluation(evaluation);
        assertFalse(product.getEvaluations().isEmpty());
        assertTrue(product.getEvaluations().contains(evaluation));
    }
    @Test
    public void testProductEvaluationRemoval() {// marche bien
        ProductEvaluation evaluation = new ProductEvaluation(4, "Great product!", "user1", 1);
        Product product = new Product("Smartphone", "Description", "2023-01-01", 5, 99.99, 1, ProductType.Hardware, "Model123", "BrandXYZ", new ArrayList<>(List.of(evaluation)), new ArrayList<>());
        product.removeEvaluation(evaluation);
        assertTrue(product.getEvaluations().isEmpty());
    }
    @Test
    public void testAverageRatingCalculation() {// marche bien
        ProductEvaluation evaluation = new ProductEvaluation(4, "Great product!", "user1", 1);
        Product product = new Product("Smartphone", "Description", "2023-01-01", 5, 99.99, 1, ProductType.Hardware, "Model123", "BrandXYZ", new ArrayList<>(List.of(evaluation)), new ArrayList<>());
        double averageRating = product.averageRating();
        assertEquals(4, (int) averageRating);
    }

    @Test
    public void testClientFollowing() { // marche bien
        Client client = new Client("John", "Doe", "john@example.com", "johnny", 123456789L, "123 Main St", "password");
        Client client2 = new Client("John222", "Doe222", "john@example222.com", "johnn222", 123456789L, "123 Main St222", "password222");
        client.follow(client2);
        assertEquals(1, client2.getFollowers().size());
    }

    @Test
    public void testClientFollowingThroughClientManager() { // marche bien
        Client client = new Client("John", "Doe", "john@example.com", "johnny", 123456789L, "123 Main St", "password");
        Client client2 = new Client("John222", "Doe222", "john@example222.com", "johnn222", 123456789L, "123 Main St222", "password222");
        ClientManager clientManager = new ClientManager(new ArrayList<>(List.of(client, client2)));
        clientManager.followClient(client2, client);
        assertEquals(1, client.getFollowers().size());
    }

        @Test
        public void buy() {// pour les tests
            ProductEvaluation evaluation1 = new ProductEvaluation(4, "Great product!", "user1", 1);
            ProductEvaluation evaluation2 = new ProductEvaluation(5, "Excellent service!", "user2", 2);

            Product product1 = new Product("Smartphone", "High-performance smartphone with a sleek design", "2023-03-10", 20, 699.99, 1, ProductType.Hardware, "Model456", "BrandTech", new ArrayList<>(), new ArrayList<>(List.of("user5", "user6")));
            Product product2 = new Product("Book", "Interesting book on a specific topic", "2023-02-15", 50, 19.99, 1, ProductType.Book, "BookModel456", "BookBrandABC", new ArrayList<>(), new ArrayList<>(List.of("user3", "user4")));
            Product product = new Product("Smartphone", "High-performance smartphone with a sleek design", "2023-03-10", 20, 699.99, 1, ProductType.Hardware, "Model456", "BrandTech", new ArrayList<>(), new ArrayList<>(List.of("user5", "user6")));

            ArrayList<Product> productsList = new ArrayList<>();
            productsList.add(product1);
            productsList.add(product2);

            List<Seller> sellers = new ArrayList<>();
            Seller seller1 = new Seller("John1", "Doe1", "john.doe@example.com", "john_doe", 123456789L, productsList, "password123");
            Seller seller2 = new Seller("John", "Doe", "john.doe@example.com", "john_doe", 123456789L, productsList, "password123");
            sellers.add(seller1);
            sellers.add(seller2);

            client = new Client("John", "Doe", "john@example.com", "johnny", 123456789L, "123 Main St", "password");
            Client client2 = new Client("John222", "Doe222", "john@exampl222e.com", "johnn222", 123456789L, "123 Main St222", "password222");

           // seller1.addProduct(product);//marche pas return null 1
            System.out.println(seller1.getProduct(1));
            System.out.println(seller1.getProduct(2));
            //client.rateProduct(product1,evaluation2); marche pas meme probleme du pointer null 10
           // System.out.println(product1.getEvaluations());


            // reste a tester, choisir entre :

           // public void updateQuantity(int id, int quantity) {
           // public void deleteProduct(int id) {
            //    public void followClient(Client follower, Client toFollow) {
            //public void displayLikedProductsByFollowing(Client user) {
            //public void removeRating(Product product) {
            //    private void followedBy(String follower) {
            // convertToOrderItems()
            // public void clearCart() {
            //    public boolean containsItem(int id) {



        }

}


