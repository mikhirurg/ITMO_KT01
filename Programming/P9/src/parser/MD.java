package parser;

import markup.*;
import scanner.MyScanner;

import java.io.IOException;
import java.util.*;

public class MD {
    enum ParseContext {
        HEADER, PARAGRAPH, LINK, DOCUMENT
    }

    public static Document parse(String md) throws IOException {
        return new Parser(md).parse();
    }

    private static class Parser {
        private final MyScanner scanner;
        private char ch;
        private int pos;
        private ParseContext parseState;

        Parser(final String data) {
            this.scanner = new MyScanner(data);
        }

        Document parse() throws IOException {
            ArrayList<Htmlable> content = new ArrayList<>();
            ArrayList<Markable> headerContent = null;
            ArrayList<ParagraphElements> paragraphContent = null;
            parseState = ParseContext.DOCUMENT;
            int hLevel = 0;
            String str;
            while (scanner.hasNextChar()) {
                str = scanner.readLine();
                MyScanner strScan = new MyScanner(str);
                if (parseState != ParseContext.DOCUMENT) {
                    if ("".equals(str)) {
                        switch (parseState) {
                            case HEADER:
                                content.add(new Header(headerContent, hLevel));
                                hLevel = 0;
                                headerContent = null;
                                break;
                            case PARAGRAPH:
                                content.add(new Paragraph(paragraphContent));
                                paragraphContent = null;
                                break;
                        }
                        parseState = ParseContext.DOCUMENT;
                    } else {
                        switch (parseState) {
                            case HEADER:
                                headerContent.add(new Text(str));
                                break;
                            case PARAGRAPH:
                                paragraphContent.add(new Text(str));
                                break;
                        }
                    }
                } else {
                    String word = strScan.nextWord();
                    if (word.length() <= 6) {
                        int i = 0;
                        while (i < word.length() && word.charAt(i) == '#') i++;
                        if (i == word.length()) {
                            hLevel = word.length();
                            headerContent = new ArrayList<Markable>();
                            parseHeaderText(strScan, headerContent);
                            parseState = ParseContext.HEADER;
                        } else {
                            parseState = ParseContext.PARAGRAPH;
                            paragraphContent = new ArrayList<>();
                            strScan = new MyScanner(str);
                            parseParagraphText(strScan, paragraphContent);
                        }
                    }
                }
            }
            if (parseState != ParseContext.DOCUMENT) {
                switch (parseState) {
                    case HEADER:
                        content.add(new Header(headerContent, hLevel));
                        break;
                    case PARAGRAPH:
                        content.add(new Paragraph(paragraphContent));
                        break;
                }
            }

            return new Document(content);
        }

        private void parseHeader(MyScanner scanner, ArrayList<Htmlable> content, int level) {

        }

        private void parseHeaderText(MyScanner scanner, ArrayList<Markable> content) throws IOException {
            content.add(new Text(scanner.readLine()));
        }

        private void parseParagraphText(MyScanner scanner, ArrayList<ParagraphElements> content) throws IOException {
            Text currentText = new Text("");
            while (scanner.hasNextChar()) {
                char c = scanner.nextChar();
                switch (c) {
                    case '*' :
                        scanner.savePos();
                        AbstractMarkup markup = parseMarkup(scanner, '*');
                        if (markup == null) {
                            currentText.append("*");
                            scanner.reset();
                        } else {
                            if (currentText.getText().length() > 0) {
                                content.add(currentText);
                                content.add(markup);
                                currentText = new Text("");
                            }
                        }
                        break;
                    default:
                        currentText.append(String.valueOf(c));
                }

            }
            if (currentText.getText().length() > 0) {
                content.add(currentText);
            }
        }

        private AbstractMarkup parseMarkup(MyScanner scanner, char exp) throws IOException {
            String str = scanner.readUntil(String.valueOf(exp));
            return str != null ? new Emphasis(List.of(new Text(str))) : null;
        }
    }
}
