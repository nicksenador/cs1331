public class PgnReader {

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

    public static String tagValue(String tagName, String gameInPgn) {

    }

    public static String finalPosition(String gameInPgn) {

    }
}