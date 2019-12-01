package markup;

import java.util.List;

public class Code implements ParagraphElements {
    private List<Text> content;

    public Code(List<Text> content) {
        this.content = content;
    }

    @Override
    public void toMarkdown(StringBuilder build) {
        build.append("`");
        for (Text e : content) {
            e.toMarkdown(build);
        }
        build.append("`");
    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append("<code>");
        for (Text e : content) {
            e.toHtml(build);
        }
        build.append("</code>");
    }

    @Override
    public void toTex(StringBuilder build) {
        build.append("\\begin{verbatim}");
        for (Text e : content) {
            e.toTex(build);
        }
        build.append("\\end{verbatim}");
    }
}
