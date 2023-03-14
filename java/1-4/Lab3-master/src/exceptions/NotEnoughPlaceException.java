package exceptions;

public class NotEnoughPlaceException extends RuntimeException {

    public NotEnoughPlaceException(String message) {
        super(message);
    }
}
