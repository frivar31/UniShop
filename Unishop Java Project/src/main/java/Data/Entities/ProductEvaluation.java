package Data.Entities;

import Data.Entities.Users.Client;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductEvaluation {
    private int rating; // Note attribuée par l'acheteur
    private String comment; // Commentaire laissé par l'acheteur
    private boolean isFlagged; // Indique si l'évaluation a été signalée comme inappropriée
    private int pointsEarned; // Points attribués à l'acheteur pour cette évaluation
    private Client op;
    private Boolean deleted;

    @JsonCreator
    public ProductEvaluation(@JsonProperty("rating") int rating,
                             @JsonProperty("comment") String comment,
                             @JsonProperty("isFlagged") boolean isFlagged,
                             @JsonProperty("pointsEarned") int pointsEarned,
                             @JsonProperty("op") Client op,
                             @JsonProperty("deleted") boolean deleted) {
        this.rating = rating;
        this.comment = comment;
        this.isFlagged = isFlagged;
        this.pointsEarned = pointsEarned;
        this.op = op;
        this.deleted = deleted;
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

    // Méthode pour signaler l'évaluation comme inappropriée
    public void flagAsInappropriate() {
        if (!isFlagged) {
            this.isFlagged = true;
            op.addPoints(-pointsEarned);
            pointsEarned = 0;
        }
    }

    // Méthode pour attribuer des points à l'acheteur lorsque l'évaluation reçoit le premier "like"
    public void receiveLike() {
        if (pointsEarned == 0) {
            pointsEarned = 10;
            System.out.println("Points earned: " + pointsEarned);
            op.addPoints(10);
        }
    }

    public void delete() {
        this.deleted = true;
        op.addPoints(-pointsEarned);
        pointsEarned = 0;
    }
}
