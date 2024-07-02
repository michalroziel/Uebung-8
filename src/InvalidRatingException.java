package src;

public class InvalidRatingException extends Exception{

    public InvalidRatingException(String message) {
        super(message);

        System.out.println("Invalid rating: " + message);
    }
}
