package Data.Entities;
import Data.Entities.Products.Product;
import Data.Entities.Users.Seller;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.HashMap;


/**
 * Classe représentant le catalogue des produits.
 */
public class Catalog {
    /**
     * Map qui associe l'identifiant du produit à un tableau d'objets contenant le produit et le vendeur.
     */
    public static HashMap<Integer, Object[]> catalogMap = new HashMap<Integer, Object[]>();


    /**
     * Met à jour la quantité des produits dans le catalogue en fonction des produits spécifiés.
     *
     * @param products Map associant l'identifiant du produit à la quantité à mettre à jour.
     */
    public static void update(HashMap<Integer, Integer> products) {
        for (Integer id : products.keySet()) {
            Object[] obj = catalogMap.get(id);
            Product current = (Product) obj[0];
            //get the quantity of items in the shopping cart from products HashMap
            if (current.getquantity() > products.get(id))
                current.setquantity((int) (current.getquantity() - products.get(id)));
            else{
                catalogMap.remove(current.getId());
                current.setquantity(0);
            }
        }
    }
    /**
     * Obtient le produit du catalogue en fonction de son identifiant.
     *
     * @param id L'identifiant du produit.
     * @return Le produit correspondant à l'identifiant, ou null s'il n'est pas présent dans le catalogue.
     */
    public static Product getProduct(int id) {
        return catalogMap.containsKey(id) ? (Product) catalogMap.get(id)[0] : null;
    }
    /**
     * Obtient le vendeur associé au produit du catalogue en fonction de l'identifiant du produit.
     *
     * @param id L'identifiant du produit.
     * @return Le vendeur associé au produit correspondant à l'identifiant, ou null s'il n'est pas présent dans le catalogue.
     */
    public static Seller getProductSeller(int id) {
        return catalogMap.containsKey(id) ? (Seller) catalogMap.get(id)[1] : null;
    }
}
