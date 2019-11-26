package parser;

import markup.Header;
import markup.Htmlable;
import markup.Texable;
import markup.Text;
import scanner.MyScanner;

import java.io.IOException;
import java.util.*;

public class MD {
    public static Document parse(String md) throws IOException {
        return new Parser(md).parse();
    }

    private static class Parser {
        private final MyScanner scanner;
        private char ch;
        private int pos;

        Parser(final String data) {
            this.scanner = new MyScanner(data);
        }

        Document parse() throws IOException {
            ArrayList<Htmlable> content = new ArrayList<>();
            Text hContent = new Text("");
            boolean inHeader = false;
            int hLevel = 0;
            String str;
            while (scanner.hasNextChar()) {
                str = scanner.readLine();
                MyScanner strScan = new MyScanner(str);
                if (inHeader) {
                    if ("".equals(str)) {
                        inHeader = false;
                        content.add(new Header(hContent, hLevel));
                        hLevel = 0;
                        hContent = null;
                    } else {
                        hContent.append(str);
                    }
                } else {
                    String word = strScan.nextWord();
                    if (word.length() <= 6) {
                        int i = 0;
                        while (i < word.length() && word.charAt(i) == '#') i++;
                        if (i == word.length()) {
                            hLevel = word.length();
                            hContent = new Text("");
                            parseText(strScan, hContent);
                            inHeader = true;
                        }
                    }
                }
            }
            if (inHeader) {
                content.add(new Header(hContent, hLevel));
            }

            return new Document(content);
        }

        private void parseHeader(MyScanner scanner, ArrayList<Htmlable> content, int level) {

        }

        private void parseText(MyScanner scanner, Text content) throws IOException {
            content = new Text(scanner.readLine());
        }

    }
}
