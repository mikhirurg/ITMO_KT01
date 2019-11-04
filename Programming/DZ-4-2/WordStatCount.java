import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;

public class WordStatCount {

    static int DEFAULT_SIZE = 256;

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
        FileReader r = new FileReader(new File(args[0]), StandardCharsets.UTF_8);
        FileWriter w = new FileWriter(new File(args[1]), StandardCharsets.UTF_8);
        Lexem[] words = new Lexem[DEFAULT_SIZE];
        int c = 0;
        int n = 0;
        StringBuilder build = new StringBuilder();
        while ((c = r.read()) != -1) {
            if (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'') {
                build.append(Character.toLowerCase((char) c));
            } else {
                if (build.length() > 0) {
                    boolean in = false;
                    int ind = 0;
                    for (int j = 0; j < n; j++) {
                        if (words[j].word.equals(build.toString())) {
                            in = true;
                            ind = j;
                            break;
                        }
                    }
                    if (in) {
                        words[ind].count++;
                        build.delete(0, build.length());
                    } else {
                        if (n >= words.length) {
                            words = Arrays.copyOf(words, words.length * 2);
                        }
                        words[n] = new Lexem(build.toString(), 1, n);
                        build = new StringBuilder();
                        n++;
                    }
                }
            }
        }

        words = Arrays.copyOf(words, n);

        Arrays.sort(words, new Comparator<Lexem>() {
            @Override
            public int compare(Lexem l1, Lexem l2) {
                if (l1.count == l2.count) {
                    if (l1.pos > l2.pos) {
                        return 1;
                    } else return 0;
                } else {
                    if (l1.count > l2.count) {
                        return 1;
                    } else return -1;
                }
            }
        });

        for (int i = 0; i < n; i++) {
            w.write(words[i].word + " " + words[i].count + "\n");
        }
        r.close();
        w.close();
    }
}
