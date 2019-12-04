package markup;

import java.util.List;

public class ListItem implements Texable {
    private List<Texable> content;

    public ListItem(List<Texable> content) {
        this.content = content;
    }

    public void toHtml(StringBuilder build) {
        build.append("<li>");
        for (Texable e : content) {
            e.toHtml(build);
        }
        build.append("</li>");
    }

    public void toTex(StringBuilder build) {
        build.append("\\item ");
        for (Texable e : content) {
            e.toTex(build);
        }
    }

}
