import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Represents a chess game which includes a list of Moves
 *
 * @author nsenador3
 * @version 1
 */
public class ChessGame {

    private List<Move> moves;

    /**
     * Creates a ChessGame setting moves to this class's List
     *
     * @param moves a list of Moves
     */
    public ChessGame(List<Move> moves) {
        this.moves = moves;
    }

    /**
     * Returns this ChessGame's list of moves
     *
     * @return List of type moves of moves
     */
    public List<Move> getMoves() {
        return moves;
    }

    /**
     * Returns specified move of position n
     *
     * @param n index of requested move
     * @return the Move at index n
     */
    public Move getMove(int n) {
        return moves.get(n);
    }

    /**
     * Returns a List of Moves filtered by the specifed Predicate
     *
     * @param filter a predicate defining what needs to be filtered
     * @return a List of Moves filtered by filter
     */
    public List<Move> filter(Predicate<Move> filter) {
        List<Move> filteredMoves = new ArrayList<>();
        for (Move m : moves) {
            if (filter.test(m)) {
                filteredMoves.add(m);
            }
        }
        return filteredMoves;
    }

    /**
     * Returns a List of moves that contain any comments(white or black) from
     * this ChessGame's List of Moves
     *
     * @return a List of moves with comments
     */
    public List<Move> getMovesWithComment() {
        return filter((Move m) -> {
                return (m.getWhitePly().getComment().isPresent())
                    || (m.getBlackPly().getComment().isPresent());
            });
    }

    /**
     * Returns a List of moves that contain no comments(white or black) from
     * this ChessGame's List of Moves
     *
     * @return a list of moves containing no comments
     */
    public List<Move> getMovesWithoutComment() {
        return filter(new Predicate<Move>() {
            @Override
            public boolean test(Move m) {
                return (!m.getWhitePly().getComment().isPresent())
                    && (!m.getBlackPly().getComment().isPresent());
            }
        });
    }

    /**
     * Returns a List of Moves that contain the specified Piece
     *
     * @param p specified Piece
     * @return List of Moves containing the specified Piece
     */
    public List<Move> getMovesWithPiece(Piece p) {
        class MovePredicate implements Predicate<Move> {
            @Override
            public boolean test(Move m) {
                return (m.getWhitePly().getPiece().getClass()
                    .equals(p.getClass()))
                    || (m.getBlackPly().getPiece().getClass()
                        .equals(p.getClass()));
            }
        }

        return filter(new MovePredicate());
    }
}