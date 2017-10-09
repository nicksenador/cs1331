public class KingTester {

    public static void main(String[] args) {
        Piece king = new King(Color.BLACK);
        System.out.println(king.algebraicName());
        System.out.println(king.fenName());
        Square[] legalSqures = king.movesFrom(new Square("e8"));
        for (Square sq : legalSqures) {
            System.out.println(sq.toString());
        }
    }
}