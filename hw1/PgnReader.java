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
        return "";
    }
}