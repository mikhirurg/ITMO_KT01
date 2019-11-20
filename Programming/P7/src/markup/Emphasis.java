package markup;

import java.util.List;

public class Emphasis extends AbstractMarkup {

    Emphasis(List<Markable> content) {
        super(content);
    }

    @Override
    public void toMarkdown(StringBuilder build) {
        build.append("*");
        super.toMarkdown(build);
        build.append("*");
    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append("<em>");
        super.toHtml(build);
        build.append("</em>");
    }

    @Override
    public void toTex(StringBuilder build) {
        build.append("\\emph{");
        super.toTex(build);
        build.append("}");
    }
}
