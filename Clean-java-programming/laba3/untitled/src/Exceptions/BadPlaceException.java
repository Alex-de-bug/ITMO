package Exceptions;

public class BadPlaceException extends NullPointerException{

    public BadPlaceException() {
        super("place is null");
    }

    @Override
    public String toString() {
        return "\n Ошибка, место не может иметь значение null!\n";
    }
}
