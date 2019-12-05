package md2html;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SpecialSymbols {
    public static Set<Character> reservedSymbols = Set.of(
            '\0', '\r'
    );

    public static Map<Character, String> specialHtml = Map.of(
            '<', "&lt;",
            '>', "&gt;",
            '&', "&amp;"
    );

    public static Map<Character, String> specialTex;
    static {
        specialTex = new HashMap<>();
        specialTex.put('#', "\\#");
        specialTex.put('$', "\\$");
        specialTex.put('%', "\\%");
        specialTex.put('^', "\\textasciicircum");
        specialTex.put('&', "\\&");
        specialTex.put('~', "\\textasciitilde");
        specialTex.put('_', "\\_");
        specialTex.put('\\', "\\backslash");
        specialTex.put('{', "\\{");
        specialTex.put('}', "\\}");
    }
}
