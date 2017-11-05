/**
 * InvalidSquareExcpetion is a RunTimeException. InvalidSquareException is an
 * unchecked exception because it is up to the user to instantiate a valid
 * square. There is no way of recovering from this invalid square because we
 * can't use an invalid square and don't know what the programmer wanted.
 *
 * @author nsenaor3
 * @version 1
 */
public class InvalidSquareException extends RuntimeException {

    /**
     * This is the constructor of InvalidSquareExcpetion which calls the super
     * constructor.
     *
     * @param message a string containing an invalid square.
     */
    public InvalidSquareException(String message) {
        super(message);
    }

}