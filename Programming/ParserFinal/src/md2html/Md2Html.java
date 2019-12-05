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
        StringBuilder builder = scanner.readAll(specialHtml);
        Document doc = parse(builder);
        scanner.close();
        FileWriter fw = new FileWriter(args[1]);
        StringBuilder result = new StringBuilder();
        doc.toHtml(result);
        fw.write(result.toString());
        fw.close();
    }

    public static Document parse(StringBuilder md) throws IOException {
        return new Document(new Parser(md).parse());
    }

    static class Parser {
        private StackScanner scanner;
        private StringBuilder data;

        Parser(StringBuilder data) {
            this.data = data;
        }

        List<Htmlable> parse() throws IOException {
            List<Htmlable> content = new ArrayList<>();
            trimLine(data);
            int pos = 0;
            while (pos < data.length()) {
                int npos = data.indexOf("\n\n", pos);
                if (npos == -1) npos = data.length();
                String text = data.substring(pos, npos);
                pos = npos + 2;

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

        private void trimLine(StringBuilder data) {
            int s = 0;
            while (s < data.length() && data.charAt(s) == '\n') {
                s++;
            }
            data.delete(0, s);
            int e = data.length() - 1;
            while (e >= 0 && data.charAt(e) == '\n') {
                e--;
            }
            data.delete(e + 1, data.length());
        }

        private String trimLine(String data) {
            StringBuilder builder = new StringBuilder(data);
            trimLine(builder);
            return builder.toString();
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
                    case '!':
                        scanner.saveState();
                        ParagraphElement image = parseImage();
                        verify(image, text, c, content, 0);
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

        private ParagraphElement parseImage() throws IOException {
            if (!scanner.hasNextChar() || scanner.nextChar()!='[') {
                return null;
            }
            char c = '\0';
            StringBuilder alt = new StringBuilder();
            while (scanner.hasNextChar() && (c = scanner.nextChar()) != ']') {
                alt.append(c);
            }
            if (c != ']') {
                return null;
            }
            String url = parseUrl();
            return url != null ? new Image(url, alt.toString()) : null;
        }

        private ParagraphElement parseLink() throws IOException {
            List<ParagraphElement> linkText = parseContent("]");
            if (linkText == null) {
                return null;
            } else {
                String href = parseUrl();
                return (href != null) ? new Link(href, linkText) : null;
            }
        }

        private String parseUrl() throws IOException {
            if (!scanner.hasNextChar() || scanner.nextChar() != '(') {
                return null;
            }
            char c = '\0';
            StringBuilder url = new StringBuilder();
            while (scanner.hasNextChar() && (c = scanner.nextChar()) != ')') {
                url.append(c);
            }
            return  (c == ')') ? url.toString() : null;
        }

        private Paragraph parseParagraph() throws IOException {
            List<ParagraphElement> par = parseContent("\0\0");
            return (par != null) ? new Paragraph(par) : null;
        }

        private Header parseHeader(int level) throws IOException {
            List<ParagraphElement> head = parseContent("\0\0");
            return (head != null) ? new Header(head, level) : null;
        }

        private Strong parseStrong(String end) throws IOException {
            List<ParagraphElement> strong = parseContent(end);
            return (strong != null) ? new Strong(strong) : null;
        }

        private Underline parseUnderLine() throws IOException {
            List<ParagraphElement> underline = parseContent("++");
            return (underline != null) ? new Underline(underline) : null;
        }

        private Mark parseMark() throws IOException {
            List<ParagraphElement> mark = parseContent("~");
            return (mark != null) ? new Mark(mark) : null;
        }


        private Emphasis parseEmphasis(String end) throws IOException {
            List<ParagraphElement> emph = parseContent(end);
            return (emph != null) ? new Emphasis(emph) : null;
        }

        private Strikeout parseStrikeout() throws IOException {
            List<ParagraphElement> strike = parseContent("--");
            return (strike != null) ? new Strikeout(strike) : null;
        }

        private Code parseCode(final String end) throws IOException {
            List<ParagraphElement> code = parseContent(end);
            return (code != null) ? new Code(code) : null;
        }

    }
}
