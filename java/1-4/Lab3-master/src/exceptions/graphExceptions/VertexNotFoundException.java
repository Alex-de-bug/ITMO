package exceptions.graphExceptions;

// checked exception
public class VertexNotFoundException extends Exception {

    public VertexNotFoundException(String message) {
        System.out.println(message);
    }

    public VertexNotFoundException() {}
}
