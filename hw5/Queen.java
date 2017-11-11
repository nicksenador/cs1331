/**
 * Represents a Queen which is a piece on a chess board with a color.
 *
 * @author nsenador3
 * @version 1
 */
public class Queen extends Piece {

    private static final String PIECE_NAME = "Q";

    /**
     * Creates a Queen calling Piece's constructor to set color to passed value
     *
     * @param color the color of this Queen
     */
    public Queen(Color color) {
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
        Square[] squares = new Square[56];
        String squareString = square.toString();
        char file = squareString.charAt(0);
        char rank = squareString.charAt(1);
        for (int i = 1; i < 8; i++) {
            int squaresIndex = ((i - 1) * 8);
            squares[squaresIndex] = new Square((char) (file + i), rank);
            squares[squaresIndex + 1] = new Square((char) (file + i),
                (char) (rank + i));
            squares[squaresIndex + 2] = new Square(file, (char) (rank + i));
            squares[squaresIndex + 3] = new Square((char) (file - i),
                (char) (rank + i));
            squares[squaresIndex + 4] = new Square((char) (file - i), rank);
            squares[squaresIndex + 5] = new Square((char) (file - i),
                (char) (rank - i));
            squares[squaresIndex + 6] = new Square(file, (char) (rank - i));
            squares[squaresIndex + 7] = new Square((char) (file + i),
                (char) (rank - i));
        }
        Square[] legalSquares = getLegalSquares(squares);
        return legalSquares;
    }
}