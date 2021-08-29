package Generics;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        ICountMap<Integer> map = new CountMap();
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);
        System.out.println(map.getCount(5)); // 2
        System.out.println(map.getCount(10)); // 3
        System.out.println(map.getCount(6)); // 1
        System.out.println();
//
        CollectionUtils.range(Arrays.asList(8, 1, 3, 5, 6, 4), 3, 6)// вернет {3,4,5,6}
                .forEach(System.out::println);
        //
        A a1 = new A("a1", 13);
        A a2 = new A("a2", 12);
        A a3 = new A("a3", 11);
        B b1 = new B("b1", 13);
        B b2 = new B("b2", 12);
        B b3 = new B("b3", 11);
        C c1 = new C("c1", 13);
        C c2 = new C("c2", 12);
        C c3 = new C("c3", 11);
        List<A> listAll;// = Arrays.asList(a1, a2, a3, b1, b2, b3, c1, c2, c3);
        List<A> listA = Arrays.asList(a1, a2, a3);
        List<B> listB = Arrays.asList(b1, b2, b3);
        List<C> listC = Arrays.asList(c1, c2, c3);
        listAll = CollectionUtils.newArrayList();
//        listAll.add(a1);        listAll.add(a2);        listAll.add(a3);
//        listAll.add(b1);        listAll.add(b2);        listAll.add(b3);
//        listAll.add(c1);        listAll.add(c2);        listAll.add(c3);
//        List<B> listX = CollectionUtils.newArrayList();
//        listX.add(a1);
        //
        CollectionUtils.addAll(listA, listAll);
        CollectionUtils.addAll(listB, listAll);
        CollectionUtils.addAll(listC, listAll);
        //
        System.out.println(CollectionUtils.indexOf(listAll, b1));
//        System.out.println(CollectionUtils.indexOf(listB, a1));
//        System.out.println(CollectionUtils.indexOf(listB, c1));
        listB = CollectionUtils.limit(listB, 3);
//        listB = CollectionUtils.limit(listA, 1);
        listA = CollectionUtils.limit(listB, 1);
        listA.clear();
        CollectionUtils.add(listA, a1);
        CollectionUtils.add(listA, a2);
        CollectionUtils.add(listA, a3);
//        CollectionUtils.add(listB, a1);
//        CollectionUtils.add(listC, a1);
        CollectionUtils.removeAll(listAll, listA);
        CollectionUtils.removeAll(listAll, listB);
//        CollectionUtils.removeAll(listC, listA);
//        CollectionUtils.removeAll(listB, listA);
//        if (CollectionUtils.containsAll(listB, listA)) System.out.printf("All+");
        if (CollectionUtils.containsAll(listAll, listA)) System.out.println("All+");
        if (CollectionUtils.containsAll(listAll, listC)) System.out.println("All+");
        listAll.add(a1);
        if (CollectionUtils.containsAny(listAll, listA)) System.out.println("Any+");
//        if (CollectionUtils.containsAny(listB, listA)) System.out.println("Any+");
//        List<B> list = CollectionUtils.range(listA, a3, a1, (o1, o2) -> o1.getValue() - o2.getValue());
        List<B> list = CollectionUtils.range(listB, b3, b1, Comparator.comparingInt(A::getValue));
//        List<B> list = CollectionUtils.range(listA, a3, b1, Comparator.comparingInt(A::getValue));
    }

}
