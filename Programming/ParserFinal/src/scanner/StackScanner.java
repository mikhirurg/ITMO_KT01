package scanner;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Stack;

public class StackScanner {
    private BufferedReader br;
    private static final int BUF_LEN = 10000000;
    private char[] buffer = new char[BUF_LEN];
    private int len;
    private int pos;
    private int nLine;
    private boolean EOF = false;


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

    private Stack<State> savedPositions = new Stack<>();

    @SuppressWarnings("unused")
    public StackScanner(InputStream is) {
        br = new BufferedReader(new InputStreamReader(is));
    }

    public StackScanner(String string) {
        br = new BufferedReader(new StringReader(string));
    }

    public StackScanner(File f, Charset chset) throws FileNotFoundException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream(f), chset));
    }

    @SuppressWarnings("unused")
    public StackScanner(File f) throws FileNotFoundException, UnsupportedEncodingException {
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

    public StringBuilder readAll(Map<Character, String> replace) throws IOException {
        StringBuilder builder = new StringBuilder();
        while (hasNextChar()) {
            char c = nextChar();
            if (replace.containsKey(c)) {
                builder.append(replace.get(c));
            } else {
                builder.append(c);
            }
        }
        return builder;
    }

    public StringBuilder readAll() throws IOException {
        StringBuilder builder = new StringBuilder();
        while (hasNextChar()) {
            char c = nextChar();
            builder.append(c);
        }
        return builder;
    }


    public void close() throws IOException {
        br.close();
    }

    public char getChar() {
        return buffer[pos];
    }

    public void movePos(int shift) {
        pos += shift;
    }

    public void saveState() {
        savedPositions.push(new State(pos, len, EOF, nLine));
    }

    public void dropState() {
        savedPositions.pop();
    }

    public boolean haveSavedStates() {
        return savedPositions.size() != 0;
    }

    public char getPrev() {
        if (pos > 0) {
            return buffer[pos-1];
        } else {
            return '\0';
        }
    }

    public void reset() {
        resetState(savedPositions.pop());
    }

    public void dropAll() {
        savedPositions.clear();
    }
}
