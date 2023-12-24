package Data.Entities;

import Data.Entities.Users.Client;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Représente une évaluation d'un produit effectuée par un utilisateur.
 */
public class ProductEvaluation {
    private int rating; // Note attribuée par l'acheteur
    private String comment; // Commentaire laissé par l'acheteur
    private boolean isFlagged; // Indique si l'évaluation a été signalée comme inappropriée
    private int pointsEarned; // Points attribués à l'acheteur pour cette évaluation
    private String pseudoOp;
    private int productId;
    /**
     * Constructeur de la classe ProductEvaluation.
     *
     * @param rating    La note attribuée par l'acheteur.
     * @param comment   Le commentaire laissé par l'acheteur.
     * @param pseudoOp  Le pseudonyme de l'utilisateur qui a effectué l'évaluation.
     * @param productId L'identifiant du produit évalué.
     */
    public ProductEvaluation(int rating,String comment,String pseudoOp,int productId){
        this.rating=rating;
        this.comment=comment;
        this.pseudoOp=pseudoOp;
        this.isFlagged=false;
        this.pointsEarned=0;
        this.productId=productId;
    }
    /**
     * Constructeur JSON de la classe ProductEvaluation.
     *
     * @param rating      La note attribuée par l'acheteur.
     * @param comment     Le commentaire laissé par l'acheteur.
     * @param isFlagged   Indique si l'évaluation a été signalée comme inappropriée.
     * @param pointsEarned Les points attribués à l'acheteur pour cette évaluation.
     * @param pseudoOp    Le pseudonyme de l'utilisateur qui a effectué l'évaluation.
     * @param productId   L'identifiant du produit évalué.
     */
    @JsonCreator
    public ProductEvaluation(@JsonProperty("rating") int rating, @JsonProperty("comment") String comment, @JsonProperty("isFlagged") boolean isFlagged, @JsonProperty("pointsEarned") int pointsEarned, @JsonProperty("pseudoOp") String pseudoOp, @JsonProperty("productId") int productId) {
        this.rating = rating;
        this.comment = comment;
        this.isFlagged = isFlagged;
        this.pointsEarned = pointsEarned;
        this.pseudoOp = pseudoOp;
        this.productId = productId;
    }
    /**
     * Obtient le pseudonyme de l'utilisateur qui a effectué l'évaluation.
     *
     * @return Le pseudonyme de l'utilisateur.
     */

    public String getPseudoOp() {
        return pseudoOp;
    }

    public void setPseudoOp(String pseudoOp) {
        this.pseudoOp = pseudoOp;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Vérifie si l'évaluation a été signalée comme inappropriée.
     *
     * @return {@code true} si l'évaluation a été signalée, sinon {@code false}.
     */
    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }
    /**
     * Retourne une représentation textuelle de l'évaluation du produit.
     *
     * @return Une chaîne de caractères représentant l'évaluation du produit.
     */
    @Override
    public String toString() {
        return "Évaluation du produit par " + getPseudoOp() + ":\n" +
                "Note: " + getRating() + "/5\n" +
                "Commentaire: " + getComment()+"\n";
    }

}
