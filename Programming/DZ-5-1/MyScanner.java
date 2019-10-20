import java.io.*;

public class MyScanner {
    private BufferedReader br;
    private int bufLen = 4096;
    private char[] buffer = new char[bufLen];
    private int len, pos;
    private boolean EOF = false;
    private boolean EOLN = false;

    public MyScanner(InputStream is) {
        br = new BufferedReader(new InputStreamReader(is));
    }

    public MyScanner(String string) {
        br = new BufferedReader(new StringReader(string));
    }

    public MyScanner(File f) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(f));
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
        return buffer[pos++];
    }

    public boolean hasNextChar() throws IOException {
        nextChar();
        pos--;
        return !EOF;
    }

    public boolean hasNext() throws IOException {
        skipBlank();
        nextChar();
        pos--;
        return !EOF;
    }

    public String readLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        char c;
        String l = System.lineSeparator();
        while (hasNextChar()) {
            c = nextChar();
            if (c == '\n') break;
            sb.append(c);
        }
        return sb.toString();
    }

    private void skipBlank() throws IOException {
        while (hasNextChar() && Character.isWhitespace(nextChar())) ;
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
            } else break;
        }
        return sb.toString();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    public double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    public long nextLong() throws IOException {
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

    public void close() throws IOException {
        br.close();
    }
}
