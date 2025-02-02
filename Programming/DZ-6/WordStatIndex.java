import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class WordStatLastIndex2 {
    public static void main(String[] args) throws IOException {
        MyScanner read = new MyScanner(new File(args[0]), StandardCharsets.UTF_8);
        FileWriter w = new FileWriter(new File(args[1]), StandardCharsets.UTF_8);
        HashMap<String, ArrayList<Integer>> map = new LinkedHashMap<>();
        HashMap<String, Integer> wordLine;
        HashMap<String, Integer> number = new LinkedHashMap<>();
        while (read.hasNextChar()) {
            String line = read.readLine();
            int n = 1;
            MyScanner inLine = new MyScanner(line);
            wordLine = new LinkedHashMap<>();
            while (inLine.hasNextChar() && line.length()>0) {
                String word = inLine.nextWord();
                if (word.length() > 0) {
                    if (wordLine.get(word) == null) {
                        wordLine.put(word, n);
                        number.put(word, 1);
                    } else {
                        wordLine.put(word, Math.max(n, wordLine.get(word)));
                        number.put(word, number.get(word) + 1);
                    }
                }
                n++;
            }
            for (String key : wordLine.keySet()){
                if (map.get(key) == null){
                    map.put(key, new ArrayList<>());
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
