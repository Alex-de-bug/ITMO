package exceptions.graphExceptions;

// checked exception
public class AlreadyContainsEdgeException extends Exception {

    public AlreadyContainsEdgeException(String message) {
        System.out.println(message);
    }

    public AlreadyContainsEdgeException() {}
}
