package md2html;

import markup.*;
import scanner.MyScanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Md2Html {

    private static Set<Character> reservedSymbols = Set.of(
        '\0', '\r'
    );

    private static Map<Character,String> specialHtml = Map.of(
            '<' , "&lt;",
            '>', "&gt;",
            '&', "&amp;"
    );

    public static void main(String[] args) throws IOException {
        MyScanner scanner = new MyScanner(new File(args[0]),StandardCharsets.UTF_8);
        String in = scanner.readAll(specialHtml)+"\n";
        Document doc = parse(in);
        scanner.close();
        FileWriter fw = new FileWriter(args[1]);
        StringBuilder result = new StringBuilder();
        doc.toHtml(result);
        fw.write(result.toString());
        fw.close();
    }

    public static Document parse(String md) throws IOException {
        return new Document((List<Htmlable>) new Parser2(md).parse());
    }
    static class Parser2 {
        private MyScanner scanner;
        private String data;
        private int pos;

        Parser2(String data) {
            this.data = data;
        }

        Object parse() throws IOException {
            List<Object> content = new ArrayList<>();
            List<String> blocks;
            data = trimLine(data);

            blocks = Arrays.asList(data.split("\n\n"));

            for (String text : blocks) {
                if (text.length() == 0) {
                    continue;
                }
                text = trimLine(text);
                text+="\0\0";
                scanner = new MyScanner(text);
                while (scanner.hasNextChar()) {
                    char c = scanner.nextChar();
                    if (c == '#') {
                        scanner.saveState();
                        int level = 0;
                        while (c == '#') {
                            c = scanner.nextChar();
                            level++;
                        }
                        if (c == ' ' && level <= 6) {
                            content.add(parseHeader("\0\0", level));
                            scanner.dropAll();
                        } else {
                            scanner.reset();
                            scanner.movePos(-1);
                            content.add(parseParagraph("\0\0"));
                            scanner.dropAll();
                        }
                    } else {
                        if (scanner.haveSavedStates()) {
                            scanner.reset();
                        } else {
                            scanner.movePos(-1);
                        }
                        content.add(parseParagraph("\0\0"));
                        scanner.dropAll();
                    }
                }
            }
            return content;
        }

        private String trimLine(String data) {
            int s = 0;
            while (s < data.length() && data.charAt(s) == '\n') {
                s++;
            }
            data = data.substring(s);
            int e = data.length() - 1;
            while (e  >= 0 && data.charAt(e) == '\n') {
                e--;
            }
            data = data.substring(0, e + 1);
            return data;
        }

        private boolean checkEnding(String end, char ch) throws IOException {
            if (scanner.getPrev() == '\\') {
                return false;
            }
            if (ch != end.charAt(0)) {
                return false;
            }

            scanner.saveState();
            int pos = 1;

            while (scanner.hasNextChar() && pos < end.length()) {

                char c = scanner.nextChar();

                if (c == end.charAt(pos)) {
                    pos++;
                } else {
                    break;
                }

            }

            if (pos < end.length()) {
                scanner.reset();
                return false;
            } else {
                scanner.dropState();
                return true;
            }
        }

        static void appendChar(StringBuilder builder, char c) {
            boolean isAdding = !reservedSymbols.contains(c);
            if (isAdding) {
                builder.append(c);
            }
        }

        private Object parseContent(String end) throws IOException {
            boolean isEnd = false;
            int pos = 0;
            List<Object> content = new ArrayList<>();
            StringBuilder text = new StringBuilder();
            while (scanner.hasNextChar()) {
                char c = scanner.nextChar();
                if (checkEnding(end, c)) {
                    isEnd = true;
                    break;
                }
                //if (!end.equals("`")) {
                    switch (c) {
                        case '*':
                        case '_':
                            scanner.saveState();
                            char nc = scanner.nextChar();
                            String ending = String.valueOf(c);
                            if (nc == c) {
                                ending += nc;
                            } else {
                                scanner.movePos(-1);
                            }


                            Object emphasis = ending.length() > 1 ? parseStrong(ending) : parseEmphasis(ending);

                            if (emphasis != null) {
                                content.add(new Text(text.toString()));
                                content.add(emphasis);
                                text.setLength(0);
                                scanner.dropState();
                            } else {
                                scanner.reset();
                                appendChar(text, c);
                            }
                            break;
                        case '`':
                            scanner.saveState();
                            Object code = parseCode(String.valueOf(c));
                            if (code != null) {
                                content.add(new Text(text.toString()));
                                content.add(code);
                                text.setLength(0);
                                scanner.dropState();
                            } else {
                                scanner.reset();
                                appendChar(text, c);
                            }
                            break;
                        case '-':
                            scanner.saveState();
                            nc = scanner.nextChar();
                            if (nc == c) {
                                Object strike = parseStrikeout("--");
                                if (strike != null) {
                                    content.add(new Text(text.toString()));
                                    content.add(strike);
                                    text.setLength(0);
                                    scanner.dropState();
                                } else {
                                    scanner.movePos(-1);
                                    scanner.reset();
                                    appendChar(text, c);
                                }
                            } else {
                                scanner.reset();
                                appendChar(text, c);
                            }
                            break;
                        case '\\':
                            nc = scanner.nextChar();
                            appendChar(text, nc);
                            break;
                        default:
                            appendChar(text, c);
                    }
//                } else {
//                    appendChar(text, c);
//                }
            }
            if (!isEnd) {
                return null;
            }
            if (text.length() > 0) {
                content.add(new Text(text.toString()));
            }
            return content;
        }

        private Paragraph parseParagraph(String end) throws IOException {
            Object par = parseContent(end);
            if (par == null) {
                return null;
            } else {
                return new Paragraph((List<ParagraphElements>) par);
            }
        }

        private Header parseHeader(String end, int level) throws IOException {
            Object head = parseContent(end);
            if (head == null) {
                return null;
            } else {
                return new Header((List<Markable>) head, level);
            }
        }

        private Strong parseStrong(String end) throws IOException {
            Object strong = parseContent(end);
            if (strong == null) {
                return null;
            } else {
                return new Strong((List<Markable>) strong);
            }
        }

        private Emphasis parseEmphasis(String end) throws IOException {
            Object emph = parseContent(end);
            if (emph == null) {
                return null;
            } else {
                return new Emphasis((List<Markable>) emph);
            }
        }

        private Strikeout parseStrikeout(String end) throws IOException {
            Object strike = parseContent(end);
            if (strike == null) {
                return null;
            } else {
                return new Strikeout((List<Markable>) strike);
            }
        }

        private Code parseCode(final String end) throws IOException {
            Object emph = parseContent(end);
            if (emph == null) {
                return null;
            } else {
                return new Code((List<Markable>) emph);
            }
        }

        private MDException error(final String message) {
            return new MDException(scanner.getPos() + ": " + message);
        }

    }
}
