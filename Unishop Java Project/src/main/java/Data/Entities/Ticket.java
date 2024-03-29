package Data.Entities;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

/**
 * Cette classe représente un ticket associé à une commande.
 */
public class Ticket {
    private final String problemDescription;
    private final OrderItem item;
    private final String creationDate;
    private String replacementRequestDate;
    private String solutionDescription;
    private String trackingNumber;
    private boolean deliveryConfirmationBySeller;
    private String replacementProductDescription;
    private String replacementTrackingNumber;
    private boolean buyerConfirmationOfReplacementDelivery;
    private String sellerPseudo;
    private boolean buyerConfirmationOfReturnProductExpedition;
    private boolean sellerConfirmationOfReturnProductReception;

    /**
     * Constructeur pour créer un objet Ticket.
     *
     * @param problemDescription                    Description du problème associé au ticket.
     * @param solutionDescription                   Description de la solution au problème.
     * @param trackingNumber                        Numéro de suivi associé à la livraison.
     * @param replacementProductDescription         Description du produit de remplacement.
     * @param replacementTrackingNumber             Numéro de suivi associé à la livraison du produit de remplacement.
     * @param item                                  Article lié au ticket.
     * @param sellerPseudo                          Pseudo du vendeur associé à la commande.
     * @param replacementRequestDate               Date à laquelle la demande de remplacement a été effectuée.
     * @param buyerConfirmationOfReturnProductExpedition Confirmation de l'expédition du produit retourné par l'acheteur.
     * @param sellerConfirmationOfReturnProductReception Confirmation de la réception du produit retourné par le vendeur.
     */
    //Constructeur
    @JsonCreator
    public Ticket(@JsonProperty("problemDescription") String problemDescription, @JsonProperty("solutionDescription") String solutionDescription, @JsonProperty("trackingNumber") String trackingNumber, @JsonProperty("replacementProductDescription") String replacementProductDescription, @JsonProperty("replacementTrackingNumber") String replacementTrackingNumber, @JsonProperty("item") OrderItem item, @JsonProperty("sellerPseudo") String sellerPseudo, @JsonProperty("replacementRequestDate") String replacementRequestDate, @JsonProperty("buyerConfirmationOfReturnProductExpedition") Boolean buyerConfirmationOfReturnProductExpedition, @JsonProperty("sellerConfirmationOfReturnProductReception") Boolean sellerConfirmationOfReturnProductReception) {
        this.problemDescription = problemDescription;
        this.solutionDescription = solutionDescription;
        this.trackingNumber = trackingNumber;
        this.deliveryConfirmationBySeller = false;
        this.replacementProductDescription = replacementProductDescription;
        this.replacementTrackingNumber = replacementTrackingNumber;
        this.buyerConfirmationOfReplacementDelivery = false;
        this.creationDate = LocalDate.now().toString();
        this.item = item;
        this.sellerPseudo = sellerPseudo;
        this.replacementRequestDate = replacementRequestDate;
        this.sellerConfirmationOfReturnProductReception = sellerConfirmationOfReturnProductReception;
        this.buyerConfirmationOfReturnProductExpedition = buyerConfirmationOfReturnProductExpedition;
    }

    /**
     *
     * @param problemDescription    La description du problème signalé dans le ticket.
     * @param item                  L'article associé au problème
     * @param sellerPseudo          Le pseudo du vendeur lié à l'article
     */
    public Ticket(String problemDescription, OrderItem item, String sellerPseudo) {
        this.problemDescription = problemDescription;
        this.solutionDescription = null;
        this.trackingNumber = null;
        this.deliveryConfirmationBySeller = false;
        this.replacementProductDescription = null;
        this.replacementTrackingNumber = null;
        this.buyerConfirmationOfReplacementDelivery = false;
        this.creationDate = LocalDate.now().toString();
        this.item = item;
        this.sellerPseudo = sellerPseudo;
        this.replacementRequestDate = null;
        this.sellerConfirmationOfReturnProductReception = false;
        this.buyerConfirmationOfReturnProductExpedition = false;

    }

    public String getReplacementRequestDate() {
        return replacementRequestDate;
    }

    public void setReplacementRequestDate(String replacementRequestDate) {
        this.replacementRequestDate = replacementRequestDate;
    }

