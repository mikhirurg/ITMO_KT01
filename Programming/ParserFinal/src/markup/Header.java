package markup;

import java.util.List;

public class Header implements Htmlable {
    List<ParagraphElement> content;
    int level;

    public Header(List<ParagraphElement> content, int level) {
        this.content = content;
        this.level = level;
    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append("<h");
        build.append(level);
        build.append(">");
        for (Markable e : content) {
            e.toHtml(build);
        }
        build.append("</h");
        build.append(level);
        build.append(">\n");
    }
}
