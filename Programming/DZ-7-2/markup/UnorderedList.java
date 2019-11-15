package markup;

import java.util.List;

public class UnorderedList implements Element {
    List<ListItem> content;
    UnorderedList(List<ListItem> content) {
        this.content = content;
    }

    @Override
    public void toMarkdown(StringBuilder build) {

    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append("<ul>");
        for (Element e : content){
            e.toHtml(build);
        }
        build.append("</ul>");
    }

    @Override
    public void toTex(StringBuilder build) {
        build.append("\\begin{itemize}");
        for (Element e : content){
            e.toTex(build);
        }
        build.append("\\end{itemize}");
    }
}
