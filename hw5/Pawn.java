/**
 * Represents a Pawn which is a piece on a chess board with a color.
 *
 * @author nsenador3
 * @version 1
 */
public class Pawn extends Piece {

    private static final String PIECE_NAME = "P";

    /**
     * Creates a Pawn calling Piece's constructor to set color to passed value
     *
     * @param color the color of this Pawn
     */
    public Pawn(Color color) {
        super(color);
    }

    @Override
    public String algebraicName() {
        return "";
    }

    @Override
    public String fenName() {
        Color color = this.getColor();
        if (color == Color.WHITE) {
            return PIECE_NAME;
        } else {
            return PIECE_NAME.toLowerCase();
        }
    }

    @Override
    public Square[] movesFrom(Square square) {
        Square[] squares = new Square[1];
        String squareString = square.toString();
        char file = squareString.charAt(0);
        char rank = squareString.charAt(1);
        Color color = this.getColor();
        if (color == Color.WHITE) {
            if (rank == '2') {
                squares = new Square[2];
                squares[0] = new Square(file, (char) (rank + 1));
                squares[1] = new Square(file, (char) (rank + 2));
            } else if (rank == '8') {
                return squares;
            } else {
                squares[0] = new Square(file, (char) (rank + 1));
            }
        } else {
            if (rank == '7') {
                squares = new Square[2];
                squares[0] = new Square(file, (char) (rank - 1));
                squares[1] = new Square(file, (char) (rank - 2));
            } else if (rank == '1') {
                return squares;
            } else {
                squares[0] = new Square(file, (char) (rank - 1));
            }
        }
        return squares;
    }
}