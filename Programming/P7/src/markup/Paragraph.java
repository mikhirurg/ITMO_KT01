package markup;

import java.util.List;

public class Paragraph implements Markable, ListItemElement {

    private List<ParagraphElements> content;

    public Paragraph(List<ParagraphElements> content) {
        this.content = content;
    }

    public void toMarkdown(StringBuilder build) {
        for (ParagraphElements e : content){
            e.toMarkdown(build);
        }
    }

    @Override
    public void toHtml(StringBuilder build) {
        for (ParagraphElements e : content){
            e.toHtml(build);
        }
    }

    @Override
    public void toTex(StringBuilder build) {
        for (ParagraphElements e : content){
            e.toTex(build);
        }
    }
}
