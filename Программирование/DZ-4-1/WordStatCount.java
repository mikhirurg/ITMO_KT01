import java.io.*;
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


    public static void main(String[] args) {
        try {
            FileReader r = new FileReader(new File(args[0]), StandardCharsets.UTF_8);
            FileWriter w = new FileWriter(new File(args[1]), StandardCharsets.UTF_8);
            Lexem[] a = new Lexem[1];
            int c = 0;
            int n = 0;
            StringBuilder build = new StringBuilder();
            while (true) {
                c = r.read();
                if (c == -1) break;
                if (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'') {
                    build.append(Character.toLowerCase((char) c));
                } else {
                    if (build.length() > 0) {
                        boolean in = false;
                        int ind = 0;
                        for (int j = 0; j < n; j++) {
                            if (a[j].word.equals(build.toString())) {
                                in = true;
                                ind = j;
                                break;
                            }
                        }
                        if (in) {
                            a[ind].count++;
                            build.delete(0, build.length());
                        } else {
                            if (n >= a.length) {
                                a = Arrays.copyOf(a, a.length * 2);
                            }
                            a[n] = new Lexem("", 0, n);
                            a[n].word = build.toString();
                            a[n].count = 1;
                            build = new StringBuilder();
                            n++;
                        }
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                int j = i;
                while (j > 0 && a[j].count <= a[j - 1].count) {
                    if (a[j].count == a[j - 1].count) {
                        if (a[j - 1].pos > a[j].pos) {
                            Lexem tmp = a[j - 1];
                            a[j - 1] = a[j];
                            a[j] = tmp;
                        }
                    } else {
                        if (a[j - 1].count > a[j].count) {
                            Lexem tmp = a[j - 1];
                            a[j - 1] = a[j];
                            a[j] = tmp;
                        }
                    }
                    j--;
                }
            }

            for (int i = 0; i < n; i++) {
                w.write(a[i].word + " " + a[i].count + "\n");
            }
            r.close();
            w.flush();
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
