package markup;

public interface Htmlable {
    void toHtml(StringBuilder build);
    void toTex(StringBuilder build);
}
