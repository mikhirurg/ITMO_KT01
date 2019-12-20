package expression.parser;

public class Lexer {
    int pos;
    long value;
    String variable;
    StringBuilder input;
    public Lexer(String input){
        this.input = new StringBuilder(input);
        pos = 0;
    }
    void skipWhiteSpace() {
        while (hasNext() && Character.isWhitespace(input.charAt(pos))) {
            pos++;
        }
    }
    boolean expect(String param) {
        skipWhiteSpace();
        if (!hasNext()) {
            return false;
        }
        for (int i = pos; i < pos + param.length(); i++) {
            if (input.charAt(i) != param.charAt(i-pos)) {
                return false;
            }
        }
        pos += param.length();
        return true;
    }

    boolean expectVar() {
        skipWhiteSpace();
        boolean var = input.charAt(pos) == 'x' || input.charAt(pos) == 'y' || input.charAt(pos) == 'z';

        if (var) {
            variable = String.valueOf(input.charAt(pos));
            pos++;
        }
        return var;
    }

    boolean expectNumber() {
        skipWhiteSpace();
        boolean digit = Character.isDigit(input.charAt(pos));
        int start = pos;
        if (digit) {
            while (hasNext() && Character.isDigit(input.charAt(pos))) {
                pos++;
            }
            value = Long.parseLong(input.substring(start, pos));
        }
        return digit;
    }

    boolean hasNext() {
        return pos < input.length();
    }

    public long getValue() {
        return value;
    }

    public String getVariable() {
        return variable;
    }

}
