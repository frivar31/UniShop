package Data.Entities;
import Data.Entities.Products.Product;
import Data.Entities.Users.Seller;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;
import java.util.Date;
/**
 * Classe représentant un élément de commande associé à un produit.
 */
public class OrderItem {
    private final int productId;
    private final String sellerPseudo;
    private int quantity;
    private String clientPseudo;
    private String reason;
    private Boolean shipped;
    private Boolean delivered;
    private boolean returned;
    private Date shipDate;
    private Boolean signaled;

    /**
     * Constructeur de la classe OrderItem.
     *
     * @param productId    Identifiant du produit associé à l'élément de commande.
     * @param quantity     Quantité du produit commandé.
     * @param sellerPseudo Pseudo du vendeur associé au produit.
     * @param clientPseudo Pseudo du client qui a passé la commande.
     * @param reason       Raison associée à l'élément de commande.
     * @param delivered    Indique si l'élément de commande a été livré.
     * @param shipped      Indique si l'élément de commande a été expédié.
     * @param returned     Indique si le produit associé à l'élément de commande a été retourné.
     * @param shipDate     Date d'expédition de l'élément de commande.
     * @param signaled     Indique si l'élément de commande a été signalé.
     */

    @JsonCreator
    public OrderItem(@JsonProperty("productId") int productId,
                     @JsonProperty("quantity") int quantity,
                     @JsonProperty("sellerPseudo") String sellerPseudo,
                     @JsonProperty("clientPseudo") String clientPseudo,
                     @JsonProperty("reason") String reason,
                     @JsonProperty("delivered") Boolean delivered,
                     @JsonProperty("shipped") Boolean shipped,
                     @JsonProperty("returned") Boolean returned,
                     @JsonProperty("shipDate") Date shipDate,
                     @JsonProperty("signaled") Boolean signaled) {
        this.productId = productId;
        this.quantity = quantity;
        this.sellerPseudo = sellerPseudo;
        this.clientPseudo = clientPseudo;
        this.shipped = shipped;
        this.delivered = delivered;
        this.reason = reason;
        this.shipDate = shipDate;
        this.signaled = signaled;
    }

    public Boolean getShipped() {
        return shipped;
    }

    public void setShipped(Boolean shipped) {
        this.shipped = shipped;
        shipDate = Calendar.getInstance().getTime();
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public Boolean getSignaled() {
        return signaled;
    }

    public void setSignaled(Boolean signaled) {
        this.signaled = signaled;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public String getClientPseudo() {
        return clientPseudo;
    }

    public void setClientPseudo(String clientPseudo) {
        this.clientPseudo = clientPseudo;
    }
    /**
     * Vérifie si l'élément de commande a été expédié.
     *
     * @return {@code true} si l'élément de commande a été expédié, sinon {@code false}.
     */
    public Boolean isShipped() {
        return shipped;
    }
    /**
     * Vérifie si l'élément de commande a été livré.
     *
     * @return {@code true} si l'élément de commande a été livré, sinon {@code false}.
     */
    public Boolean isDelivered() {
        return delivered;
    }
    /**
     * Vérifie si le produit associé à l'élément de commande a été retourné.
     *
     * @return {@code true} si le produit a été retourné, sinon {@code false}.
     */
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
        this.clientPseudo = clientPseudo;
        this.shipped = shipped;
        this.delivered = delivered;
        this.reason = reason;
        this.returned = returned;
    }

    public String getSellerPseudo() {
        return sellerPseudo;
    }


    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

/**
 * Convertit l'objet en une représentation sous forme de chaîne de caractères.
 *
 * @return La représentation de l'objet sous forme de chaîne de caractères.
 */
    @Override
    public String toString() {
        Product product = Catalog.getProduct(getProductId());
        String state = "";
        String returnState = "";
        if (!isShipped()) state = "En production";
        else if (isShipped()) state = "En livraison";
        if (isDelivered()) state = "Livré";
        if (isReturned()) returnState = "\n- " + "retourné";
        return "{" +
                "\n- titre='" + product.getTitle() + '\'' +
                "\n- quantité='" + getQuantity() + '\'' +
                "\n- id='" + getProductId() + '\'' +
                returnState +
                "\n- " + shipDate +
                "\n}";
    }
}