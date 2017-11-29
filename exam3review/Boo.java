public class Boo {

    public interface Foo {
        void bar(Object o);
    }

    public static void doo(Foo f) {
        f.bar("baz");
    }

    public static void main(String[] args) {
        doo(o -> System.out.println(o));
        doo(System.out::println);
    }
}