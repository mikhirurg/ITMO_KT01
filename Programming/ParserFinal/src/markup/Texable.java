package markup;

public interface Texable {
    void toHtml(StringBuilder build);
    void toTex(StringBuilder build);
}
