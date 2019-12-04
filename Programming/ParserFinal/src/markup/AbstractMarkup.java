package markup;

import java.util.List;

public abstract class AbstractMarkup implements ParagraphElements {
    private List<Markable> content;

    AbstractMarkup(List<Markable> content) {
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
