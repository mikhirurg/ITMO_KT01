import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class WordStatIndex {

    public static void main(String[] args) throws IOException {
        MyScanner read = new MyScanner(new File(args[0]), StandardCharsets.UTF_8);
        FileWriter w = new FileWriter(new File(args[1]), StandardCharsets.UTF_8);
        int n = 0;
        String word = "";
        LinkedHashMap<String, ArrayList<Integer>> map = new LinkedHashMap<>();
        while (read.hasNext()) {
            word = read.nextWord().toLowerCase();
            n++;
            if (map.get(word) == null) {
                ArrayList<Integer> numbers = new ArrayList();
                numbers.add(n);
                map.put(word, numbers);
            } else {
                map.get(word).add(n);
            }
        }
        for (String key : map.keySet()) {
            w.write(key + " " + map.get(key).size());
            for (int num : map.get(key)) {
                w.write(" " + num);
            }
            w.write("\n");
        }
        w.close();
        read.close();
    }
}
