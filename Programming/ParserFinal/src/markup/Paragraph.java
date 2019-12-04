package markup;

import java.util.List;

public class Paragraph implements Markable, ListItemElement, Htmlable {

    private List<ParagraphElement> content;

    public Paragraph(List<ParagraphElement> content) {
        this.content = content;
    }

    @Override
    public void toMarkdown(StringBuilder build) {
        for (ParagraphElement e : content) {
            e.toMarkdown(build);
        }
    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append("<p>");
        for (ParagraphElement e : content) {
            e.toHtml(build);
        }
        build.append("</p>\n");
    }

    @Override
    public void toTex(StringBuilder build) {
        for (ParagraphElement e : content) {
            e.toTex(build);
        }
    }
}
