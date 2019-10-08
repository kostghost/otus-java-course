package ru.otus.hw02;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import ru.otus.hw02.collection.DiyArrayList;

public class Main {
    private static Random randomizer = new Random();

    public static void main(String[] args) {
        DiyArrayList<Integer> arrayList = generateList(42, () -> randomizer.nextInt(100));
        Collections.addAll(arrayList, 8, 8, 0, 0, 5, 5, 5, 3, 5, 3, 5);
        System.out.println("Original lst:");
        printList(arrayList);

        DiyArrayList<Integer> arrayListTwo = generateList(arrayList.size(), () -> 0);
        Collections.copy(arrayListTwo, arrayList);
        System.out.println("Copied lst:");
        printList(arrayListTwo);

        Collections.sort(arrayList, Integer::compareTo);
        System.out.println("Sorted lst:");
        printList(arrayList);
    }

    private static <T> DiyArrayList<T> generateList(int length, Supplier<T> elementGenerator) {
        var arrayList = new DiyArrayList<T>();
        for (int i = 0; i < length; i++) {
            arrayList.add(elementGenerator.get());
        }
        return arrayList;
    }

    private static <T> void printList(List<T> list) {
        for (T el : list) {
            System.out.print(el + " ");
        }
        System.out.println();
    }
}
