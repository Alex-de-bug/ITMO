package exceptions;

// checked exception
public class FreePlaceNotFoundException extends Exception {

    public FreePlaceNotFoundException(String message) {
        System.out.println(message);
    }

    public FreePlaceNotFoundException() {}
}
