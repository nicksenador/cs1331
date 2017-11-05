import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * A set of valid Squares which implements Set of Squares.
 * @author nsenador3
 * @version 1
 */
public class SquareSet implements Set<Square> {

    private class SquareSetIterator implements Iterator<Square> {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public Square next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return squareSet[cursor++];
        }

    }

    private Square[] squareSet;
    private int size;

    /**
     * Creates a new Square set with size 0 and initial capacity of size 10
     */
    public SquareSet() {
        squareSet = new Square[10];
        size = 0;
    }

    /**
     * Creates a new Square set with size 0 and initial capacity of size 10, and
     * then calls addAll for c
     *
     * @param c Collection of type Square preferably containing valid Squares
     * and no nulls
     */
    public SquareSet(Collection<Square> c) {
        squareSet = new Square[10];
        size = 0;
        this.addAll(c);
    }

    @Override
    public boolean add(Square square) {
        // check if square is valid to add
        if (null == square) {
            throw new NullPointerException();
        } else if (!isValid(square)) {
            throw new InvalidSquareException(square.toString());
        } else if (this.contains(square)) {
            return false;
        }

        // check if squareSet if full before adding
        if (size == squareSet.length) {
            this.increaseSize();
        }
        squareSet[size] = square;
        size += 1;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Square> c) {
        boolean change = false;
        // check that collection c contains all valid squares
        for (Square sq : c) {
            if (null == sq) {
                throw new NullPointerException();
            } else if (!isValid(sq)) {
                throw new InvalidSquareException(sq.toString());
            }
        }

        for (Square sq : c) {
            if (!(this.contains(sq))) {
                change = true;
                this.add(sq);
            }
        }
        return change;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Square)) {
            return false;
        }
        Square sq = (Square) o;
        for (int i = 0; i < size; i++) {
            if (squareSet[i].equals(sq)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!(contains(o))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (null == other) {
            return false;
        }
        if (!(other instanceof Set)) {
            return false;
        }
        Set that = (Set) other;
        if (!(this.size() == that.size())) {
            return false;
        }
        return this.containsAll(that);
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (int i = 0; i < size; i++) {
            hashCode += squareSet[i].hashCode();
        }
        return hashCode;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public Iterator<Square> iterator() {
        return new SquareSetIterator();
    }

    @Override
    public boolean remove(Object o) {
        if (!(this.contains(o))) {
            return false;
        }
        Square otherSq = (Square) o;
        int i = 0;
        for (Square sq : this) {
            if (sq.equals(otherSq)) {
                squareSet[i] = squareSet[size - 1];
                squareSet[size - 1] = null;
                size--;
                return true;
            }
            i++;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Square[] newSquareSetArray = new Square[size];
        for (int i = 0; i < size; i++) {
            newSquareSetArray[i] = squareSet[i];
        }
        return newSquareSetArray;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) new Square[size];
        } else if (a.length > size) {
            a[size] = null;
        }
        int i = 0;
        for (Square sq : this) {
            a[i] = (T) sq;
            i++;
        }
        return a;
    }

    private static boolean isValid(Square sq) {
        char file = sq.getFile();
        char rank = sq.getRank();
        return !(file < 'a' || file > 'h' || rank < '1' || rank > '8');
    }

    private void increaseSize() {
        int newLength = squareSet.length * 2;
        Square[] newSquareSet = new Square[newLength];
        for (int i = 0; i < squareSet.length; i++) {
            newSquareSet[i] = squareSet[i];
        }
        this.squareSet = newSquareSet;
    }

}