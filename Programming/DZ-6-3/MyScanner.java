import java.io.*;
import java.nio.charset.Charset;

public class MyScanner {
    private BufferedReader br;
    private int bufLen = 1048576;
    private char[] buffer = new char[bufLen];
    private int len;
    private int pos;
    private boolean EOF = false;

    interface TokenWorker {
        void skip() throws IOException;

        boolean append(StringBuilder b, Character c) throws IOException;
    }

    class WordParser implements TokenWorker {
        @Override
        public void skip() throws IOException {
            char next = nextChar();
            while (hasNextChar() && !isWord(next)) {
                next = nextChar();
            }
            pos--;
        }

        @Override
        public boolean append(StringBuilder b, Character c) {
            if (isWord(c)) {
                b.append(Character.toLowerCase(c));
                return true;
            }
            return false;
        }
    }

    class TokenParser implements TokenWorker {
        @Override
        public void skip() throws IOException {
            while (hasNextChar() && Character.isWhitespace(nextChar())) ;
            pos--;
        }

        @Override
        public boolean append(StringBuilder b, Character c) throws IOException {
            if (isWord(c)) {
                b.append(Character.toLowerCase(c));
                return true;
            }
            return false;
        }
    }

    TokenParser tParser = new TokenParser();
    WordParser wParser = new WordParser();

    MyScanner(InputStream is) {
        br = new BufferedReader(new InputStreamReader(is));
    }

    MyScanner(String string) {
        br = new BufferedReader(new StringReader(string));
    }

    MyScanner(File f, Charset chset) throws FileNotFoundException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream(f), chset));
    }

    MyScanner(File f) throws FileNotFoundException, UnsupportedEncodingException {
        String basicCharset = "UTF-8";
        br = new BufferedReader(new InputStreamReader(new FileInputStream(f), basicCharset));
    }

    private void readBuffer() throws IOException {
        len = br.read(buffer);
        while (len == 0) {
            len = br.read(buffer);
        }
        if (len == -1) {
            EOF = true;
        }
        pos = 0;
    }

    private char nextChar() throws IOException {
        if (pos >= len) {
            readBuffer();
        }
        return buffer[pos++];
    }

    boolean hasNextChar() throws IOException {
        nextChar();
        pos--;
        return !EOF;
    }

    boolean hasNext() throws IOException {
        skipBlank();
        nextChar();
        pos--;
        return !EOF;
    }

    String readLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        char c;
        while (hasNextChar()) {
            c = nextChar();
            if (c == '\n') {
                break;
            }
            if (c != '\r') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private void skipBlank() throws IOException {
        while (hasNextChar() && Character.isWhitespace(nextChar())) ;
        pos--;
    }

    private boolean isWord(char c) {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'';
    }

    private void skipBlankWord() throws IOException {
        char next = nextChar();
        while (hasNextChar() && !isWord(next)) {
            next = nextChar();
        }
        pos--;
    }


    public String next(TokenWorker worker) throws IOException {
        worker.skip();
        StringBuilder sb = new StringBuilder();
        char c;
        while (hasNextChar()) {
            c = nextChar();
            if (!worker.append(sb, c)) {
                break;
            }
        }
        return sb.toString();
    }


    String nextWord() throws IOException {
        return next(wParser);
    }

    String nextToken() throws IOException {
        return next(tParser);
    }


    public int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

    long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    public byte nextByte() throws IOException {
        return Byte.parseByte(nextToken());
    }

    public short nextShort() throws IOException {
        return Short.parseShort(nextToken());
    }

    public float nextFloat() throws IOException {
        return Float.parseFloat(nextToken());
    }

    void close() throws IOException {
        br.close();
    }
}
