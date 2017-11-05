import java.util.ArrayList;

public class myTester {

    public static void main(String[] args) {
        ArrayList<Square> arrayList = new ArrayList<>();
        arrayList.add(new Square("a1"));
        arrayList.add(new Square("a2"));
        arrayList.add(new Square("a3"));

        SquareSet set = new SquareSet(arrayList);

    }
}