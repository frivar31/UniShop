package Data.Entities;

import Data.Entities.Products.Product;
import Data.Entities.Users.Seller;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class ReturnItem extends OrderItem {
    /**
     * Constructeur de la classe ReturnItem.
     *
     * @param returnedProductId ID du produit retourné.
     * @param returnedQuantity  Quantité du produit retourné.
     * @param sellerPseudo      Pseudo du vendeur.
     * @param clientPseudo      Pseudo du client.
     * @param reason            Raison du retour.
     * @param delivered         Statut de livraison.
     * @param shipped           Statut d'expédition.
     * @param returned          Statut du retour.
     * @param shipDate          Date d'expédition.
     * @param signaled          Statut de signalement.
     */
    @JsonCreator
    public ReturnItem(@JsonProperty("returnedProductId") int returnedProductId,
                     @JsonProperty("returnedQuantity") int returnedQuantity,
                     @JsonProperty("sellerPseudo") String sellerPseudo,
                      @JsonProperty("clientPseudo") String clientPseudo,
                      @JsonProperty("reason") String reason,
                      @JsonProperty("delivered") Boolean delivered,
                      @JsonProperty("shipped") Boolean shipped,
                      @JsonProperty("returned") Boolean returned,
                      @JsonProperty("shipDate") Date shipDate,
                      @JsonProperty("signaled") Boolean signaled) {
        super(returnedProductId,returnedQuantity,sellerPseudo,clientPseudo,reason,delivered,shipped,returned,shipDate,signaled);
    }


    /**
     * Retourne une représentation textuelle de l'objet ReturnItem.
     *
     * @return Une chaîne de caractères représentant l'élément de retour.
     */
    @Override
    public String toString() {
        Product product=Catalog.getProduct(getProductId());
        String state = "" ;
        if (!isShipped()) state = "En production" ;
        else if(isShipped()) state = "En livraison" ;
        if (isDelivered()) state = "Confirmé" ;
        return "{" +
                "\n- titre='" + product.getTitle() + '\'' +
                "\n- quantité='" + getQuantity() + '\'' +
                "\n- id='" + getProductId() + '\'' +
                "\n- raison de retour='" + getReason() + '\'' +
                "\n- état='" + state + '\'' +
                "\n}";
    }
}
