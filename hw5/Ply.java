import java.util.Optional;

/**
 * Represents a players move in a game of chess.
 *
 * @author nsenador3
 * @version 1
 */
public class Ply {

    private Piece piece;
    private Square from;
    private Square to;
    private Optional<String> comment;

    /**
     * Creates a Ply object assigning all variables to supplied arguments.
     *
     * @param piece the Move's Piece
     * @param from the Piece's old Square
     * @param to the Piece's new Square
     * @param comment Optional comment for this player's Move
     */
    public Ply(Piece piece, Square from, Square to, Optional<String> comment) {
        this.piece = piece;
        this.from = from;
        this.to = to;
        this.comment = comment;
    }

    /**
     * Returns this Ply's Piece
     *
     * @return this Ply's Piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Returns this Ply's Piece's previous Square
     *
     * @return this Ply's Piece's previous Square
     */
    public Square getFrom() {
        return from;
    }

    /**
     * Returns this Ply's Piece's new Square
     *
     * @return this Ply's Piece's new Square
     */
    public Square getTo() {
        return to;
    }


    /**
     * Returns the optional comment for this Ply
     *
     * @return Optional of type String for this Ply
     */
    public Optional<String> getComment() {
        return comment;
    }
}