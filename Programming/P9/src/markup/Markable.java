package markup;

public interface Markable {
    void toMarkdown(StringBuilder build);

    void toHtml(StringBuilder build);

    void toTex(StringBuilder build);
}
