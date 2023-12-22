import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Controller.ClientManager;
import Controller.SellerManager;
import Data.Entities.*;
import Data.Entities.Products.Product;
import Data.Entities.Products.ProductType;
import Data.Entities.Users.Client;
import Data.Entities.Users.Seller;
import org.junit.Test;


public class testClient {
    Client client= new Client("John3", "Doe3", "john33@example.com", "johnny33", 123456789L, "123 Main St", "password");
    SellerManager sellerManager = new SellerManager(new ArrayList<>());
    ClientManager clientManager = new ClientManager(new ArrayList<>());
    Order order = new Order("123", new ArrayList<>(), new Date(), false, false, null, null, "456 Main St");
    Product product1 = new Product("Smartphone", "High-performance smartphone with a sleek design", "2023-03-10", 20, 699.99, 1, ProductType.Hardware, "Model456", "BrandTech", new ArrayList<>(), new ArrayList<>(List.of("user5", "user6")));
    Product product2 = new Product("Book", "Interesting book on a specific topic", "2023-02-15", 50, 19.99, 1, ProductType.Book, "BookModel456", "BookBrandABC", new ArrayList<>(), new ArrayList<>(List.of("user3", "user4")));
    ArrayList<Product> productsList = new ArrayList<>();
    List<Seller> sellers = new ArrayList<>();
    List<Client> clients = new ArrayList<>();
    Seller seller1 = new Seller("John1", "Doe1", "john.doe@example.com", "john_doe11", 123456789L, productsList, "password123");
    Seller seller2 = new Seller("John", "Doe", "john.doe@example.com", "john_doe", 123456789L, productsList, "password123");
    Client client2 = new Client("John222", "Doe222", "john@example222.com", "johnn222", 123456789L, "123 Main St222", "password222");

    @Test
    public void addOrder() {
        client.addOrder(order);
        assertNotNull(client.getOrders());
    }
    @Test
    public void testisPseudoAlreadyUsed() {
        productsList.add(product1);
        productsList.add(product2);
        sellers.add(seller1);
        sellers.add(seller2);
        sellerManager.setSellers(new ArrayList<>(sellers));
        clients.add(client);
        clientManager.setClients(new ArrayList<>(clients));
        clientManager.setSellerManager(sellerManager);
        sellerManager.setClientManager(clientManager);
        assertTrue(clientManager.isPseudoAlreadyUsed("john_doe11"));
        assertTrue(clientManager.isPseudoAlreadyUsed("johnny33"));
        assertFalse(clientManager.isPseudoAlreadyUsed("DD"));
    }
    @Test
    public void testClientFollowing() {
        client.follow(client2);
        assertEquals(1, client2.getFollowers().size());
    }
    @Test
    public void testClientFollowingThroughClientManager() {
        clientManager.followClient(client2, client);
        assertEquals(1, client.getFollowers().size());
    }
    @Test
    public void testisSignalable(){
        clientManager.followClient(client2, client);
        assertEquals(1, client.getFollowers().size());
    }
    @Test
        public void forMeToTest() {

            productsList.add(product1);
            productsList.add(product2);

            sellers.add(seller1);
            sellers.add(seller2);

            client = new Client("John", "Doe", "john@example.com", "johnny", 123456789L, "123 Main St", "password");
            Client client2 = new Client("John222", "Doe222", "john@exampl222e.com", "johnn222", 123456789L, "123 Main St222", "password222");


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


