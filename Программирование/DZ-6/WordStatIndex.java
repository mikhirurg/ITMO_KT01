import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class WordStatIndex {

    public static void main(String[] args) throws IOException {
        FileReader r = new FileReader(new File(args[0]), StandardCharsets.UTF_8);
        FileWriter w = new FileWriter(new File(args[1]), StandardCharsets.UTF_8);
        int c = 0;
        int n = 0;
        StringBuilder build = new StringBuilder();
        LinkedHashMap<String, ArrayList<Integer>> map = new LinkedHashMap<>();
        while ((c = r.read()) != -1) {
            if (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'') {
                build.append(Character.toLowerCase((char) c));
            } else {
                if (build.length() > 0) {
                    n++;
                    if (map.get(build.toString()) == null) {
                        ArrayList<Integer> numbers = new ArrayList();
                        numbers.add(n);
                        map.put(build.toString(), numbers);
                    } else {
                        map.get(build.toString()).add(n);
                    }
                    build.delete(0, build.length());
                }
            }
        }

        for (String key : map.keySet()) {
            w.write(key + " " + map.get(key).size());
            for (int num : map.get(key)) {
                w.write(" " + num);
            }
            w.write("\n");
        }

        r.close();
        w.close();
    }
}
