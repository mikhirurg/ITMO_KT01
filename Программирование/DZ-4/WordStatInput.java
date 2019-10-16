import java.io.*;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class WordStatInput {
    static class Pair {
        String word;
        int count;

        Pair(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        try {
            FileReader r = new FileReader(new File(args[0]),StandardCharsets.UTF_8);
            FileWriter w = new FileWriter(new File(args[1]),StandardCharsets.UTF_8);
            Pair[] a = new Pair[1];
            int c = 0;
            String tmp = "";
            int n = 0;
            StringBuilder build = new StringBuilder();
            while (true) {
                c = r.read();
                if (c == -1) break;
                if (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'') {
                    build.append(Character.toLowerCase((char)c));
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
                            build.delete(0,build.length());
                        } else {
                            if (n >= a.length) {
                                a = Arrays.copyOf(a, a.length * 2);
                            }
                            a[n] = new Pair("", 0);
                            a[n].word = build.toString();
                            a[n].count = 1;
                            build = new StringBuilder();
                            n++;
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                w.write(a[i].word + " " + a[i].count + "\n");
            }
            r.close();
            w.flush();
            w.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
