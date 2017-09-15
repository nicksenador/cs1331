import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PgnReader {

    private static String[][] board = {
        {"r", "n", "b", "q", "k", "b", "n", "r"},
        {"p", "p", "p", "p", "p", "p", "p", "p"},
        {"", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", ""},
        {"P", "P", "P", "P", "P", "P", "P", "P"},
        {"R", "N", "B", "Q", "K", "B", "N", "R"},
    };

    public static void main(String[] args) {
        String game = fileContent(args[0]);
        System.out.println("Event: " + tagValue("Event", game));
        System.out.println("Site: " + tagValue("Site", game));
        System.out.println("Date: " + tagValue("Date", game));
        System.out.println("Round: "  + tagValue("Round", game));
        System.out.println("White: " + tagValue("White", game));
        System.out.println("Black: " + tagValue("Black", game));
        System.out.println("Result: " + tagValue("Result", game));
        System.out.println("Final Position:");
        System.out.println(finalPosition(game));
    }

    public static String fileContent(String path) {
        Path file = Paths.get(path);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.exit(1);
        }
        return sb.toString();
    }

    public static String tagValue(String tagName, String gameInPgn) {
        int tagIndex = gameInPgn.indexOf(tagName);
        if (tagIndex > 0) {
            StringBuilder sb = new StringBuilder();
            int i = tagIndex + tagName.length();
            while (gameInPgn.charAt(i) != '"') {
                i++;
            }
            i++;
            // i is at index of the first character of tag value
            while (gameInPgn.charAt(i) != '"') {
                sb.append(gameInPgn.charAt(i));
                i++;
            }
            return sb.toString();
        } else {
            return "NOT GIVEN";
        }
    }

    public static String finalPosition(String gameInPgn) {
        String[] splitGame = gameInPgn.split("\\n1\\. ");
        String[] moves = splitGame[1].split("\\s");
        boolean whitesTurn = true;
        for (int i = 0; i < moves.length; i++) {
            if (!moves[i].contains(".")) {
                determinePiece(whitesTurn, moves[i]);
                whitesTurn = !whitesTurn;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            int emptyTileCount = 0;
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].equals("")) {
                    emptyTileCount++;
                    if (j == (board[i].length - 1)) {
                        sb.append(emptyTileCount);
                    }
                } else if (emptyTileCount != 0) {
                    sb.append(emptyTileCount + board[i][j]);
                    emptyTileCount = 0;
                } else {
                    sb.append(board[i][j]);
                }
            }
            if (i != (board.length - 1)) {
                sb.append("/");
            }
        }
        return sb.toString();
    }

    public static void determinePiece(boolean whitesTurn, String movement) {
        char firstChar = movement.charAt(0);
        switch (firstChar) {
        case 'N':
            movePiece(whitesTurn, firstChar,
                movement.substring(1, movement.length() - 1));
            break;
        case 'B':
            movePiece(whitesTurn, firstChar,
                movement.substring(1, movement.length() - 1));
            break;
        case 'R':
            movePiece(whitesTurn, firstChar,
                movement.substring(1, movement.length() - 1));
            break;
        case 'Q':
            movePiece(whitesTurn, firstChar,
                movement.substring(1, movement.length() - 1));
            break;
        case 'K':
            movePiece(whitesTurn, firstChar,
                movement.substring(1, movement.length() - 1));
            break;
        case 'O':
            break;
        case 'a': case 'b': case 'c': case 'd':
        case 'e': case 'f': case 'g': case 'h':
            movePiece(whitesTurn, 'p', movement);
            break;
        default:
        // Game over
            break;
        }
    }

    public static void movePiece(boolean whitesTurn, char piece,
        String movement) {
        if (whitesTurn) {
            piece = Character.toUpperCase(piece);
        } else {
            piece = Character.toLowerCase(piece);
        }
        if (piece == 'p' || piece == 'P') {
            if (movement.charAt(1) != 'x') {
                int column = determineColumn(movement.charAt(0));
                int row = determineRow(movement.charAt(1));
                if (whitesTurn) {
                    if (board[row + 1][column] == String.valueOf(piece)) {
                        rewriteBoard(row + 1, column, piece,
                            row, column);
                    } else {
                        rewriteBoard(row + 2, column, piece,
                            row, column);
                    }
                } else {
                    System.out.println(movement);
                    System.out.println(row);
                    if (board[row - 1][column] == String.valueOf(piece)) {
                        rewriteBoard(row - 1, column, piece,
                            row, column);
                    } else {
                        rewriteBoard(row - 2, column, piece,
                            row, column);
                    }
                }
            } // TODO: implement x
        }
    }

    public static int determineColumn(char columnLetter) {
        switch (columnLetter) {
        case 'a':
            return 0;
        case 'b':
            return 1;
        case 'c':
            return 2;
        case 'd':
            return 3;
        case 'e':
            return 4;
        case 'f':
            return 5;
        case 'g':
            return 6;
        default:
            return 7;
        }
    }

    public static int determineRow(char row) {
        switch (row) {
        case 1:
            return 7;
        case 2:
            return 6;
        case 3:
            return 5;
        case 4:
            return 4;
        case 5:
            return 3;
        case 6:
            return 2;
        case 7:
            return 1;
        default:
            return 0;
        }
    }

    public static void rewriteBoard(int deletedRow, int deletedColumn,
        char piece, int updatedRow, int updatedColumn) {
        System.out.println(deletedRow + " " + deletedColumn + " " + piece + " " + updatedRow + " " + updatedColumn);
        String[][] newBoard = new String[8][8];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == deletedRow && j == deletedColumn) {
                    newBoard[i][j] = "";
                } else if (i == updatedRow && j == updatedColumn) {
                    newBoard[i][j] = String.valueOf(piece);
                } else {
                    newBoard[i][j] = board[i][j];
                }
            }
        }
        board = newBoard;
    }
}