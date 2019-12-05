package markup;

import java.util.List;

public class Strong extends AbstractMarkup {

    public Strong(List<ParagraphElement> content) {
        super(content);
    }

    @Override
    public void toMarkdown(StringBuilder build) {
        build.append("__");
        super.toMarkdown(build);
        build.append("__");
    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append("<strong>");
        super.toHtml(build);
        build.append("</strong>");
    }

    @Override
    public void toTex(StringBuilder build) {
        build.append("\n\\textbf{");
        super.toTex(build);
        build.append("}");
    }
}
