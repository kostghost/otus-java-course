package ru.otus.hw01;

import java.util.Comparator;
import java.util.stream.Collectors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class HelloOtus {
    public static void main(String[] args) {
        printNotAMeme(generatePhrases());
    }

    private static Multimap<String, String> generatePhrases() {
        Multimap<String, String> whatHeSaid = ArrayListMultimap.create();

        whatHeSaid.put("Nobody", "");

        String lenin = "Lenin";
        whatHeSaid.put(lenin, "study");
        whatHeSaid.put(lenin, "study");
        whatHeSaid.put(lenin, "study again");

        return whatHeSaid;
    }

    private static void printNotAMeme(Multimap<String, String> phrases) {
        var sortedKeys = phrases.keys().stream()
                .sorted(Comparator.reverseOrder())
                .distinct()
                .collect(Collectors.toList());

        sortedKeys.forEach(
                dude -> System.out.println(dude + ": " + String.join(", ", phrases.get(dude)))
        );
    }
}
