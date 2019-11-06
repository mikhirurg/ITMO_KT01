import java.io.*;
import java.nio.charset.Charset;

public class MyScanner {
    private BufferedReader br;
    private int bufLen = 1048576;
    private char[] buffer = new char[bufLen];
    private int len;
    private int pos;
    private boolean EOF = false;
    final private int DECODE_WORD = 1;
    final private int DECODE_TOKEN = 2;

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

    private String next(int TOKEN_TYPE) throws IOException {
        switch (TOKEN_TYPE){
            case DECODE_WORD: skipBlankWord();
            break;
            case DECODE_TOKEN: skipBlank();
        }
        StringBuilder sb = new StringBuilder();
        char c;
        while (hasNextChar()) {
            c = nextChar();
            boolean isReading = false;
            switch (TOKEN_TYPE){
                case DECODE_WORD:{
                    isReading = isWord(c);
                    c = Character.toLowerCase(c);
                }
                break;
                case DECODE_TOKEN:{
                    isReading = !Character.isWhitespace(c);
                }
            }
            if (isReading) {
                sb.append(c);
            } else {
                break;
            }
        }
        return sb.toString();
    }

    String nextWord() throws IOException {
        return next(DECODE_WORD);
    }

    String nextToken() throws IOException {
        return next(DECODE_TOKEN);
    }


    public int nextInt() throws IOException {
        return Integer.parseInt(next(DECODE_TOKEN));
    }

    public double nextDouble() throws IOException {
        return Double.parseDouble(next(DECODE_TOKEN));
    }

    long nextLong() throws IOException {
        return Long.parseLong(next(DECODE_TOKEN));
    }

    public byte nextByte() throws IOException {
        return Byte.parseByte(next(DECODE_TOKEN));
    }

    public short nextShort() throws IOException {
        return Short.parseShort(next(DECODE_TOKEN));
    }

    public float nextFloat() throws IOException {
        return Float.parseFloat(next(DECODE_TOKEN));
    }

    void close() throws IOException {
        br.close();
    }
}
