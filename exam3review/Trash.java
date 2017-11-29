import java.util.*;
import java.lang.Comparable;

public class Trash implements Comparable<Trash> {

    static class OldBanana extends Trash {

        public OldBanana(String name, int age, boolean isStinky) {
            super(name, age, isStinky);
        }
    }

    static class TrashComparator implements Comparator<Trash> {

        @Override
        public int compare(Trash a, Trash b) {
            if (a.stinks() && !b.stinks()) {
                return -1;
            } else if (!a.stinks() && b.stinks()) {
                return 1;
            } else {
                return a.compareTo(b);
            }
        }
    }

    private String name;
    private int age;
    private boolean isStinky;

    public Trash(String name, int age, boolean isStinky) {
        this.name = name;
        this.age = age;
        this.isStinky = isStinky;
    }

    public String toString() {
        return name;
    }

    public boolean stinks() {
        return isStinky;
    }

    @Override
    public int compareTo(Trash other) {
        return this.age - other.age;
    }

    public static void main(String[] args) {
        List<Trash> trash = new ArrayList<>();
        trash.add(new OldBanana("billy", 223, true));
        trash.add(new OldBanana("bob", 5, false));
        trash.add(new OldBanana("George", 1, true));
        System.out.println("Unsorted: " + trash);
        Collections.sort(trash);
        System.out.println("Naturally sorted: " + trash);
        Collections.sort(trash, new TrashComparator());
        System.out.println("Unnaturally sorted: " + trash);
        Collections.sort(trash);
        System.out.println("Naturally sorted: " + trash);
        Collections.sort(trash, (Trash a, Trash b) -> {
            if (a.stinks() && !b.stinks()) {
                return -1;
            } else if (!a.stinks() && b.stinks()) {
                return 1;
            } else {
                return a.compareTo(b);
            }
        });
        System.out.println("Unnaturally sorted: " + trash);
    }
}