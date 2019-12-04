package markup;

import java.util.List;

public abstract class AbstractMarkup implements ParagraphElement {
    private List<ParagraphElement> content;

    AbstractMarkup(List<ParagraphElement> content) {
        this.content = content;
    }

    public void toMarkdown(StringBuilder build) {
        for (Markable e : content) {
            e.toMarkdown(build);
        }
    }

    public void toTex(StringBuilder build) {
        for (Markable e : content) {
            e.toTex(build);
        }
    }

    public void toHtml(StringBuilder build) {
        for (Markable e : content) {
            e.toHtml(build);
        }
    }
}
