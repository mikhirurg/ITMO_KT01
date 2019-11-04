import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class WordStatCount {

    static class Lexem {
        String word;
        int count, pos;

        Lexem(String word, int count, int pos) {
            this.word = word;
            this.count = count;
            this.pos = pos;
        }
    }

    public static void main(String[] args) throws IOException {
        MyScanner r = new MyScanner(new File(args[0]));
        FileWriter w = new FileWriter(new File(args[1]), StandardCharsets.UTF_8);
        List<Lexem> words = new ArrayList<>();
        int n = 0;
        while (r.hasNextChar()) {
            String word = r.nextWord();
            if (word.length() > 0) {
                boolean in = false;
                int ind = 0;
                for (int j = 0; j < n; j++) {
                    if (words.get(j).word.equals(word)) {
                        in = true;
                        ind = j;
                        break;
                    }
                }
                if (in) {
                    words.get(ind).count++;
                } else {
                    words.add(new Lexem("", 0, n));
                    words.get(n).word = word;
                    words.get(n).count = 1;
                    n++;
                }
            }
        }
        words.sort((o1, o2) -> {
            if (o1.count == o2.count) {
                if (o1.pos > o2.pos) {
                    return 1;
                } else return 0;
            } else {
                if (o1.count > o2.count) {
                    return 1;
                } else return -1;
            }
        });

        for (int i = 0; i < n; i++) {
            w.write(words.get(i).word + " " + words.get(i).count + "\n");
        }
        r.close();
        w.close();
    }
}
