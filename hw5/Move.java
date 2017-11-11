/**
 * Represents a move in a chess game which consists of two Plys which are
 * individual players' moves.
 *
 * @author nsenador3
 * @version 1
 */
public class Move {

    private Ply whitePly;
    private Ply blackPly;

    /**
     * Creates a Move object assigning variables to passed arguments.
     *
     * @param whitePly the white player's move
     * @param blackPly the black player's move
     */
    public Move(Ply whitePly, Ply blackPly) {
        this.whitePly = whitePly;
        this.blackPly = blackPly;
    }

    /**
     * Returns white's Ply
     *
     * @return white player's Ply
     */
    public Ply getWhitePly() {
        return whitePly;
    }

    /**
     * Returns black's Ply
     *
     * @return black player's Ply
     */
    public Ply getBlackPly() {
        return blackPly;
    }
}