package markup;

import java.util.List;

public class Code implements ParagraphElement {
    private List<ParagraphElement> content;

    public Code(List<ParagraphElement> content) {
        this.content = content;
    }

    @Override
    public void toMarkdown(StringBuilder build) {
        build.append("`");
        for (Markable e : content) {
            e.toMarkdown(build);
        }
        build.append("`");
    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append("<code>");
        for (Markable e : content) {
            e.toHtml(build);
        }
        build.append("</code>");
    }

    @Override
    public void toTex(StringBuilder build) {
        build.append("\\texttt{");
        for (Markable e : content) {
            e.toTex(build);
        }
        build.append("}");
    }
}
