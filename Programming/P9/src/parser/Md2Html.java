package parser;

import markup.Emphasis;
import markup.Header;
import markup.Paragraph;
import markup.Strong;
import scanner.MyScanner;

import java.io.IOException;

public class Md2Html {
    public static Document parse(String md) throws IOException {
        return (Document) new Parser2(md).parse();
    }
    static class Parser2 {
        private final MyScanner scanner;
        private char ch;
        private int pos;

        Parser2(final String data) {
            //Create new scanner from data string adding ending symbols "\0\0"
            this.scanner = new MyScanner(data+"\0\0");
        }

        Object parse() {
            //Here we will do recursive parsing an object structure
            final Object result;
            return null;
        }

        private void expect(final char c) throws IOException {
            char ch = scanner.getChar();

            if (ch != c) {
                throw error("Expected " + c + ", found: " + ch);
            }
            scanner.nextChar();
        }

        private void expect(final String seq) throws IOException {
            for (char c : seq.toCharArray()) {
                expect(c);
            }
        }

        private Object parseText(String end) {
            return null;
        }

        private Paragraph parseParagraph() {
            return null;
        }

        private Header parseHeader() {
            return null;
        }

        private Strong parseStrong() {
            return null;
        }

        private Emphasis parseEmphasis() {
            return null;
        }

        private void parseStrikeout() {

        }

        private void parseCode() {

        }

        private void parseLight() {

        }

        private MDException error(final String message) {
            return new MDException(scanner.getPos() + ": " + message);
        }

    }
}
