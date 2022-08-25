import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("Tolstoi_Lev__Voina_i_mir._Tom_1_www.Litmir.net_27684.txt");
        BufferedReader bufferedReader
                = new BufferedReader(new FileReader(file, Charset.forName("UTF-8")));
        String line;

        TreeMap<String, Integer> words = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() > o2.length() && o1.length() > 0 ||
                        o1.length() == o2.length() && o1.compareTo(o2) > 0) {
                    return 1;
                } else if (o1.length() == o2.length() && o1.compareTo(o2) == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        while ((line = bufferedReader.readLine()) != null) {
            String lineWithoutSpecCharacters = line.replaceAll("[^a-zA-Zà-ÿÀ-ß]", " ");
            String[] arrayWords = lineWithoutSpecCharacters.toUpperCase().split("\\s+");

            for (int i = 0; i < arrayWords.length; i++) {
                if (arrayWords[i].isEmpty()) {
                    continue;
                } else if (words.containsKey(arrayWords[i])) {
                    Integer value = words.get(arrayWords[i]);
                    words.put(arrayWords[i], value + 1);
                } else {
                    words.put(arrayWords[i], 1);
                }
            }
        }
        System.out.println("The shortest word is " + words.firstKey() + " and repeats " +
                words.firstEntry().getValue() + " times; the largest word - " + words.lastKey() +
                " and repeats " + words.lastEntry().getValue() + " times.");
    }
}
