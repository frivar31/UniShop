package Data.Entities;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Représente une commande avec des détails tels que le numéro de commande, les articles, la date de commande,
 * les informations de livraison et le statut.
 */
public class Order {

    private String orderNumber;
    private ArrayList<OrderItem> items;
    private Date orderDate;
    private Boolean delivered;
    private Boolean shipped;
    private Date shippedDate;
    private Date deliveryDate;
    private String address;
    private Boolean returned ;

    public Boolean isReturned() {
        return this.returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }

    /**
     * constructeur:
     *
     * @param orderNumber
     * @param items
     * @param orderDate
     * @param delivered
     * @param shipped
     * @param returned
     * @param shippedDate
     * @param deliveryDate
     * @param address
     */
    @JsonCreator
    public Order(@JsonProperty("orderNumber") String orderNumber,
                 @JsonProperty("items") ArrayList<OrderItem> items,
                 @JsonProperty("orderDate") Date orderDate,
                 @JsonProperty("delivered") Boolean delivered,
                 @JsonProperty("shipped") Boolean shipped,
                 @JsonProperty("returned") Boolean returned,
                 @JsonProperty("shippedDate") Date shippedDate,
                 @JsonProperty("deliveryDate") Date deliveryDate,
                 @JsonProperty("address") String address) {
        this.orderNumber = orderNumber;
        this.items = items;
        this.orderDate = orderDate;
        this.delivered = delivered;
        this.shipped = shipped;
        this.shippedDate = shippedDate;
        this.deliveryDate = deliveryDate;
        this.address = address;
        this.returned = returned ;
    }

    /**
     *
     * @return L'adresse de livraison de la commande
     */
    public String getAddress(){
        return address;
    }

    /**
     * Obtient le numéro de commande
     * @return
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     *Définit le numéro de commande.
     * @param orderNumber
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     *Renvoie la liste des articles de cette commande
     * @return  La liste des articles de la commande
     */
    public ArrayList<OrderItem> getItems() {
        return items;
    }

    /**
     *Renvoie la date de la commande
     * @return
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Définit la date de la commande.
     * @param orderDate orderDate La nouvelle date de la commande.
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     *Vérifie si la commande contient une quantité spécifiée.
     * @param productId L'identifiant du produit.
     * @param quantity La quantité à vérifier.
     * @return Vrai si la commande contient la quantité spécifiée
     */
    public boolean containsQuantity(int productId, int quantity) {
        return items.stream().anyMatch(orderIt -> orderIt.getProductId() == productId && orderIt.getQuantity() >= quantity) ;
    }

    /**
     * Récupère l'élément de commande correspondant à un produit spécifié.
     * @param productId
     * @return L'objet OrderItem associé au produit, ou null s'il n'est pas trouvé
     */
    public OrderItem getItem(int productId) {
        for (OrderItem item : items) {
            if (item.getProductId() == productId) return item ;
        }
        return null ;
    }

    /**
     * Met à jour la quantité
     * @param productId identifiant du produit
     * @param returnQuantity la quantité à soustraire de la quantité actuelle du produit
     */
    public void update(int productId,int returnQuantity) {
        int removePos = 0 ;
        for (int i = 0 ; i < items.size(); ++i) {
            if (items.get(i).getProductId() == productId) {
                int currQuantity = items.get(i).getQuantity() ;
                items.get(i).setQuantity(currQuantity-returnQuantity);
                if(items.get(i).getQuantity() == 0) {
                    removePos = i ;
                    break ;
                }
                else return ;
            }
        }
        items.remove(removePos) ;
    }

    /**
     * Vérifie si la commande est retournée en fonction de la date de livraison.
     * @return vrai si la commande est retournee ,sinon faux
     */
    @JsonIgnore
    public boolean isReturnable() {
        // Calculate the difference in milliseconds
        long diffInMilliseconds = Math.abs(Calendar.getInstance().getTime().getTime() - deliveryDate.getTime());
        // Convert milliseconds to days
        long daysDifference = TimeUnit.MILLISECONDS.toDays(diffInMilliseconds);
        return daysDifference < 30  ;
    }


    /**
     * virifier si tous les articles de la commande ont ete bien liver
     * @return vrai si tuous les artciles ont ete livrés, sinon faux.
     */
    public Boolean isDelivered() {
        for(OrderItem orderItem:items) if(!orderItem.isDelivered()) return false;
        return true;
    }

    /**
     *  Vérifie si la commande peut être signalée en fonction de la date de livraison
     * @return
     */
    @JsonIgnore
    public boolean isSignalable(){
        long diffInMilliseconds = Math.abs(Calendar.getInstance().getTime().getTime() - deliveryDate.getTime());
        // Convert milliseconds to days
        long daysDifference = TimeUnit.MILLISECONDS.toDays(diffInMilliseconds);
        return daysDifference < 365 ;
    }

    /**
     *Définit l'état de livraison
     * @param delivered
     */
    public void setDelivered(Boolean delivered) {

        this.delivered = delivered;
        for(OrderItem item:items){
            item.setDelivered(true);
        }
    }

    /**
     * Vérifie si tous les articles de la commande ont été expédiés
     * @return vrai si tous les articles ont ete expedies
     */
    public Boolean isShipped() {
        for(OrderItem orderItem:items) {
            if(!orderItem.isShipped()) return false;
        }
        this.shipped =  true ;
        //set ship date the max orderitem shipped date
        shippedDate = items.stream()
                .map(OrderItem::getShipDate)
                .max(Date::compareTo)
                .orElse(Calendar.getInstance().getTime());
        return this.shipped;
    }

    /**
     * Définit l'état d'expédition
     * @param shipped vrai si la commande a ete expediee , sinon faux.
     */
    public void setShipped(Boolean shipped) {
        this.shipped = shipped;
        for(OrderItem item:items){
            item.setShipped(true);
        }

    }

    /**
     * renvoie la date d'expedition de la commande
     * @return la date d'expedition de la commande
     */
    public Date getShippedDate() {
        return shippedDate;
    }

    /**
     *
     * @param shippedDate la vouelle date d'expedition de la commande
     */
    public void setShippedDate(Date shippedDate) {
        this.shippedDate = shippedDate;
    }

    /**
     *
     * @return date de livraison.
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     *
     * @param deliveryDate la nouvelle date de livraison
     */
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     *
     * @return retourne la commande sous forme de chaine de characteres
     */
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        StringBuilder sb = new StringBuilder();
        sb.append("Order Number: ").append(orderNumber).append("\n");
        sb.append("Order Date: ").append(dateFormat.format(orderDate)).append("\n");
        sb.append("Delivered: ").append(isDelivered()).append("\n");
        sb.append("Shipped: ").append(isShipped()).append("\n");

        if (shipped) {
            sb.append("Shipped Date: ").append(dateFormat.format(shippedDate)).append("\n");
        }

        if (delivered) {
            sb.append("Delivery Date: ").append(dateFormat.format(deliveryDate)).append("\n");
        }

        sb.append("Product: "+items);
        sb.append("\n");

        return sb.toString();
    }


}
