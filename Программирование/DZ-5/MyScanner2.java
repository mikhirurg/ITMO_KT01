import java.io.*;

public class MyScanner2 {
    private Reader read;
    private String buffer = "";
    private int pos = 0;

    MyScanner2(InputStream read) {
        this.read = new InputStreamReader(read);
    }

    MyScanner2(Reader read) {
        this.read = read;
    }

    MyScanner2(String read) {
        this.read = new StringReader(read);
    }

    String next() {
        if (pos == buffer.length()) {
            buffer = nextLine();
            pos = 0;
        }
        if (buffer == null) return null;
        StringBuilder word = new StringBuilder();
        char c;
        c = buffer.charAt(pos);
        pos++;
        if (pos >= buffer.length()) {
            word.append(c);
            return word.toString();
        }
        while (Character.isWhitespace(c) && c < buffer.length()) {
            c = buffer.charAt(pos);
            pos++;
        }
        if (pos < buffer.length() && !Character.isWhitespace(c)) word.append(c);
        c = buffer.charAt(pos);
        pos++;
        while (!Character.isWhitespace(c) && pos < buffer.length()) {
            word.append(c);
            c = buffer.charAt(pos);
            pos++;
        }
        return word.toString();
    }

    String nextLine() {
        if (pos == 0 && buffer.length()>0) {
            return buffer;
        }
        StringBuilder line = new StringBuilder();
        int c;
        try {
            c = read.read();
            while ((c != '\n') && (c!=-1)) {
                line.append((char) c);
                c = read.read();
            }
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        if (line.length() == 0) return null;
        return line.toString();
    }

    int nextInt(){
        return Integer.parseInt(next());
    }

    double nextDouble(){
        return Double.parseDouble(next());
    }

    long nextLong(){
        return Long.parseLong(next());
    }

    boolean hasNextLine(){
        StringBuilder line = new StringBuilder();
        int c;
        try {
            c = read.read();
            while ((c != '\n') && (c!=-1)) {
                line.append((char) c);
                c = read.read();
            }
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        buffer = line.toString();
        return (line.length()>0);
    }

    boolean hasNext() {
        int old_pos = pos;
        String word = next();
        pos = old_pos;
        return (word != null && word.length()>0);
    }

    boolean hasNextInt() {
        int old_pos = pos;
        String word = next();
        pos = old_pos;
        if (word==null) return false;
        try {
            Integer.parseInt(word);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }







}
