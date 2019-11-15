import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WordStatLastIndex {
    public static void main(String[] args) throws IOException {
        MyScanner read = new MyScanner(new File(args[0]), StandardCharsets.UTF_8);
        FileWriter w = new FileWriter(new File(args[1]), StandardCharsets.UTF_8);
        Map<String, IntList> map = new LinkedHashMap<>();
        Map<String, Integer> number = new LinkedHashMap<>();

        while (read.hasNextChar()) {
            String line = read.readLine();
            int n = 1;
            MyScanner inLine = new MyScanner(line);
            HashMap<String, Integer> wordLine = new LinkedHashMap<>();
            while (inLine.hasNextChar() && !line.isEmpty()) {
                String word = inLine.nextWord();
                if (!word.isEmpty()) {
                  number.put(word, number.getOrDefault(word, 0) + 1);
                    if (!wordLine.containsKey(word)) {
                        wordLine.put(word, n);
                    } else {
                        wordLine.put(word, Math.max(n, wordLine.get(word)));
                    }
                }
                n++;
            }
            for (String key : wordLine.keySet()){
                if (!map.containsKey(key)){
                    map.put(key, new IntList());
                    map.get(key).add(wordLine.get(key));
                } else {
                    map.get(key).add(wordLine.get(key));
                }
            }
        }

        for (String key : map.keySet()) {
            w.write(key + " " +number.get(key));
            for (int numb : map.get(key)) {
                w.write(" " + numb);
            }
            w.write("\n");
        }
        w.close();
        read.close();
    }
}
