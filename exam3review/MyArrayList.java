import java.lang.Iterable;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MyArrayList implements Iterable<Integer> {

    private class MyArrayListIterator implements Iterator<Integer> {

        private int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < myArrayList.size();
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return myArrayList.get(cursor++);
        }
    }

    private ArrayList<Integer> myArrayList;

    public MyArrayList() {
        myArrayList = new ArrayList<>();
    }

    public void add(Integer x) {
        myArrayList.add(x);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new MyArrayListIterator();
    }

    public static void main(String[] args) {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add(5);
        myArrayList.add(4);
        myArrayList.add(3);
        myArrayList.add(2);
        myArrayList.add(1);
        for (Integer i : myArrayList) {
            System.out.println(i);
        }
        System.out.println("Ok!");
    }
}