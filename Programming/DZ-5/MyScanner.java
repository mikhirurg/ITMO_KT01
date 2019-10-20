import java.io.*;
import java.util.Arrays;

public class MyScanner {
    private Reader read;
    private int[] buffer;
    private int buf_size;
    private int pos;

    private boolean is_wire(char c) {
        return Character.isWhitespace(c);
    }

    private boolean is_newline(char c) {
        return c == '\n';
    }

    MyScanner(InputStream read) {
        buf_size = 0;
        buffer = new int[1];
        pos = 0;
        this.read = new InputStreamReader(read);
    }

    MyScanner(Reader read) {
        buf_size = 0;
        buffer = new int[1];
        pos = 0;
        this.read = read;
    }

    MyScanner(String read) {
        buf_size = 0;
        buffer = new int[1];
        pos = 0;
        this.read = new StringReader(read);
    }

    Integer nextInt() {
        String tmp = next();
        return Integer.parseInt(tmp);
    }

    String nextLine() {
        if (pos >= buf_size) {
            buf_size = 0;
            pos = 0;
            buffer = new int[1];
        } else return nextLineBuffer();
        StringBuilder line = new StringBuilder();
        int c;
        try {
            c = read.read();
            while (!is_newline((char) c)) {
                line.append((char) c);
                c = read.read();
            }
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        if (line.length() == 0) return null;
        return line.toString();
    }

    private void increaseBuffer(){
        if (pos >= buffer.length) {
            buffer = Arrays.copyOf(buffer, buffer.length * 2);
        }
    }

    private String nextLineBuffer(){
        StringBuilder word = new StringBuilder();
        int c;
        c = buffer[pos];
        pos++;
        while (pos < buf_size && pos < buffer.length){
            word.append((char) c);
            c = buffer[pos];
            pos++;
        }
        return word.toString();
    }

    private String nextBuffer() {
        StringBuilder word = new StringBuilder();
        int c;
        c = buffer[pos];
        pos++;
        while (is_wire((char) c) && pos < buf_size) {
            c = buffer[pos];
            pos++;
        }
        if (pos < buf_size && !is_wire((char) c)) word.append((char) c);
        c = buffer[pos];
        pos++;
        while (!is_wire((char) c) && pos < buf_size) {
            word.append((char) c);
            c = buffer[pos];
            pos++;
        }
        if (word.length() == 0) return null;
        return word.toString();
    }

    String next() {
        if (pos >= buf_size) {
            buf_size = 0;
            pos = 0;
            buffer = new int[1];
        } else return nextBuffer();
        StringBuilder word = new StringBuilder();
        int c;
        try {
            c = read.read();
            while (is_wire((char) c) && c != -1) {
                c = read.read();
            }
            if (c != -1 && !is_wire((char) c)) word.append((char) c);
            c = read.read();
            while (!is_wire((char) c) && c != -1) {
                word.append((char) c);
                c = read.read();
            }
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        if (word.length() == 0) return null;
        return word.toString();
    }

    String readNextBuffer(){
        StringBuilder word = new StringBuilder();
        int c;
        int old_pos = pos;
        try {
            increaseBuffer();
            c = read.read();
            buffer[pos] = c;
            pos++;
            while (is_wire((char) c) && c != -1) {
                increaseBuffer();
                c = read.read();
                buffer[pos] = c;
                pos++;
            }
            if (c != -1 && !is_wire((char) c) && !is_newline((char) c)) word.append((char) c);
            c = read.read();
            increaseBuffer();
            buffer[pos] = c;
            pos++;
            while (!is_wire((char) c) && c != -1) {
                increaseBuffer();
                word.append((char) c);
                c = read.read();
                buffer[pos] = c;
                pos++;
            }
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        buf_size = pos;
        pos = old_pos;
        return word.toString();
    }

    boolean hasNext() {
        if (pos < buf_size) return hasNextBuffer();
        return readNextBuffer().length() != 0;
    }

    boolean hasNextInt(){
        if (pos < buf_size && buf_size>0){
            try {
                Integer.parseInt(getNextBuffer());
            } catch (NumberFormatException e){
                return false;
            }
            return true;
        }
        return readNextBuffer().length() != 0;
    }

    boolean hasNextLine() {
        StringBuilder line = new StringBuilder();
        int c;
        int old_pos = pos;
        if (pos>=buf_size) {
            buffer = new int[1];
            pos = 0;
            buf_size = 0;
        } else return hasNextLineBuffer();
        try {
            c = read.read();
            if (c == -1) return false;
            increaseBuffer();
            buffer[pos] = c;
            pos++;
            while (!is_newline((char) c)) {
                increaseBuffer();
                line.append((char) c);
                c = read.read();
                buffer[pos] = c;
                pos++;
            }
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        buf_size = pos;
        pos = 0;
        return line.length() != 0;
    }

    private String getNextBuffer(){
        StringBuilder word = new StringBuilder();
        int k = pos;
        int c= 0 ;
        c = buffer[k];
        k++;
        while (is_wire((char) c) && k<=buf_size) {
            c = buffer[k];
            k++;
        }
        if (c != -1 && !is_wire((char) c)) word.append((char) c);
        c = buffer[k];
        k++;
        while (!is_wire((char) c) && k<=buf_size) {
            word.append((char) c);
            c = buffer[k];
            k++;
        }
        return word.toString();
    }

    private boolean hasNextBuffer(){
        int k = 0;
        for (int i = 0; i < buffer.length; i++){
            if (!is_wire((char)buffer[i])){
                return true;
            }
        }
        return false;
    }
    private boolean hasNextLineBuffer(){
        return pos < buf_size && buffer.length > 0;
    }


}
