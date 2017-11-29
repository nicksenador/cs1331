import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * This class represents a chess game with data about the game and list of moves
 *
 * @author nsenador3
 * @version 1
 */
public class ChessGame {

    private StringProperty event = new SimpleStringProperty(this, "NA");
    private StringProperty site = new SimpleStringProperty(this, "NA");
    private StringProperty date = new SimpleStringProperty(this, "NA");
    private StringProperty white = new SimpleStringProperty(this, "NA");
    private StringProperty black = new SimpleStringProperty(this, "NA");
    private StringProperty result = new SimpleStringProperty(this, "NA");
    private List<String> moves;

    /**
     * Constructor for a ChessGame that takes in all instance variables.
     * @param  event  name of the game
     * @param  site   site of the game
     * @param  date   date of the game
     * @param  white  white player's name
     * @param  black  black player's name
     * @param  result result of the game
     */
    public ChessGame(String event, String site, String date,
                     String white, String black, String result) {
        this.event.set(event);
        this.site.set(site);
        this.date.set(date);
        this.white.set(white);
        this.black.set(black);
        this.result.set(result);
        moves = new ArrayList<>();
    }

    /**
     * adds a single move to the game
     * @param move valid move in a game
     */
    public void addMove(String move) {
        moves.add(move);
    }

    /**
     * returns the specified move indexed from 1
     * @param  n specified move from index 1
     * @return   String of move at n
     */
    public String getMove(int n) {
        return moves.get(n - 1);
    }

    /**
     * returns the event of this ChessGame
     * @return String of the event
     */
    public String getEvent() {
        return event.get();
    }

    /**
     * returns the site of this ChessGame
     * @return String of the site
     */
    public String getSite() {
        return site.get();
    }

    /**
     * returns the date of this ChessGame
     * @return String of the date
     */
    public String getDate() {
        return date.get();
    }

    /**
     * returns the name of the white player of this ChessGame
     * @return String of the white player's name
     */
    public String getWhite() {
        return white.get();
    }

    /**
     * returns the name of the black player of this ChessGame
     * @return String of the black player's name
     */
    public String getBlack() {
        return black.get();
    }


    /**
     * returns the result of this ChessGame
     * @return String of the result
     */
    public String getResult() {
        return result.get();
    }
}