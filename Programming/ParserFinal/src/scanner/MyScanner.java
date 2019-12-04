package scanner;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Stack;

public class MyScanner {
    private BufferedReader br;
    private int bufLen = 20048576;
    private char[] buffer = new char[bufLen];
    private int len;
    private int pos;
    private int nLine;
    private boolean EOF = false;
    private int savedPos = 0;
    private int savedLen = 0;


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

    private static class State {
        private int pos;
        private int len;
        private boolean EOF;
        private int nline;
        State(int pos, int len, boolean EOF, int nline) {
            this.pos = pos;
            this.len = len;
            this.EOF = EOF;
            this.nline = nline;
        }
        int getPos() {
            return pos;
        }
        int getLen() {
            return len;
        }
        boolean getEOF() {
            return EOF;
        }
        int getNline() {
            return nline;
        }
    }

    void resetState(State state) {
        pos = state.getPos();
        len = state.getLen();
        EOF = state.getEOF();
        nLine = state.getNline();
    }

    private Stack<State> savedStations = new Stack<>();
    private TokenParser tParser = new TokenParser();
    private WordParser wParser = new WordParser();

    MyScanner(InputStream is) {
        br = new BufferedReader(new InputStreamReader(is));
    }

    public MyScanner(String string) {
        br = new BufferedReader(new StringReader(string));
    }

    public MyScanner(File f, Charset chset) throws FileNotFoundException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream(f), chset));
    }

    public MyScanner(File f) throws FileNotFoundException, UnsupportedEncodingException {
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

    public char nextChar() throws IOException {
        if (pos >= len) {
            readBuffer();
        }
        if (getChar() == '\r') {
            pos++;
        }
        return buffer[pos++];
    }

    public boolean hasNextChar() throws IOException {
        nextChar();
        pos--;
        return !EOF;
    }

    public String readAll() throws IOException {
        StringBuilder builder = new StringBuilder();
        while (hasNextChar()) {
            builder.append(nextChar());
        }
        return builder.toString();
    }

    public String readAll(Map<Character, String> replace) throws IOException {
        StringBuilder builder = new StringBuilder();
        while (hasNextChar()) {
            char c = nextChar();
            if (replace.containsKey(c)) {
                builder.append(replace.get(c));
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    boolean hasNext() throws IOException {
        tParser.skip();
        nextChar();
        pos--;
        return !EOF;
    }

    public String readLine() throws IOException {
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


    private boolean isWord(char c) {
        return c =='#' || Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'';
    }

    private String next(TokenWorker worker) throws IOException {
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


    public String nextWord() throws IOException {
        return next(wParser);
    }

    private String nextToken() throws IOException {
        return next(tParser);
    }


    public int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

    public long nextLong() throws IOException {
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

    public void close() throws IOException {
        br.close();
    }

    public char getChar() {
        return buffer[pos];
    }

    public int getPos() {
        return pos;
    }

    public void movePos(int shift) {
        pos += shift;
    }

    public void saveState() {
        savedStations.push(new State(pos, len, EOF, nLine));
    }

    public void dropState() {
        savedStations.pop();
    }

    public void deleteStates() {
        savedStations.clear();
    }

    public boolean haveSavedStates() {
        return savedStations.size() != 0;
    }

    public char getPrev() {
        if (pos > 0) {
            return buffer[pos-1];
        } else {
            return '\0';
        }
    }
    public char getNext() {
        if (pos < buffer.length-1) {
            return buffer[pos+1];
        } else {
            return '\0';
        }
    }


    public void reset() {
        resetState(savedStations.pop());
    }

    public void dropAll() {
        savedStations.clear();
    }
}
