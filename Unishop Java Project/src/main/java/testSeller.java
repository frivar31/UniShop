import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import Controller.ProductManager;
import Controller.SellerManager;
import Data.Entities.Products.Product;
import Data.Entities.Products.ProductType;
import Data.Entities.Users.Seller;
import org.junit.Test;
/**
 * Représente les tests relier aux seller.
 */
public class testSeller {
    SellerManager sellerManager = new SellerManager(new ArrayList<>());
    Product product1 = new Product("Smartphone", "High-performance smartphone with a sleek design", "2023-03-10", 20, 699.99, 1, ProductType.Hardware, "Model456", "BrandTech", new ArrayList<>(), new ArrayList<>(List.of("user5", "user6")));
    Product product2 = new Product("Book", "Interesting book on a specific topic", "2023-02-15", 50, 19.99, 1, ProductType.Book, "BookModel456", "BookBrandABC", new ArrayList<>(), new ArrayList<>(List.of("user3", "user4")));
    ArrayList<Product> productsList = new ArrayList<>();
    List<Seller> sellers = new ArrayList<>();
    Seller seller1 = new Seller("John1", "Doe1", "john.doe@example.com", "john_doe11", 123456789L, productsList, "password123");
    Seller seller2 = new Seller("John", "Doe", "john.doe@example.com", "john_doe", 123456789L, productsList, "password123");
    Product product = new Product("New Product", "Description", "2023-01-01", 5, 99.99, 1, ProductType.Hardware, "Model123", "BrandXYZ", new ArrayList<>(), new ArrayList<>());
    ProductManager productManager = new ProductManager();

    @Test
    public void testifindSellersByName(){
        productsList.add(product1);
        productsList.add(product2);
        sellers.add(seller1);
        sellers.add(seller2);
        sellerManager = new SellerManager(sellers);
        assertEquals(2, sellerManager.getSellers().size());
        assertEquals("["+ seller1.toString()+"]",sellerManager.findSellersByName("John1").toString());
    }
    @Test
    public void testfindProductsByTitle() {
        productsList.add(product1);
        productsList.add(product2);
        sellers.add(seller1);
        sellers.add(seller2);
        for (Seller seller:sellers) seller.updateCatalog();
        assertFalse(productManager.findProductsByTitle("Book").isEmpty());
    }
    @Test
    public void testAddProductToSeller() { // ici je dois savoir si il a bien ajouter le produit
        productsList.add(product1);
        productsList.add(product2);
        sellers.add(seller1);
        sellers.add(seller2);
        seller1.addProduct(product);
        System.out.println(seller1.getProduct(1).toString());
        System.out.println(seller1.getProduct(2).toString());
        assertFalse(seller1.getProducts().isEmpty());
    }
    @Test
    public void testRemoveProductToSeller() {
        productsList.add(product1);
        productsList.add(product2);
        sellers.add(seller1);
        sellers.add(seller2);
        seller1.addProduct(product);
        seller1.deleteProduct(product);
        seller1.deleteProduct(product1);
        seller1.deleteProduct(product2);
        assertTrue(seller1.getProducts().isEmpty());

    }

}
