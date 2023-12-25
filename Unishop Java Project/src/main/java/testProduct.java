import Data.Entities.Catalog;
import Data.Entities.ProductEvaluation;
import Data.Entities.Products.Product;
import Data.Entities.Products.ProductType;
import Data.Entities.ShoppingCart;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testProduct {
     ShoppingCart cart = new ShoppingCart();
     Product product = new Product("New Product", "Description", "2023-01-01", 5, 99.99, 1, ProductType.Hardware, "Model123", "BrandXYZ", new ArrayList<>(), new ArrayList<>());
    ProductEvaluation evaluation = new ProductEvaluation(4, "Great product!", "user1", 1);
    ProductEvaluation evaluation2 = new ProductEvaluation(5, "Excellent service!", "user2", 2);
    @Test
    public void testProductEvaluation() {
        product.addEvaluation(evaluation);
        product.addEvaluation(evaluation2);
        assertFalse(product.getEvaluations().isEmpty());
        assertTrue(product.getEvaluations().contains(evaluation));
    }
    @Test
    public void testProductEvaluationRemoval() {
        product.removeEvaluation(evaluation);
        assertTrue(product.getEvaluations().isEmpty());
    }
    @Test
    public void testAverageRatingCalculation() {
        product.addEvaluation(evaluation);
        double averageRating = product.averageRating();
        assertEquals(4, (int) averageRating);
    }
    @Test
    public void testContainsItem() {
        Catalog.catalogMap.put(product.getId(), new Object[]{product, null});
        assertFalse(cart.containsItem(product.getId()));
        cart.add(product.getId());
        assertTrue(cart.containsItem(product.getId()));
    }
    @Test
    public void testclearcart() {
        Catalog.catalogMap.put(product.getId(), new Object[]{product, null});
        cart.add(product.getId());
        assertTrue(cart.containsItem(product.getId()));
        assertEquals(1, cart.getCart().size());
        assertEquals(99.99, cart.getTotal(), 0.01);
        assertEquals(1, cart.getNumberItems());
        assertEquals(99, cart.getNumberPoints());
    }
    @Test
    public void testDeleteProduct() {
        Product product1 = new Product("Product 1", "Description 1", "2023-01-01", 10, 20.0, 2, ProductType.Article, "Model 1", "Brand 1", new ArrayList<>(), new ArrayList<>());
        Catalog.catalogMap.put(product1.getId(), new Object[]{product1, null});
        cart.add(product1.getId());
        cart.deleteProduct(product1.getId());
        assertFalse(cart.getCart().isEmpty());
        assertEquals(0,(int) cart.getTotal());
        assertEquals(0, cart.getNumberItems());
        assertEquals(0, cart.getNumberPoints());
    }
    @Test
    public void testClearCart() {
        Product product1 = new Product("Product 1", "Description 1", "2023-01-01", 10, 20.0, 2, ProductType.Article, "Model 1", "Brand 1", new ArrayList<>(), new ArrayList<>());
        Product product2 = new Product("Product 2", "Description 2", "2023-01-02", 15, 30.0, 3, ProductType.Article, "Model 2", "Brand 2", new ArrayList<>(), new ArrayList<>());
        Catalog.catalogMap.put(product1.getId(), new Object[]{product1, null});
        Catalog.catalogMap.put(product2.getId(), new Object[]{product2, null});
        cart.add(product1.getId());
        cart.add(product2.getId());
        cart.clearCart();
        assertFalse(cart.containsItem(product1.getId()));
        assertFalse(cart.containsItem(product2.getId()));
        assertEquals(0, cart.getCart().size());
        assertEquals(0.0, cart.getTotal(), 0.01);
        assertEquals(0, cart.getNumberItems());
        assertEquals(0, cart.getNumberPoints());
    }

}
