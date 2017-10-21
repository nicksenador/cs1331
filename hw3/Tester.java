public class Tester {

    public static void main(String[] args) {
        try {
            new Square("a1");
        } catch (InvalidSquareException e) {
            System.out.println("InvalidSquareException for valid square: " + e.getMessage());
        }
        try {
            String invalidSquare = "a9";
            new Square(invalidSquare);
        } catch (InvalidSquareException e) {
            System.out.println("No InvalidSquareException for invalid square: " + e.getMessage());
        }
        try {
            new Square('a', '1');
        } catch (InvalidSquareException e) {
            System.out.println("InvalidSquareException for valid square: " + e.getMessage());
        }
        try {
            new Square('a', '9');
        } catch (InvalidSquareException e) {
            System.out.println("InvalidSquareException for valid square: " + e.getMessage());
        }
        try {
            new Square('i', '1');
        } catch (InvalidSquareException e) {
            System.out.println("InvalidSquareException for valid square: " + e.getMessage());
        }
        try {
            new Square("i1");
        } catch (InvalidSquareException e) {
            System.out.println("InvalidSquareException for valid square: " + e.getMessage());
        }
        try {
            new Square("a0");
        } catch (InvalidSquareException e) {
            System.out.println("InvalidSquareException for valid square: " + e.getMessage());
        }
        try {
            new Square('a', '0');
        } catch (InvalidSquareException e) {
            System.out.println("InvalidSquareException for valid square: " + e.getMessage());
        }
        Square s = new Square("f9");
        System.out.println('f' == s.getFile());
        System.out.println('7' == s.getRank());
        Square s2 = new Square('e', '4');
        System.out.println("e4".equals(s2.toString()));
    }
}