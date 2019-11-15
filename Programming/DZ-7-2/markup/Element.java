package markup;

public interface Element {
    void toMarkdown(StringBuilder build);
    void toHtml(StringBuilder build);
    void toTex(StringBuilder build);
}
