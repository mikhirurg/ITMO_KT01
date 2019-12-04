package md2html;

import markup.*;
import scanner.StackScanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Md2Html {

    private static Set<Character> reservedSymbols = Set.of(
            '\0', '\r'
    );

    private static Map<Character, String> specialHtml = Map.of(
            '<', "&lt;",
            '>', "&gt;",
            '&', "&amp;"
    );

    public static void main(String[] args) throws IOException {
        StackScanner scanner = new StackScanner(new File(args[0]), StandardCharsets.UTF_8);
        String in = scanner.readAll(specialHtml) + "\n";
        Document doc = parse(in);
        scanner.close();
        FileWriter fw = new FileWriter(args[1]);
        StringBuilder result = new StringBuilder();
        doc.toHtml(result);
        fw.write(result.toString());
        fw.close();
    }

    public static Document parse(String md) throws IOException {
        return new Document(new Parser(md).parse());
    }

    static class Parser {
        private StackScanner scanner;
        private String data;

        Parser(String data) {
            this.data = data;
        }

        List<Htmlable> parse() throws IOException {
            List<Htmlable> content = new ArrayList<>();
            List<String> blocks;
            data = trimLine(data);

            blocks = Arrays.asList(data.split("\n\n"));

            for (String text : blocks) {
                if (text.length() == 0) {
                    continue;
                }
                text = trimLine(text);
                text += "\0\0";
                scanner = new StackScanner(text);
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
                            content.add(parseHeader(level));
                        } else {
                            scanner.reset();
                            scanner.movePos(-1);
                            content.add(parseParagraph());
                        }
                    } else {
                        if (scanner.haveSavedStates()) {
                            scanner.reset();
                        } else {
                            scanner.movePos(-1);
                        }
                        content.add(parseParagraph());
                    }
                    scanner.dropAll();
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
            while (e >= 0 && data.charAt(e) == '\n') {
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

        private List<ParagraphElement> parseContent(String end) throws IOException {
            boolean isEnd = false;
            List<ParagraphElement> content = new ArrayList<>();
            StringBuilder text = new StringBuilder();
            while (scanner.hasNextChar()) {
                char c = scanner.nextChar();
                if (checkEnding(end, c)) {
                    isEnd = true;
                    break;
                }
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

                        ParagraphElement emphasis = ending.length() > 1 ? parseStrong(ending) : parseEmphasis(ending);
                        verify(emphasis, text, c, content, 0);
                        break;
                    case '`':
                        scanner.saveState();
                        ParagraphElement code = parseCode(String.valueOf(c));
                        verify(code, text, c, content, 0);
                        break;
                    case '[':
                        scanner.saveState();
                        ParagraphElement link = parseLink();
                        verify(link, text, c, content, 0);
                        break;
                    case '-':
                        scanner.saveState();
                        nc = scanner.nextChar();
                        if (nc == c) {
                            ParagraphElement strike = parseStrikeout();
                            verify(strike, text, c, content, -1);
                        } else {
                            scanner.reset();
                            appendChar(text, c);
                        }
                        break;
                    case '\\':
                        nc = scanner.nextChar();
                        appendChar(text, nc);
                        break;
                    case '+':
                        scanner.saveState();
                        nc = scanner.nextChar();
                        if (nc == c) {
                            ParagraphElement underline = parseUnderLine();
                            verify(underline, text, c, content, -1);
                        } else {
                            scanner.reset();
                            appendChar(text, c);
                        }
                        break;
                    case '~':
                        scanner.saveState();
                        ParagraphElement mark = parseMark();
                        verify(mark, text, c, content, 0);
                        break;
                    default:
                        appendChar(text, c);
                }
            }
            if (!isEnd) {
                return null;
            }
            if (text.length() > 0) {
                content.add(new Text(text.toString()));
            }
            return content;
        }

        private void verify(ParagraphElement element, StringBuilder text, char c,
                            List<ParagraphElement> content, int move) {
            if (element != null) {
                content.add(new Text(text.toString()));
                content.add(element);
                text.setLength(0);
                scanner.dropState();
            } else {
                scanner.movePos(move);
                scanner.reset();
                appendChar(text, c);
            }
        }

        private ParagraphElement parseLink() throws IOException {
            List<ParagraphElement> linkText = parseContent("]");
            if (linkText == null) {
                return null;
            } else {
                String href = parseLinkUrl();
                if (href == null) {
                    return null;
                }
                return new Link(href, linkText);
            }
        }

        private String parseLinkUrl() throws IOException {
            if (!scanner.hasNextChar() || scanner.nextChar() != '(') {
                return null;
            }
            char c = '\0';
            StringBuilder url = new StringBuilder();
            while (scanner.hasNextChar() && (c = scanner.nextChar()) != ')') {
                url.append(c);
            }
            if (c != ')') {
                return null;
            }
            return url.toString();
        }

        private Paragraph parseParagraph() throws IOException {
            List<ParagraphElement> par = parseContent("\0\0");
            if (par == null) {
                return null;
            } else {
                return new Paragraph(par);
            }
        }

        private Header parseHeader(int level) throws IOException {
            List<ParagraphElement> head = parseContent("\0\0");
            if (head == null) {
                return null;
            } else {
                return new Header(head, level);
            }
        }

        private Strong parseStrong(String end) throws IOException {
            List<ParagraphElement> strong = parseContent(end);
            if (strong == null) {
                return null;
            } else {
                return new Strong(strong);
            }
        }

        private Underline parseUnderLine() throws IOException {
            List<ParagraphElement> underline = parseContent("++");
            if (underline == null) {
                return null;
            } else {
                return new Underline(underline);
            }
        }

        private Mark parseMark() throws IOException {
            List<ParagraphElement> mark = parseContent("~");
            if (mark == null) {
                return null;
            } else {
                return new Mark(mark);
            }
        }


        private Emphasis parseEmphasis(String end) throws IOException {
            List<ParagraphElement> emph = parseContent(end);
            if (emph == null) {
                return null;
            } else {
                return new Emphasis(emph);
            }
        }

        private Strikeout parseStrikeout() throws IOException {
            List<ParagraphElement> strike = parseContent("--");
            if (strike == null) {
                return null;
            } else {
                return new Strikeout(strike);
            }
        }

        private Code parseCode(final String end) throws IOException {
            List<ParagraphElement> code = parseContent(end);
            if (code == null) {
                return null;
            } else {
                return new Code(code);
            }
        }

    }
}
