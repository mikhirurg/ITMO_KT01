package markup;

import java.util.List;

public class Strikeout extends AbstractMarkup {
    public Strikeout(List<ParagraphElement> content) {
        super(content);
    }

    @Override
    public void toMarkdown(StringBuilder build) {
        build.append("~");
        super.toMarkdown(build);
        build.append("~");
    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append("<s>");
        super.toHtml(build);
        build.append("</s>");
    }

    @Override
    public void toTex(StringBuilder build) {
        build.append("\\textst{");
        super.toTex(build);
        build.append("}");
    }
}
