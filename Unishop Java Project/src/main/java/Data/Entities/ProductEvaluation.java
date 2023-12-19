package Data.Entities;

import Data.Entities.Users.Client;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductEvaluation {
    private int rating; // Note attribuée par l'acheteur
    private String comment; // Commentaire laissé par l'acheteur
    private boolean isFlagged; // Indique si l'évaluation a été signalée comme inappropriée
    private int pointsEarned; // Points attribués à l'acheteur pour cette évaluation
    private String pseudoOp;
    private int productId;

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

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

    @JsonCreator
    public ProductEvaluation(@JsonProperty("rating") int rating,
                             @JsonProperty("comment") String comment,
                             @JsonProperty("isFlagged") boolean isFlagged,
                             @JsonProperty("pointsEarned") int pointsEarned,
                             @JsonProperty("pseudoOp") String pseudoOp,
                             @JsonProperty("productId") int productId) {
        this.rating = rating;
        this.comment = comment;
        this.isFlagged = isFlagged;
        this.pointsEarned = pointsEarned;
        this.pseudoOp = pseudoOp;
        this.productId=productId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

}
