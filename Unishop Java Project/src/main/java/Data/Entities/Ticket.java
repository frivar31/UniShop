package Data.Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Ticket {
    private final String problemDescription;
    private final String solutionDescription;
    private final OrderItem item;
    private final String creationDate;
    private String trackingNumber;
    private boolean deliveryConfirmationBySeller;
    private String replacementProductDescription;
    private String replacementTrackingNumber;
    private boolean buyerConfirmationOfReplacementDelivery;
    private String sellerPseudo;

    //Constructeur
    @JsonCreator
    public Ticket(
            @JsonProperty("problemDescription") String problemDescription,
            @JsonProperty("solutionDescription") String solutionDescription,
            @JsonProperty("trackingNumber") String trackingNumber,
            @JsonProperty("replacementProductDescription") String replacementProductDescription,
            @JsonProperty("replacementTrackingNumber") String replacementTrackingNumber,
            @JsonProperty("item") OrderItem item,
            @JsonProperty("sellerPseudo") String sellerPseudo) {
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
    }
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

    public String getTrackingNumber() {
        return trackingNumber;
    }

    // Getters et Setters

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

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

    public boolean isBuyerConfirmationOfReplacementDelivery() {
        return buyerConfirmationOfReplacementDelivery;
    }

    public void setBuyerConfirmationOfReplacementDelivery(boolean buyerConfirmationOfReplacementDelivery) {
        this.buyerConfirmationOfReplacementDelivery = buyerConfirmationOfReplacementDelivery;
    }
    public String getCreationDate() {
        return creationDate;
    }
}