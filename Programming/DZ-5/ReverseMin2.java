import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ReverseSum {
    public static void main(String[] args) throws IOException {
        CustomScannerString in = new CustomScannerString(System.in);
        int [][] input = new int[1][];
        int [] colsum = new int [1];
        int [] strsize = new int [1];
        int [] strsum = new int [1];
        int str = 0;
        while (in.hasNextLine()) {
            if (str == input.length) {
                input = Arrays.copyOf(input, str*2);
                strsize = Arrays.copyOf(strsize, str*2);
                strsum = Arrays.copyOf(strsum, str*2);
            }
            CustomScannerString lil_in = new CustomScannerString(in.nextLine());
            int col = 0;
            strsum[str] = 0;
            input[str] = new int[1];
            while (lil_in.hasNextInt()) {
                if (col == input[str].length) {
                    input[str] = Arrays.copyOf(input[str], col*2);
                    if (col == colsum.length) { // it is working
                        colsum = Arrays.copyOf(colsum, col*2);
                    }
                }
                input[str][col] = lil_in.nextInt();
                strsum[str] += input[str][col];
                colsum[col] += input[str][col];
                col++;
            }
            strsize[str] = col;
            str++;
        }
        for (int i = 0; i < str; i++) {
            for (int j = 0; j < strsize[i]; j++) {
                System.out.print((strsum[i] + colsum[j] - input[i][j]) + " ");
            }
            System.out.println();
        }
    }
}

class CustomScannerString {

    private String in;
    private int position = 0;
    private int savedposition = 0;
    private int len;
    private char cachemode = 'n';
    private int icache;
    private String scache;
    private double dcache;


    CustomScannerString(InputStream istream) throws IOException {
        StringBuilder temp = new StringBuilder();
        int c = istream.read();
        while (c != -1) {
            temp.append((char) c);
            c = istream.read();
        }
        in = temp.toString();
        len = in.length();
    }

    CustomScannerString(String str) {
        in = str;
    }

    private void save() {
        savedposition = position;
    }

    private void reset() {
        position = savedposition;
    }

    private void cache(String s) {
        scache = s;
        cachemode = 's';
    }

    private void cache(int i) {
        icache = i;
        cachemode = 'i';
    }

    private void cache(double d) {
        dcache = d;
        cachemode = 'd';
    }

    private boolean checkCacheType(char type) {
        return type == cachemode ? true : false;
    }

    private boolean checkCache() {
        return cachemode == 'n' ? false : true;
    }

    public int nextInt() throws IOException {
        if (checkCache()) {
            if (checkCacheType('i')) {
                cachemode = 'n';
                return icache;
            } else {
                cachemode = 'n';
                reset();
            }
        }
        while (Character.isWhitespace(in.charAt(position))) {
            position++;
            if (position == in.length()) {
                throw new InputMismatchException("Nothing to read");
            }
        }
        int start = position;
        while (!Character.isWhitespace(in.charAt(position))) {
            position++;
            if (position == len) {
                break;
            }
        }
        return Integer.parseInt(in.substring(start, position));
    }

    public String nextLine() throws IOException {
        if (checkCache()) {
            if (checkCacheType('s')) {
                cachemode = 'n';
                return scache;
            } else {
                cachemode = 'n';
                reset();
            }
        }
        int start = position;
        while (in.charAt(position) != '\n') {
            position++;
            if (position == len) {
                break;
            }
        }
        if (position != len) {
            position++;
        }
        return in.substring(start, position);
    }

    public boolean hasNextLine() {
        if (checkCache()) {
            if (checkCacheType('s')) {
                return true;
            } else {
                cachemode = 'n';
                reset();
            }
        }
        save();
        try {
            cache(nextLine());
        } catch (Exception e) {
            reset();
            return false;
        }
        return true;
    }

    public boolean hasNextInt() {
        if (checkCache()) {
            if (checkCacheType('i')) {
                return true;
            } else {
                cachemode = 'n';
                reset();
            }
        }
        save();
        try {
            cache(nextInt());
        } catch (Exception e) {
            reset();
            return false;
        }
        return true;
    }
}
