import java.io.*;
import java.util.*;
public class WordStatLastIndex {

    public static final String UTF_8 = "utf8";

    public static void main(String[] args) {
        try {
            MyScannerFast scan = new MyScannerFast(new File(args[0]));
            Map<String, IntList> wordcur = new LinkedHashMap<>();
            int line = 0;
            while (scan.hasNextLine()) {
                line++;
                int count = 0;
                Map<String, Boolean> wordbe = new HashMap<>();
                while(scan.hasNextinLine()) {
                    String num = scan.readNextWord().toLowerCase();
                    if (num.length() > 0) {
                        ++count;
                        IntList wordlist = wordcur.getOrDefault(num, new IntList());
                        if (wordlist.size() == 0) {
                            wordlist.add(0);
                            wordlist.add(count);
                        }
                       else {
                           if (wordbe.containsKey(num)) {
                               wordlist.remove();
                                System.out.println();
                           }
                            wordlist.add(count);
                        }
                        wordlist.set(0, wordlist.get(0) + 1);
                        wordbe.put(num, true);
                        wordcur.put(num, wordlist);
                    }
                }
            }
            try {
                PrintWriter out = new PrintWriter(
                        new BufferedWriter(
                                new OutputStreamWriter(
                                        new FileOutputStream(new File(args[1])),
                                        UTF_8
                                ),
                                1024
                        )
                );
                try {
                    for (Map.Entry<String, IntList> print : wordcur.entrySet()) {
                        out.print(print.getKey());
                        IntList io = print.getValue();
                        for (int j = 0; j < io.size(); ++j)
                            out.print(" " + io.get(j));
                        out.println();
                    }

                } finally {
                    out.close();
                }
            } finally {
                scan.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        }

    }
}