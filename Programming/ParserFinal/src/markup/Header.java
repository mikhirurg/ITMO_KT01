package markup;

import java.util.List;

public class Header implements Htmlable {
    List<Markable> content;
    int level;

    public Header(List<Markable> content, int level) {
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
