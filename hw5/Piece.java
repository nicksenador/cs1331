/**
 * Represents a piece on the chess board that has a color.
 *
 * @author nsenador3
 * @version 1
 */
public abstract class Piece {

    private Color color;

    /**
     * Creates a Piece setting color to the passed value.
     *
     * @param color color of this piece
     */
    public Piece(Color color) {
        this.color = color;
    }

    /**
     * Returns the color of this instance variable.
     *
     * @return color the color of called instance of Piece
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns alebraic name of called Piece.
     *
     * @return the algebraic name of called Piece
     */
    public abstract String algebraicName();

    /**
     * Returns the FEN name of called Piece.
     *
     * @return the FEN name of called Piece
     */
    public abstract String fenName();

    /**
     * Returns a Square array with all possible Squares this Piece can move to
     * from square.
     *
     * @param square the Square the called Piece is currently on
     * @return a square array of all the possible Squares on the chess board the
     *         called piece can move
     */
    public abstract Square[] movesFrom(Square square);

    /**
     * Takes in an array of Squares and returns a new array containing moves
     * that are only possible on a chess board.
     *
     * @param squares array of Squares containing all possible moves if chess
     *        board borders are ignored
     * @return array of Squares containing only possible moves on a chess board
     */
    public static Square[] getLegalSquares(Square[] squares) {
        int count = 0;
        String squareString;
        char file = '0';
        char rank = '0';
        for (int i = 0; i < squares.length; i++) {
            if (squares[i] == null) {
                continue;
            }
            Square sq = squares[i];
            squareString = sq.toString();
            file = squareString.charAt(0);
            rank = squareString.charAt(1);
            if (file >= 'a' && file <= 'h') {
                if (rank >= '1' && rank <= '8') {
                    count++;
                }
            }
        }
        Square[] legalSquares = new Square[count];
        int legalSquaresIndex = 0;
        for (int i = 0; i < squares.length; i++) {
            if (squares[i] == null) {
                continue;
            }
            Square sq = squares[i];
            squareString = sq.toString();
            file = squareString.charAt(0);
            rank = squareString.charAt(1);
            if (file >= 'a' && file <= 'h') {
                if (rank >= '1' && rank <= '8') {
                    legalSquares[legalSquaresIndex] = sq;
                    legalSquaresIndex += 1;
                }
            }
        }
        return legalSquares;
    }
}