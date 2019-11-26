package markup;

import java.util.List;

public class UnorderedList extends AbstractList {
    private List<ListItem> content;

    public UnorderedList(List<ListItem> content) {
        super(content);
    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append("<ul>");
        super.toHtml(build);
        build.append("</ul>");
    }

    @Override
    public void toTex(StringBuilder build) {
        build.append("\\begin{itemize}");
        super.toTex(build);
        build.append("\\end{itemize}");
    }
}
