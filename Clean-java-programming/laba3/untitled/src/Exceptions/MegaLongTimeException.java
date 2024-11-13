package Exceptions;

public class MegaLongTimeException extends Exception{
    public MegaLongTimeException() {
        super("Too long");
    }

    @Override
    public String toString() {
        return "\n Ошибка, животное не может гулять более 24 часов!\n";
    }
}
