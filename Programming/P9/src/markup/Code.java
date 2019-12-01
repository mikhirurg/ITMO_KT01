package markup;

public class Code implements ParagraphElements {
    private String text;

    @Override
    public void toMarkdown(StringBuilder build) {
        build.append("`");
        build.append(text);
        build.append("`");
    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append("<code>");
        build.append(text);
        build.append("</code>");
    }

    @Override
    public void toTex(StringBuilder build) {
        build.append("\\begin{verbatim}");
        build.append(text);
        build.append("\\end{verbatim}");
    }
}
