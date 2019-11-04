import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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
        Lexem[] a = new Lexem[1];
        int n = 0;
        while (r.hasNextChar()) {
            String word = r.nextWord();
            if (word.length() > 0) {
                boolean in = false;
                int ind = 0;
                for (int j = 0; j < n; j++) {
                    if (a[j].word.equals(word)) {
                        in = true;
                        ind = j;
                        break;
                    }
                }
                if (in) {
                    a[ind].count++;
                } else {
                    if (n >= a.length) {
                        a = Arrays.copyOf(a, a.length * 2);
                    }
                    a[n] = new Lexem("", 0, n);
                    a[n].word = word;
                    a[n].count = 1;
                    n++;
                }
            }
        }

        a = Arrays.copyOf(a, n);

        Arrays.sort(a, (o1, o2) -> {
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
            w.write(a[i].word + " " + a[i].count + "\n");
        }
        r.close();
        w.close();
    }
}
