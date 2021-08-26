package First;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
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
        First.Vector point = new First.Vector(10, 10);
        List<Figure> figures = new ArrayList<>() {{
            add(new Rectangle(point, 50, 40));
            add(new Square(point, 50));
            add(new Circle(point, 50, 10));
            First.Vector point2 = new First.Vector(50, 50);
            First.Vector point3 = new Vector(90, -20);
            add(new Triangle(point, point2, point3));
        }};
        figures.iterator().forEachRemaining(Figure::printPoints);
        for (Figure f :
                figures) {
            System.out.println(f.toString() + ", Perimetr: " + f.perimetr() + ", First.Square: " + f.square());
        }
        point.setXY(0, 0); // изменение точки привязки
        figures.iterator().forEachRemaining(Figure::printPoints);
    }

    public static void task3() {
        TemperatureConverter converter = new TemperatureConverter(0, TemperatureConverter.TemperatureScale.CELSIUS);
        String helper = "Введите значение температуры или выбирите вид температурной шкалы '/n' " +
                "(c - Цельсия, f - Фаренгейта, k - Кельвина), e - выход, h - справка";
        System.out.println(helper);
        Scanner in = new Scanner(System.in);
//        String sep = DecimalFormatSymbols.getInstance(in.locale()).getDecimalSeparator() + "";
        boolean stay = true;
        while (stay) {
//            String str = in.nextLine().toUpperCase().replaceAll(".", sep);
//            str = str.replaceAll(",", sep);
            if (in.hasNextDouble()) {
                converter.setTempC(in.nextDouble());//Double.parseDouble()
                converter.printTemparature();
            } else {
                String str = in.nextLine().toUpperCase();
                if (str.length() == 1) {
                    switch (str) {
                        case "C":
                        case "F":
                        case "K":
                            for (TemperatureConverter.TemperatureScale scale : TemperatureConverter.TemperatureScale.values()) {
                                if (str.equals(scale.getTitle())) {
                                    converter.setUnit(scale);
                                    converter.printTemparature();
                                    break;
                                }
                            }
                            break;
                        case "H":
                            System.out.println(helper);
                            break;
                        case "E":
                            stay = false;
                            break;
                    }
                }
            }
        }
    }
}
