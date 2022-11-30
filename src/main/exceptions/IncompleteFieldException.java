package exceptions;

public class IncompleteFieldException extends Exception {
    // EFFECTS: throws this exception with no message
    public IncompleteFieldException() {
        super();
    }

    // EFFECTS: throws this exception with the given message
    public IncompleteFieldException(String msg) {
        super(msg);
    }
}