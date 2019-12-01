package parser;

import markup.*;
import scanner.MyScanner;

import java.io.IOException;
import java.util.List;

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

        private Object parseContent(String end) throws IOException {
            boolean isEnd = false;
            int p = 0;
            List<Object> content = null;
            StringBuilder text = new StringBuilder();
            while (scanner.hasNextChar()) {
                char c = scanner.nextChar();
                if (c == end.charAt(p)) {
                    p++;
                } else {
                    p = 0;
                }
                if (p == end.length()) {
                    isEnd = true;
                    break;
                }

                if (c == '*' || c == '_') {
                    scanner.saveState();
                    Object emph = parseEmphasis(String.valueOf(c));
                    if (emph != null) {
                        content.add(new Text(text.toString()));
                        content.add(emph);
                        text.setLength(0);
                        scanner.dropState();
                    } else {
                        scanner.reset();
                    }
                } else {
                    if (c == '`') {
                        scanner.saveState();
                        Object code = parseCode(String.valueOf(c));
                        if (code != null) {
                            content.add(new Text(text.toString()));
                            content.add(code);
                            text.setLength(0);
                            scanner.dropState();
                        } else {
                            scanner.reset();
                        }
                    } else {
                        text.append(c);
                    }
                }
            }
            if (!isEnd) {
                return null;
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

        private Strong parseStrikeout(String end) throws IOException {
            Object strike = parseContent(end);
            if (strike == null) {
                return null;
            } else {
                return new Strong((List<Markable>) strike);
            }
        }

        private Code parseCode(final String end) throws IOException {
            Object emph = parseContent(end);
            if (emph == null) {
                return null;
            } else {
                return new Code((List<Text>) emph);
            }
        }

        private MDException error(final String message) {
            return new MDException(scanner.getPos() + ": " + message);
        }

    }
}
