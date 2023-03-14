package exceptions.graphExceptions;

// checked exception
public class AlreadyContainsVertexException extends Exception {

    public AlreadyContainsVertexException(String message) {
        System.out.println(message);
    }

    public AlreadyContainsVertexException() {}
}
