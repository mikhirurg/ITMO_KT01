package markup;

import java.util.List;

public class Strong extends AbstractMarkup {

    Strong(List<Markable> content) {
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
        build.append("\\textbf{");
        super.toTex(build);
        build.append("}");
    }
}
