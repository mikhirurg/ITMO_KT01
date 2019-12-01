package parser;

import markup.*;
import scanner.MyScanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Md2Html {
    public static Document parse(String md) throws IOException {
        return new Document((List<Htmlable>) new Parser2(md).parse());
    }
    static class Parser2 {
        private final MyScanner scanner;
        private char ch;
        private int pos;

        Parser2(final String data) {
            //Create new scanner from data string adding ending symbols "\0\0"
            this.scanner = new MyScanner(data+"\0\0");
        }

        Object parse() throws IOException {
            boolean isEnd = false;
            int p = 0;
            List<Object> content = new ArrayList<>();
            String seq = "\0\0";
            while (scanner.hasNextChar()) {
                scanner.saveState();
                char c = scanner.nextChar();
                if (c==seq.charAt(p)) {
                    p++;
                } else {
                    p = 0;
                }
                if (p == seq.length()) {
                    break;
                }
                if (c == '#') {
                    int level = 0;
                    while (c == '#') {
                        c = scanner.nextChar();
                        level++;
                    }
                    if (c == ' ' && level <= 6) {
                        content.add(parseHeader("\n\n", level));
                    } else {
                        scanner.reset();
                    }
                } else {
                    if (c!='\n' && c != '\0') {
                        if (scanner.haveSavedStates()) {
                            scanner.reset();
                        }
                        content.add(parseParagraph("\n\n"));
                    } else {
                        if (scanner.haveSavedStates()) {
                            scanner.dropState();
                            scanner.saveState();
                        } else {
                            scanner.saveState();
                        }
                    }
                }
            }
            return content;
        }

        private Object parseContent(String end) throws IOException {
            boolean isEnd = false;
            int p = 0;
            List<Object> content = new ArrayList<>();
            StringBuilder text = new StringBuilder();
            char old = '\0';
            while (scanner.hasNextChar()) {
                char c = scanner.nextChar();
                if (c == end.charAt(p)) {
                    p++;
                } else {
                    p = 0;
                }
                if (p == end.length()) {
                    isEnd = true;
                    if (end.equals("\n\n")) {
                        text.setLength(text.length()-1);
                    }
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
                        boolean isAdding = !(c==old && c == '\n');
                        if (isAdding) {
                            text.append(c);
                        }
                    }
                }
                old = c;
            }
            if (!isEnd) {
                if (!end.equals("\n\n")) {
                    return null;
                }
            }
            if (text.length() != 0) {
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
