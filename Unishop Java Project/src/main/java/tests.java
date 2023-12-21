import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import Data.Entities.*;
import Data.Entities.Products.Product;
import Data.Entities.Products.ProductType;
import Data.Entities.Users.Client;
import Data.Entities.Users.Seller;
import org.junit.Before;
import org.junit.Test;


public class tests {
        public Client client;

        @Test
        public void addOrder() {
            client = new Client("John", "Doe", "john@example.com", "johnny", 123456789L, "123 Main St", "password");
            // Créer une commande
            Order order = new Order("123", new ArrayList<>(), new Date(), false, false, null, null, "456 Main St");
            // Ajouter la commande au client
            order.toString();
            System.out.println(client.toString());
            client.addOrder(order);
            System.out.println(client.getOrders());

        }

        @Test
        public void buy() {
            ProductEvaluation evaluation1 = new ProductEvaluation(4, "Great product!", "user1", 1);
            ProductEvaluation evaluation2 = new ProductEvaluation(5, "Excellent service!", "user2", 2);
            // Create some example products
            Product product1 = new Product(
                    "Laptop",
                    "Powerful laptop with high-end specifications",
                    "2023-01-01",
                    10,
                    999.99,
                    1,
                    ProductType.Hardware,
                    "Model123",
                    "BrandXYZ",
                    new ArrayList<>(List.of(evaluation1)),
                    new ArrayList<>(List.of("user1", "user2"))
            );

            Product product2 = new Product(
                    "Book",
                    "Interesting book on a specific topic",
                    "2023-02-15",
                    50,
                    19.99,
                    1,
                    ProductType.Book,
                    "BookModel456",
                    "BookBrandABC",
                    new ArrayList<>(List.of(evaluation2)),
                    new ArrayList<>(List.of("user3", "user4"))
            );

            // Create an ArrayList of products
            ArrayList<Product> productsList = new ArrayList<>();
            productsList.add(product1);
            productsList.add(product2);


            // Create a Seller instance
            Seller seller1 = new Seller(
                    "John",
                    "Doe",
                    "john.doe@example.com",
                    "john_doe",
                    123456789L,
                    productsList,
                    "password123"
            );
            client = new Client("John", "Doe", "john@example.com", "johnny", 123456789L, "123 Main St", "password");
            // Créer une commande

            Product product = new Product(
                    "Smartphone",
                    "High-performance smartphone with a sleek design",
                    "2023-03-10",
                    20,
                    699.99,
                    1,
                    ProductType.Hardware,
                    "Model456",
                    "BrandTech",
                    new ArrayList<>(),  // No evaluations initially
                    new ArrayList<>(List.of("user5", "user6"))
            );
            seller1.addProduct(product);
            System.out.println(seller1.getProduct(1).toString());
            System.out.println(seller1.getProduct(2).toString());
/*
            System.out.println(product.toString(1));
            Catalog.catalogMap.put(product.getId(), new Object[]{product, });
            ShoppingCart shoppingCart = new ShoppingCart();
            System.out.println(product.getId());
            // Add the product to the shopping cart
            System.out.println(shoppingCart.toString());
            System.out.println(product.getPrice());
            //shoppingCart.add(mockProduct.getId());
            System.out.println(shoppingCart.toString());
    */

            // Store the initial points of the client
            //int initialPoints = client.getPoints();

            // Debugging information
           // System.out.println("Initial points: " + initialPoints);

            // Debugging information
           // System.out.println("Client before buy: " + client);

            // Perform the purchase
           // Order order = client.buy("Shipping Address");

            // Debugging information
           // System.out.println("Client after buy: " + client);

            // Assertions
           // assertNotNull(order);
           // assertTrue(client.getShoppingCart().getCart().isEmpty()); // Cart should be empty after purchase
            //assertEquals(initialPoints + (int) mockProduct.getPoints(), client.getPoints()); // Points should be updated

            // Additional assertions based on your specific requirements
            //assertFalse(order.isShipped());
           // assertFalse(order.isDelivered());
            //assertNotNull(order.getOrderDate());
            //assertEquals("Shipping Address", order.getAddress());
            // Add more assertions as needed
        }

}


