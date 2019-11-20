package markup;

import java.util.List;

public class OrderedList extends AbstractList{
    private List<ListItem> content;
    OrderedList(List<ListItem> content) {
        super(content);
    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append("<ol>");
        super.toHtml(build);
        build.append("</ol>");
    }

    @Override
    public void toTex(StringBuilder build) {
        build.append("\\begin{enumerate}");
        super.toTex(build);
        build.append("\\end{enumerate}");
    }
}
