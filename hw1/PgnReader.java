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

    private static boolean enPassant = false;

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
        String[] moves;
        if (splitGame.length == 2) {
            moves = splitGame[1].split("\\s");
        } else {
            moves = splitGame[0].split("\\s");
        }
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
                movement.substring(1, movement.length()));
            break;
        case 'B':
            movePiece(whitesTurn, firstChar,
                movement.substring(1, movement.length()));
            break;
        case 'R':
            movePiece(whitesTurn, firstChar,
                movement.substring(1, movement.length()));
            break;
        case 'Q':
            movePiece(whitesTurn, firstChar,
                movement.substring(1, movement.length()));
            break;
        case 'K':
            movePiece(whitesTurn, firstChar,
                movement.substring(1, movement.length()));
            break;
        case 'O':
            performCastle(whitesTurn, movement);
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
        boolean enPassantThisMove = false;
        piece = determinePieceCaps(piece, whitesTurn);
        if (piece == 'p' || piece == 'P') {
            if (movement.contains("=")) {
                piece = movement.charAt(movement.indexOf("=") + 1);
                piece = determinePieceCaps(piece, whitesTurn);
            }
            if (movement.charAt(1) != 'x') {
                int column = determineColumn(movement.charAt(0));
                int row = determineRow(movement.charAt(1));
                if (whitesTurn) {
                    if (board[row + 1][column].equals(String.valueOf(piece))) {

                        writeToBoard(row + 1, column, piece,
                            row, column);
                    } else {
                        enPassantThisMove = true;
                        writeToBoard(row + 2, column, piece,
                            row, column);
                    }
                } else {
                    if (board[row - 1][column].equals(String.valueOf(piece))) {
                        writeToBoard(row - 1, column, piece,
                            row, column);
                    } else {
                        enPassantThisMove = true;
                        writeToBoard(row - 2, column, piece,
                            row, column);
                    }
                }
            } else {
                int column = determineColumn(movement.charAt(2));
                int row = determineRow(movement.charAt(3));
                int originalColumn = determineColumn(movement.charAt(0));
                int originalRow = row + 1;
                if (!whitesTurn) {
                    originalRow = row - 1;
                }
                writeToBoard(originalRow, originalColumn, piece, row, column);
                if (enPassant) {
                    if (whitesTurn) {
                        board[row + 1][column] = "";
                    } else {
                        board[row - 1][column] = "";
                    }
                    enPassant = false;
                }
            }
        } else if (piece == 'n' || piece == 'N') {
            if (movement.indexOf("x") > -1) {
                movement = movement.substring(movement.indexOf("x") + 1,
                    movement.length());
            }
            int column = determineColumn(movement.charAt(0));
            int row = determineRow(movement.charAt(1));
            int[][] possibleOriginations = getKnightOriginations(row, column);
            for (int i = 0; i < possibleOriginations.length; i++) {
                int possibleRow = possibleOriginations[i][0];
                int possibleColumn = possibleOriginations[i][1];
                if (possibleRow >= 0 && possibleRow <= 7) {
                    if (possibleColumn >= 0 && possibleColumn <= 7) {
                        if (board[possibleRow][possibleColumn]
                            .equals(String.valueOf(piece))) {
                            writeToBoard(possibleRow, possibleColumn, piece,
                                row, column);
                        }
                    }
                }
            }
        } else if (piece == 'b' || piece == 'B') {
            if (movement.indexOf("x") > -1) {
                movement = movement.substring(movement.indexOf("x") + 1,
                    movement.length());
            }
            int column = determineColumn(movement.charAt(0));
            int row = determineRow(movement.charAt(1));
            int[] possibleOrigination = checkBishopOrigin(row, column, piece,
                movement);
            if (possibleOrigination[0] == -1) {
                System.out.println("Error finding origin of bishop movement: "
                    + movement);
                System.exit(1);
            }
            int originalRow = possibleOrigination[0];
            int originalColumn = possibleOrigination[1];
            writeToBoard(originalRow, originalColumn, piece, row, column);
        } else if (piece == 'r' || piece == 'R') {
            if (movement.indexOf("x") > -1) {
                movement = movement.substring(movement.indexOf("x") + 1,
                    movement.length());
            }
            int column = determineColumn(movement.charAt(0));
            int row = determineRow(movement.charAt(1));
            int[] origination = getRookOrigination(row, column, piece);
            if (origination[0] == -1) {
                System.out.println("Error finding origin of rook movement: "
                    + movement);
                System.exit(1);
            }
            int originalRow = origination[0];
            int originalColumn = origination[1];
            writeToBoard(originalRow, originalColumn, piece, row, column);
        } else if (piece == 'q' || piece == 'Q') {
            if (movement.indexOf("x") > -1) {
                movement = movement.substring(movement.indexOf("x") + 1,
                    movement.length());
            }
            int column = determineColumn(movement.charAt(0));
            int row = determineRow(movement.charAt(1));
            int[] possibleOrigination = checkBishopOrigin(row, column, piece,
                movement);
            if (possibleOrigination[0] == -1) {
                possibleOrigination = getRookOrigination(row, column, piece);
            }
            if (possibleOrigination[0] == -1) {
                System.out.println("Error finding origin of queen movement: "
                    + movement);
                System.exit(1);
            }
            int originalRow = possibleOrigination[0];
            int originalColumn = possibleOrigination[1];
            writeToBoard(originalRow, originalColumn, piece, row, column);
        } else if (piece == 'k' || piece == 'K') {
            if (movement.indexOf("x") > -1) {
                movement = movement.substring(movement.indexOf("x") + 1,
                    movement.length());
            }
            int column = determineColumn(movement.charAt(0));
            int row = determineRow(movement.charAt(1));
            int[] possibleOrigination = getKingOrigination(row, column, piece);
            if (possibleOrigination[0] == -1) {
                System.out.println("Error finding origin of king movement: "
                    + movement);
                System.exit(1);
            }
            int originalRow = possibleOrigination[0];
            int originalColumn = possibleOrigination[1];
            writeToBoard(originalRow, originalColumn, piece, row, column);
        }
        if (enPassantThisMove) {
            enPassant = true;
        } else {
            enPassant = false;
        }
    }

    public static char determinePieceCaps(char piece, boolean whitesTurn) {
        if (whitesTurn) {
            piece = Character.toUpperCase(piece);
        } else {
            piece = Character.toLowerCase(piece);
        }
        return piece;
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
        case 'h':
            return 7;
        default:
            System.out.println("Error determining column: " + columnLetter);
            System.exit(1);
            return -1;
        }
    }

    public static int determineRow(char row) {
        switch (row) {
        case '1':
            return 7;
        case '2':
            return 6;
        case '3':
            return 5;
        case '4':
            return 4;
        case '5':
            return 3;
        case '6':
            return 2;
        case '7':
            return 1;
        case '8':
            return 0;
        default:
            System.out.println("Error determining row: " + row);
            System.exit(1);
            return -1;
        }
    }

    public static void writeToBoard(int deletedRow, int deletedColumn,
        char piece, int updatedRow, int updatedColumn) {
        board[deletedRow][deletedColumn] = "";
        board[updatedRow][updatedColumn] = String.valueOf(piece);
    }

    public static int[][] getKnightOriginations(int row, int column) {
        int[][] possibleOrigination = new int[8][2];
        possibleOrigination[0] = new int[]{row - 1, column - 2};
        possibleOrigination[1] = new int[]{row - 1, column + 2};
        possibleOrigination[2] = new int[]{row - 2, column - 1};
        possibleOrigination[3] = new int[]{row - 2, column + 1};
        possibleOrigination[4] = new int[]{row + 1, column - 2};
        possibleOrigination[5] = new int[]{row + 1, column + 2};
        possibleOrigination[6] = new int[]{row + 2, column - 1};
        possibleOrigination[7] = new int[]{row + 2, column + 1};
        return possibleOrigination;
    }

    public static int[] checkBishopOrigin(int row, int column, char piece,
        String movement) {
        int[] possibleOrigination = checkTopLeft(row, column, piece);
        if (possibleOrigination[0] == -1) {
            possibleOrigination = checkTopRight(row, column, piece);
            if (possibleOrigination[0] == -1) {
                possibleOrigination = checkBottomLeft(row, column, piece);
                if (possibleOrigination[0] == -1) {
                    possibleOrigination = checkBottomRight(row, column, piece);
                    if (possibleOrigination[0] == -1) {
                        possibleOrigination = getRookOrigination(row, column,
                            piece);
                    }
                }
            }
        }
        return possibleOrigination;
    }

    public static int[] checkTopLeft(int row, int column, char piece) {
        String stringPiece = String.valueOf(piece);
        int[] possibleOrigination = {-1, -1};
        int i = row - 1;
        int j = column - 1;
        String testPiece = "";
        while (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
            testPiece = board[i][j];
            if (testPiece.equals(stringPiece)) {
                possibleOrigination[0] = i;
                possibleOrigination[1] = j;
                return possibleOrigination;
            } else if (!testPiece.equals("")) {
                return possibleOrigination;
            }
            i -= 1;
            j -= 1;
        }
        return possibleOrigination;
    }

    public static int[] checkTopRight(int row, int column, char piece) {
        String stringPiece = String.valueOf(piece);
        int[] possibleOrigination = {-1, -1};
        int i = row - 1;
        int j = column + 1;
        String testPiece = "";
        while (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
            testPiece = board[i][j];
            if (testPiece.equals(stringPiece)) {
                possibleOrigination[0] = i;
                possibleOrigination[1] = j;
                return possibleOrigination;
            } else if (!testPiece.equals("")) {
                return possibleOrigination;
            }
            i -= 1;
            j += 1;
        }
        return possibleOrigination;
    }

    public static int[] checkBottomLeft(int row, int column, char piece) {
        String stringPiece = String.valueOf(piece);
        int[] possibleOrigination = {-1, -1};
        int i = row + 1;
        int j = column - 1;
        String testPiece = "";
        while (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
            testPiece = board[i][j];
            if (testPiece.equals(stringPiece)) {
                possibleOrigination[0] = i;
                possibleOrigination[1] = j;
                return possibleOrigination;
            } else if (!testPiece.equals("")) {
                return possibleOrigination;
            }
            i += 1;
            j -= 1;
        }
        return possibleOrigination;
    }

    public static int[] checkBottomRight(int row, int column, char piece) {
        String stringPiece = String.valueOf(piece);
        int[] possibleOrigination = {-1, -1};
        int i = row + 1;
        int j = column + 1;
        String testPiece = "";
        while (i >= 0 && i <= 7 && j >= 0 && j <= 7) {
            testPiece = board[i][j];
            if (testPiece.equals(stringPiece)) {
                possibleOrigination[0] = i;
                possibleOrigination[1] = j;
                return possibleOrigination;
            } else if (!testPiece.equals("")) {
                return possibleOrigination;
            }
            i += 1;
            j += 1;
        }
        return possibleOrigination;
    }

    public static int[] getRookOrigination(int row, int column, char piece) {
        String stringPiece = String.valueOf(piece);
        int[] possibleOrigination = {-1, -1};
        int i = row - 1;
        String testPiece = "";
        while (i >= 0 && i <= 7) {
            testPiece = board[i][column];
            if (testPiece.equals(stringPiece)) {
                possibleOrigination[0] = i;
                possibleOrigination[1] = column;
                return possibleOrigination;
            } else if (!testPiece.equals("")) {
                i = 100;
            }
            i -= 1;
        }
        i = row + 1;
        while (i >= 0 && i <= 7) {
            testPiece = board[i][column];
            if (testPiece.equals(stringPiece)) {
                possibleOrigination[0] = i;
                possibleOrigination[1] = column;
                return possibleOrigination;
            } else if (!testPiece.equals("")) {
                i = 100;
            }
            i += 1;
        }
        i = column - 1;
        while (i >= 0 && i <= 7) {
            testPiece = board[row][i];
            if (testPiece.equals(stringPiece)) {
                possibleOrigination[0] = row;
                possibleOrigination[1] = i;
                return possibleOrigination;
            } else if (!testPiece.equals("")) {
                i = 100;
            }
            i -= 1;
        }
        i = column + 1;
        while (i >= 0 && i <= 7) {
            testPiece = board[row][i];
            if (testPiece.equals(stringPiece)) {
                possibleOrigination[0] = row;
                possibleOrigination[1] = i;
                return possibleOrigination;
            } else if (!testPiece.equals("")) {
                i = 100;
            }
            i += 1;
        }
        return possibleOrigination;
    }

    public static void performCastle(boolean whitesTurn, String movement) {
        if (movement.contains("O-O-O")) {
            if (whitesTurn) {
                writeToBoard(7, 4, 'K', 7, 2);
                writeToBoard(7, 0, 'R', 7, 3);
            } else {
                writeToBoard(0, 4, 'k', 0, 2);
                writeToBoard(0, 0, 'r', 0, 3);
            }
        } else {
            if (whitesTurn) {
                writeToBoard(7, 4, 'K', 7, 6);
                writeToBoard(7, 7, 'R', 7, 5);
            } else {
                writeToBoard(0, 4, 'k', 0, 6);
                writeToBoard(0, 7, 'r', 0, 5);
            }
        }
    }

    public static int[] getKingOrigination(int row, int column, char piece) {
        String stringPiece = String.valueOf(piece);
        int[] possibleOrigination = {-1, -1};
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((row + i) >= 0 && (row + i) <= 7) {
                    if ((column + j) >= 0 && (column + j) <= 7) {
                        if (board[row + i][column + j].equals(stringPiece)) {
                            possibleOrigination[0] = row + i;
                            possibleOrigination[1] = column + j;
                        }
                    }
                }
            }
        }
        return possibleOrigination;
    }
}