    /**
     * Vérifie si l'acheteur a confirmé l'expédition du produit retourné
     * @return  Vrai si l'acheteur a confirmé l'expédition
     */
    public boolean isBuyerConfirmationOfReturnProductExpedition() {
        return buyerConfirmationOfReturnProductExpedition;
    }

    public void setBuyerConfirmationOfReturnProductExpedition(boolean buyerConfirmationOfReturnProductExpedition) {
        this.buyerConfirmationOfReturnProductExpedition = buyerConfirmationOfReturnProductExpedition;
    }

    /**
     * Vérifie si le vendeur a confirmé la réception
     * @return Vrai si le vendeur a confirmé la réception du produit retourné, sinon faux
     */
    public boolean isSellerConfirmationOfReturnProductReception() {
        return sellerConfirmationOfReturnProductReception;
    }

    public void setSellerConfirmationOfReturnProductReception(boolean sellerConfirmationOfReturnProductReception) {
        this.sellerConfirmationOfReturnProductReception = sellerConfirmationOfReturnProductReception;
    }

    public OrderItem getItem() {
        return item;
    }

    public String getSellerPseudo() {
        return sellerPseudo;
    }

    public void setSellerPseudo(String sellerPseudo) {
        this.sellerPseudo = sellerPseudo;
    }

    // Méthode pour annuler automatiquement une demande de réexpédition après 30 jours
    /**
     * Méthode pour annuler automatiquement une demande de réexpédition après 30 jours.
     * Si la date actuelle est postérieure de 30 jours à la date de création du ticket, la demande de réexpédition est annulée.
     */
    public void autoCancelReshipmentRequest() {
        LocalDate thirtyDaysAfterCreation = LocalDate.parse(creationDate).plusDays(30);
        LocalDate currentDate = LocalDate.now();

        if (currentDate.isAfter(thirtyDaysAfterCreation)) {
            // Annuler la demande de réexpédition
            trackingNumber = null;
        }
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public String getSolutionDescription() {
        return solutionDescription;
    }

    public void setSolutionDescription(String solutionDescription) {
        this.solutionDescription = solutionDescription;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    // Getters et Setters

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
    /**
     * Vérifie si le vendeur a confirmé la livraison du produit.
     *
     * @return true si le vendeur a confirmé la livraison du produit, sinon false.
     */
    public boolean isDeliveryConfirmationBySeller() {
        return deliveryConfirmationBySeller;
    }

    public void setDeliveryConfirmationBySeller(boolean deliveryConfirmationBySeller) {
        this.deliveryConfirmationBySeller = deliveryConfirmationBySeller;
    }

    public String getReplacementProductDescription() {
        return replacementProductDescription;
    }

    public void setReplacementProductDescription(String replacementProductDescription) {
        this.replacementProductDescription = replacementProductDescription;
    }

    public String getReplacementTrackingNumber() {
        return replacementTrackingNumber;
    }

    public void setReplacementTrackingNumber(String replacementTrackingNumber) {
        this.replacementTrackingNumber = replacementTrackingNumber;
    }
    /**
     * Vérifie si l'acheteur a confirmé la réception du produit de remplacement.
     *
     * @return true si l'acheteur a confirmé la réception du produit de remplacement, sinon false.
     */
    public boolean isBuyerConfirmationOfReplacementDelivery() {
        return buyerConfirmationOfReplacementDelivery;
    }

    public void setBuyerConfirmationOfReplacementDelivery(boolean buyerConfirmationOfReplacementDelivery) {
        this.buyerConfirmationOfReplacementDelivery = buyerConfirmationOfReplacementDelivery;
    }

    public String getCreationDate() {
        return creationDate;
    }

    /**
     * Obtient une représentation textuelle du ticket.
     *
     * @return Une chaîne représentant le ticket.
     */
    @Override
    public String toString() {
        String stringBuilder = "\nDescription du problème: " + problemDescription + "\nDescription de la solution: " + solutionDescription + "\nArticle: " + item + "\nDate de création: " + creationDate + "\nNuméro de suivi: " + trackingNumber + "\nConfirmation de livraison par le vendeur: " + deliveryConfirmationBySeller + "\nDescription du produit de remplacement: " + replacementProductDescription + "\nNuméro de suivi du remplacement: " + replacementTrackingNumber + "\nConfirmation de livraison du remplacement par l'acheteur: " + buyerConfirmationOfReplacementDelivery + "\nPseudo du vendeur: " + sellerPseudo + "\n}";

        return stringBuilder;
    }
}
