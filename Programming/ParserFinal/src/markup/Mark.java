package markup;

import java.util.List;

public class Mark extends AbstractMarkup {

    public Mark(List<Markable> content) {
        super(content);
    }

    @Override
    public void toMarkdown(StringBuilder build) {
        build.append("~~");
        super.toMarkdown(build);
        build.append("~~");
    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append("<mark>");
        super.toHtml(build);
        build.append("</mark>");
    }

    @Override
    public void toTex(StringBuilder build) {
        build.append("\\hl{");
        super.toTex(build);
        build.append("}");
    }
}
