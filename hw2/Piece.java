public abstract class Piece {

    private Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract String algebraicName();

    public abstract String fenName();

    public abstract Square[] movesFrom(Square square);

    public static Square[] getLegalSquares(Square[] squares) {
        int count = 0;
        String sqString;
        char file = '0';
        char rank = '0';
        for (Square sq : squares) {
            sqString = sq.toString();
            file = sqString.charAt(0);
            rank = sqString.charAt(1);
            if (file >= 'a' && file <= 'h') {
                if (rank >= '1' && rank <= '8') {
                    count++;
                }
            }
        }
        Square[] legalSquares = new Square[count];
        int legalSquaresIndex = 0;
        for (Square sq : squares) {
            sqString = sq.toString();
            file = sqString.charAt(0);
            rank = sqString.charAt(1);
            if (file >= 'a' && file <= 'h') {
                if (rank >= '1' && rank <= '8') {
                    legalSquares[legalSquaresIndex] = sq;
                    legalSquaresIndex+=1;
                }
            }
        }
        return legalSquares;
    }
}