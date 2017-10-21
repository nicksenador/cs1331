/**
 * InvalidSquareExcpetion is a RunTimeException. InvalidSquareException is an
 * unchecked exception because it is up to the user to instantiate a valid
 * square. There is no way of recovering from this invalid square because we
 * can't use an invalid square and don't know what the programmer wanted.
 *
 * @author nsenaor3
 */
public class InvalidSquareException extends RuntimeException {

    private String message;

    /**
     * This is the constructor of InvalidSquareExcpetion which sets this.message
     *
     * @param message a string containing an invalid square.
     */
    public InvalidSquareException(String message) {
        this.message = message;
    }

    /**
     * Returns invalid square.
     *
     * @return a String containing the invalid square.
     */
    public String getMessage() {
        return message;
    }
}