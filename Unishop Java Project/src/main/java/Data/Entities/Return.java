package Data.Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Return extends Order{
    /**
     * Constructeur de la classe Return.
     *
     * @param returnNumber   Numéro du retour.
     * @param returnItems    Liste des articles retournés.
     * @param returnDate     Date du retour.
     * @param delivered      Statut de livraison du retour.
     * @param shipped        Statut d'expédition du retour.
     * @param shipDate       Date d'expédition du retour.
     * @param deliveryDate   Date de livraison du retour.
     * @param returnAddress  Adresse de retour.
     */
    @JsonCreator
    public Return(@JsonProperty("returnNumber") String returnNumber,
                 @JsonProperty("returnItems") ArrayList<OrderItem> returnItems,
                 @JsonProperty("returnDate") Date returnDate,
                 @JsonProperty("delivered") Boolean delivered,
                 @JsonProperty("shipped") Boolean shipped,
                 @JsonProperty("shipDate") Date shipDate,
                 @JsonProperty("deliveryDate") Date deliveryDate,
                 @JsonProperty("returnAddress") String returnAddress) {
        super(returnNumber, returnItems, returnDate, delivered, shipped, shipDate, deliveryDate, returnAddress);
    }
    /**
     * Retourne une représentation textuelle de l'objet Return.
     *
     * @return Une chaîne de caractères représentant le retour.
     */
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        StringBuilder sb = new StringBuilder();
        sb.append("Return Number: ").append(getOrderNumber()).append("\n");
        sb.append("Return Date: ").append(dateFormat.format(getOrderDate())).append("\n");
        sb.append("Delivered: ").append(isDelivered()).append("\n");
        sb.append("Shipped: ").append(isShipped()).append("\n");

        if (isShipped()) {
            sb.append("Shipped Date: ").append(dateFormat.format(getShippedDate())).append("\n");
        }

        if (isDelivered()) {
            sb.append("Delivery Date: ").append(dateFormat.format(getDeliveryDate())).append("\n");
        }

        sb.append("Product: "+getItems());
        sb.append("\n");

        return sb.toString();
    }

}
