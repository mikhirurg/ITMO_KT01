package markup;

public class Text implements ParagraphElement {
    private String text;

    public Text(String text) {
        this.text = text;
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
