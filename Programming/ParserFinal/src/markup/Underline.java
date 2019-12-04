package markup;

import java.util.List;

public class Underline extends AbstractMarkup{
    public Underline(List<ParagraphElement> content) {
        super(content);
    }
    @Override
    public void toMarkdown(StringBuilder build) {
        build.append("++");
        super.toMarkdown(build);
        build.append("++");
    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append("<u>");
        super.toHtml(build);
        build.append("</u>");
    }

    @Override
    public void toTex(StringBuilder build) {
        build.append("\\underline{");
        super.toTex(build);
        build.append("}");
    }
}
