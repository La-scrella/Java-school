package Collections;

import java.io.*;
import java.util.*;

public class Task2 {
    public static void main(String[] args) {
        task1();
        task2();
    }

    public static void task1() {
        ArrayList<Car> cars = new ArrayList<>() {{
            add(new Car("Лада", "Седан"));
            add(new Car("Лада", "Хэтчбэк"));
            add(new Car("Мерседес", "Седан"));
            add(new Car("БМВ", "Кроссовер"));
            add(new Car("Форд", "Хэтчбэк"));
            add(new Car("Пежо", "Кроссовер"));
            add(new Car("Тойота", "Седан"));
            add(new Car("Субару", "Хэтчбэк"));
        }};
        ArrayList<ArrayList<Car>> groups = new ArrayList<>();
        for (Car car :
                cars) {
            boolean added = false;
            for (ArrayList<Car> list :
                    groups) {
                if (list.get(0).getType().equals(car.getType())) {
                    list.add(car);
                    added = true;
                    break;
                }
            }
            if (!added) {
                groups.add(new ArrayList<>() {{
                    add(car);
                }});
            }
        }
        for (ArrayList<Car> list :
                groups) {
            for (Car car :
                    list) {
                car.print();
            }
        }
    }

    public static void task2() {
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> lines = new ArrayList<>();
        try {
            File file = new File( "src/Collections/file.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = line.replace(",", "").replace(".", "").replace(":", "").replace("!", "")
                        .replace("–", " ").replace(" – ", " ").toLowerCase().trim();
                for (String s : line.split(" ")) {
                    if (!s.equals("")) {
                        words.add(s);
                    }
                }
                line = reader.readLine();
            }
            reader.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashSet<String> hashSet = new HashSet<>(words);
        System.out.println("Число различных слов в файле: " + hashSet.size() + " / всего: " + words.size());
        List<String> sortable = new ArrayList<>(hashSet);
        sortable.sort(Comparator.comparing(o -> ((String) o).length()).thenComparing(o -> (String) o));
        for (String el: sortable) {
            System.out.println(el + " (" + Collections.frequency(words, el) + ")");
        }
        Deque<String> deque = new ArrayDeque<>();
        for (String l: lines) {
            deque.push(l);
        }
        while (deque.peek() != null) {
            System.out.println(deque.pop());
        }
        System.out.println();
        Reverse<String> reverse = new Reverse<>(lines);
        reverse.forEach(System.out::println);
        Scanner in = new Scanner(System.in);
        System.out.println("Введите но мера строк от 1 до " + lines.size() + ":");
        List<Integer> numbers = new ArrayList<>();
        while (true) {
            if (in.hasNextInt()) {
                int number = in.nextInt();
                if (number > 0 && number <= lines.size()) {
                    numbers.add(number);
                } else break;
            } else break;
        }
        for (Integer number: numbers) {
            System.out.println(lines.get(number - 1));
        }
    }
}
