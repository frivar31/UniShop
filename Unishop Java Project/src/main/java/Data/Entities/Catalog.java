package Data.Entities;

import Data.Entities.Products.Product;
import Data.Entities.Users.Seller;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.HashMap;
public class Catalog {
    public static HashMap<Integer, Object[]> catalogMap = new HashMap<Integer, Object[]>();

    public static void update(HashMap<Integer, Integer> products) {
        for (Integer id : products.keySet()) {
            Object[] obj = catalogMap.get(id);
            Product current = (Product) obj[0];
            //get the quantity of items in the shopping cart from products HashMap
            if (current.getquantity() > products.get(id))
                current.setquantity((int) (current.getquantity() - products.get(id)));
            else catalogMap.remove(current.getId());
        }
    }

    public static Product getProduct(int id) {
        return catalogMap.containsKey(id) ? (Product) catalogMap.get(id)[0] : null;
    }

    public static Seller getSeller(int id) {
        return catalogMap.containsKey(id) ? (Seller) catalogMap.get(id)[1] : null;
    }
}
