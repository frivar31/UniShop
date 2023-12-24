import Data.Entities.ProductEvaluation;
import Data.Entities.Products.Product;
import Data.Entities.Products.ProductType;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testProduct {
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

}
