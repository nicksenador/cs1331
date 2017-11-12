/**
 * Represents a Knight which is a piece on a chess board with a color.
 *
 * @author nsenador3
 * @version 1
 */
public class Knight extends Piece {

    private static final String PIECE_NAME = "N";

    /**
     * Creates a Knight calling Piece's constructor to set color to passed value
     *
     * @param color the color of this Knight
     */
    public Knight(Color color) {
        super(color);
    }

    @Override
    public String algebraicName() {
        return PIECE_NAME;
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
        Square[] squares = new Square[8];
        String squareString = square.toString();
        char file = squareString.charAt(0);
        char rank = squareString.charAt(1);
        try {
            squares[0] = new Square((char) (file - 1), (char) (rank - 2));
        } catch (Exception e) {
            int i;
        }
        try {
            squares[1] = new Square((char) (file - 1), (char) (rank + 2));
        } catch (Exception e) {
            int i;
        }
        try {
            squares[2] = new Square((char) (file - 2), (char) (rank - 1));
        } catch (Exception e) {
            int i;
        }
        try {
            squares[3] = new Square((char) (file - 2), (char) (rank + 1));
        } catch (Exception e) {
            int i;
        }
        try {
            squares[4] = new Square((char) (file + 1), (char) (rank - 2));
        } catch (Exception e) {
            int i;
        }
        try {
            squares[5] = new Square((char) (file + 1), (char) (rank + 2));
        } catch (Exception e) {
            int i;
        }
        try {
            squares[6] = new Square((char) (file + 2), (char) (rank - 1));
        } catch (Exception e) {
            int i;
        }
        try {
            squares[7] = new Square((char) (file + 2), (char) (rank + 1));
        } catch (Exception e) {
            int i;
        }
        Square[] legalSquares = getLegalSquares(squares);
        return legalSquares;
    }
}