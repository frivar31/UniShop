package Data.Entities;

import java.time.LocalDate;

public class Ticket {
    private final String problemDescription;
    private final String solutionDescription;
    private final OrderItem item;
    private String trackingNumber;
    private boolean deliveryConfirmationBySeller;
    private String replacementProductDescription;
    private String replacementTrackingNumber;
    private boolean buyerConfirmationOfReplacementDelivery;
    private final String creationDate;

    // Constructeur
    public Ticket(String problemDescription, String solutionDescription, String trackingNumber,
                  String replacementProductDescription, String replacementTrackingNumber,OrderItem item) {
        this.problemDescription = problemDescription;
        this.solutionDescription = solutionDescription;
        this.trackingNumber = trackingNumber;
        this.deliveryConfirmationBySeller = false;
        this.replacementProductDescription = replacementProductDescription;
        this.replacementTrackingNumber = replacementTrackingNumber;
        this.buyerConfirmationOfReplacementDelivery = false;
        this.creationDate = LocalDate.now().toString();
        this.item=item;
    }
    public Ticket(String problemDescription,OrderItem item,String userPseudo){
        this.problemDescription = problemDescription;
        this.solutionDescription = null;
        this.trackingNumber = null;
        this.deliveryConfirmationBySeller = false;
        this.replacementProductDescription = null;
        this.replacementTrackingNumber = null;
        this.buyerConfirmationOfReplacementDelivery = false;
        this.creationDate = LocalDate.now().toString();
        this.item=item;
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

    // Getters et Setters

    public String getProblemDescription() {
        return problemDescription;
    }

    public String getSolutionDescription() {
        return solutionDescription;
    }

    public String getTrackingNumber() {
        return trackingNumber;
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

    public String getReplacementTrackingNumber() {
        return replacementTrackingNumber;
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