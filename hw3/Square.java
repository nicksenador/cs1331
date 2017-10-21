/**
 * Represents a square on a chess board with file and rank from a-h and 1-8
 * respectively.
 *
 * @author nseador3
 */
public class Square {

    private char file;
    private char rank;

    /**
     * Creates a Square setting the values of file and rank to the passed values
     *
     * @param file a char with values a-h
     * @param rank a char with values 1-8
     */
    public Square(char file, char rank) throws InvalidSquareException {
        if (file < 'a' || file > 'h' || rank < '1' || rank > '8') {
            throw new InvalidSquareException("" + file + rank);
        }
        this.file = file;
        this.rank = rank;
    }

    /**
     * Creates a Square by calling another constructor with char values of name.
     *
     * @param name a String containing two characters, a file and rank from a-h
     * and 1-8 respectively.
     */
    public Square(String name) throws InvalidSquareException {
        if (name.length() > 2) {
            throw new InvalidSquareException(name);
        }
        if (name.charAt(0) < 'a' || name.charAt(0) > 'h'
            || name.charAt(1) < '1' || name.charAt(1) > '8') {
            throw new InvalidSquareException(name);
        }
        this.file = name.charAt(0);
        this.rank = name.charAt(1);
    }

    /**
     * Returns this Squares' file.
     *
     * @return this Square's file.
     */
    public char getFile() {
        return file;
    }

    /**
     * Returns this Squares' rank.
     *
     * @return this Square's rank.
     */
    public char getRank() {
        return rank;
    }

    /**
     * Returns the file and rank together in a String.
     *
     * @return String version of file then rank concatenated
     */
    public String toString() {
        return "" + file + rank;
    }

    /**
     * Determines if two squares are equal.
     *
     * @param other Object used to determine if is equal to this
     * @return boolean if object is equal to this
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (null == other) {
            return false;
        }
        if (!(other instanceof Square)) {
            return false;
        }
        Square that = (Square) other;
        return (this.file == that.file) && (this.rank == that.rank);
    }

    /**
     * Not sure what this does. Used to hush checkstyle.
     *
     * @return int unimportant value used as placeholder
     */
    @Override
    public int hashCode() {
        return -1;
    }
}