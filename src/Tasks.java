import java.util.ArrayList;
import java.util.List;

public class Tasks {
    public static void main(String[] args) {
        task1();
        task2();
    }

    public static void task1() {
        Integer[] sortedInts = ArraySorter.Sort(new Integer[]{1, 2, 0, 5, 4, 6, 0, 8, 5, 10, 7, 9, 8});
        ArraySorter.PrintArray(sortedInts);
        int v1 = 8;
        System.out.println("IndexOf(" + v1 + ") = " + ArraySorter.IndexOf(v1, sortedInts));
        Double[] sortedDoubles = ArraySorter.Sort(new Double[]{1d, 2.0, 0.5, 5d, 4.0, 6d, 8d, 5.5, 10d, 7d, 9d, 8.1});
        ArraySorter.PrintArray(sortedDoubles);
        double v2 = 5.4;
        System.out.println("IndexOf(" + v2 + ") = " + ArraySorter.IndexOf(v2, sortedDoubles));
        Character[] sortedChars = ArraySorter.Sort(new Character[]{'a', 'c', 'm', 'p', 'z', 'b', 'y', 'e', 't', 'u', 'n', 'f'});
        ArraySorter.PrintArray(sortedChars);
        char v3 = 'm';
        System.out.println("IndexOf(" + v3 + ") = " + ArraySorter.IndexOf(v3, sortedChars));
    }

    public static void task2() {
        Vector point = new Vector(10, 10);
        List<Figure> figures = new ArrayList<>() {{
            add(new Rectangle(point, 50, 40));
            add(new Square(point, 50));
            add(new Circle(point, 50, 10));
            Vector point2 = new Vector(50, 50);
            Vector point3 = new Vector(90, -20);
            add(new Triangle(point, point2, point3));
        }};
        figures.iterator().forEachRemaining(Figure::printPoints);
        for (Figure f :
                figures) {
            System.out.println(f.toString() + ", Perimetr: " + f.perimetr() + ", Square: " + f.square());
        }
        point.setXY(0, 0); // изменение точки привязки
        figures.iterator().forEachRemaining(Figure::printPoints);
    }
}
