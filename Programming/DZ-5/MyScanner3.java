import java.io.*;

public class MyScanner3 {
    private BufferedReader reader;
    private StringBuffer buffer = new StringBuffer();
    private StringBuffer word = new StringBuffer();
    private int pos = 0;
    private int MAX_LEN = 100000;
    private boolean longLine = false;

    public MyScanner3(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
    }

    public MyScanner3(String stream) {
        reader = new BufferedReader(new StringReader(stream));
    }

    boolean hasNextLine() {
        if (pos == 0 && buffer.length() > 0) return true;
        buffer.setLength(0);
        buffer.trimToSize();
        int c = -1;
        try {
            while (((c = reader.read()) != '\n' && c!='\r') && c != -1 ) {
                if (buffer.length()>MAX_LEN && Character.isWhitespace((char) c)){
                    longLine = true;
                    buffer.append((char) c);
                    break;
                }
                buffer.append((char) c);
                longLine = false;
            }
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        pos = 0;
        return buffer.length() > 0;
    }

    String nextLine(){
        int c = -1;
        if (!hasNextLine()){
            System.out.println("IO Exception");
        }
        if (longLine) {
            try {
                while ((c = reader.read()) != '\n' && c!='\r' && c != -1) {
                    buffer.append((char) c);
                }
            } catch (IOException e){
                System.out.println("IO Exception");
            }
            longLine = false;
        }
        String result =  buffer.toString();
        pos = 0;
        buffer.setLength(0);
        buffer.trimToSize();
        return result;
    }

    boolean hasNext(){
        if (word.length() > 0) return true;
        if (pos < buffer.length()) {
            while (pos < buffer.length() && Character.isWhitespace(buffer.charAt(pos))) {
                pos++;
            }
            while (pos < buffer.length() && !Character.isWhitespace(buffer.charAt(pos))) {
                word.append(buffer.charAt(pos++));
            }
        } else {
            if (longLine) {
                pos = 0;
                buffer.setLength(0);
                buffer.trimToSize();
                longLine = false;
            }
            if (hasNextLine()) {
                return hasNext();
            }
            return false;
        }
        if (word.length() == 0) {
            if (longLine) {
                pos = 0;
                buffer.setLength(0);
                buffer.trimToSize();
                longLine = false;
            }
            if (hasNextLine()) {
                return hasNext();
            }
            return false;
        }
        return true;
    }

    String next(){
        if (word.length() > 0) {
            String result = word.toString();
            word.setLength(0);
            word.trimToSize();
            return result;
        }
        if (hasNext()) {
            return next();
        }
        return "";
    }

    boolean hasNextInt(){
        if (hasNext()) {
            try {
                Integer.parseInt(word.toString());
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    int nextInt(){
        return Integer.parseInt(next());
    }
}
