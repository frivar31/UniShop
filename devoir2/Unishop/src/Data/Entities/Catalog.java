package Data.Entities;

import Data.Entities.Products.Product;

import java.util.HashMap;
import java.util.List;

public class Catalog {
    public static HashMap<Integer,Object[]> catalogMap=new HashMap<Integer,Object[]>();

    public static void update(List<Product> products){
        for(Product product:products){
            Object[] obj=catalogMap.get(product.getId());
            Product current= (Product) obj[0];
            if(current.getquantity()>1)product.setquantity((int) (current.getquantity()-1));
            else catalogMap.remove(product.getId());
        }
    }
}
