package markup;

import java.util.List;

public class Paragraph implements Markable, ListItemElement, Htmlable {

    private List<ParagraphElements> content;

    public Paragraph(List<ParagraphElements> content) {
        this.content = content;
    }

    @Override
    public void toMarkdown(StringBuilder build) {
        for (ParagraphElements e : content) {
            e.toMarkdown(build);
        }
    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append("<p>");
        for (ParagraphElements e : content) {
            e.toHtml(build);
        }
        build.append("</p>");
    }

    @Override
    public void toTex(StringBuilder build) {
        for (ParagraphElements e : content) {
            e.toTex(build);
        }
    }
}
