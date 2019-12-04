package markup;

public class Text implements ParagraphElements {
    private String text;

    public Text(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void append(String str) {
        text += str;
    }

    @Override
    public void toMarkdown(StringBuilder build) {
        build.append(text);
    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append(text);
    }

    @Override
    public void toTex(StringBuilder build) {
        build.append(text);
    }
}
