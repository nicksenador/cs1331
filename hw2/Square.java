public class Square {

    private char file;
    private char rank;

    public Square(char file, char rank) {
        this.file = file;
        this.rank = rank;
    }

    public Square(String name) {
        this(name.charAt(0), name.charAt(1));
    }

    public String toString() {
        return "" + file + rank;
    }

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

    @Override
    public int hashCode() {
        return -1;
    }
}