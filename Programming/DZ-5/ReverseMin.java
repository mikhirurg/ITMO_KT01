import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ReverseMin {
    static int min(int a, int b) {
        if (a > b) {
            return b;
        }
        return a;
    }
    public static void main(String[] args) throws IOException {
        CustomScannerString in = new CustomScannerString(System.in);
        int [] colmin = new int [1];
        colmin[0] = Integer.MAX_VALUE;
        int [] strsize = new int [1];
        int [] strmin = new int [1];
        int str = 0;
        while (in.hasNextLine()) {
            if (str == strsize.length) {
                strsize = Arrays.copyOf(strsize, str*2);
                strmin = Arrays.copyOf(strmin, str*2);
            }
            CustomScannerString lil_in = new CustomScannerString(in.nextLine());
            int col = 0;
            strmin[str] = Integer.MAX_VALUE;
            while (lil_in.hasNextInt()) {
                if (col == colmin.length) {
                    colmin = Arrays.copyOf(colmin, col*2);
                    for (int i = col; i < col * 2; i++) {
                        colmin[i] = Integer.MAX_VALUE;
                    }
                }
                int t = lil_in.nextInt();
                strmin[str] = min(strmin[str], t);
                colmin[col] = min(colmin[col], t);
                col++;
            }
            strsize[str] = col;
            str++;
        }
        for (int i = 0; i < str; i++) {
            for (int j = 0; j < strsize[i]; j++) {
                System.out.print(min(strmin[i], colmin[j]) + " ");
            }
            System.out.println();
        }
    }


    static class CustomScannerString {

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
}
