import java.io.*;
import java.nio.charset.Charset;

public class MyScanner {
    private BufferedReader br;
    private int bufLen = 128;
    private char[] buffer = new char[bufLen];
    private int len, pos;
    private boolean EOF = false;

    MyScanner(InputStream is) {
        br = new BufferedReader(new InputStreamReader(is));
    }

    MyScanner(String string) {
        br = new BufferedReader(new StringReader(string));
    }

    MyScanner(File f, Charset chset) throws FileNotFoundException {
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
        while (hasNextChar() && Character.isWhitespace(nextChar()));
        pos--;
    }

    private boolean isWord(char c) {
        return Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'';
    }

    private void skipBlankWord() throws IOException {
        char next = nextChar();
        while (hasNextChar() && !isWord(next)){
            next = nextChar();
        }
        pos--;
    }

    public String next() throws IOException {
        skipBlank();
        StringBuilder sb = new StringBuilder();
        char c;
        while (hasNextChar()) {
            c = nextChar();
            if (!Character.isWhitespace(c)) {
                sb.append(c);
            } else {
                break;
            }
        }
        return sb.toString();
    }

    String nextWord() throws IOException {
        skipBlankWord();
        StringBuilder sb = new StringBuilder();
        char c;
        while (hasNextChar()) {
            c = nextChar();
            if (isWord(c)) {
                sb.append(Character.toLowerCase(c));
            } else {
                break;
            }
        }
        return sb.toString();
    }


    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    public byte nextByte() throws IOException {
        return Byte.parseByte(next());
    }

    public short nextShort() throws IOException {
        return Short.parseShort(next());
    }

    public float nextFloat() throws IOException {
        return Float.parseFloat(next());
    }

    void close() throws IOException {
        br.close();
    }
}
