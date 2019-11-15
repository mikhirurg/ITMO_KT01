package markup;

import java.util.List;

public class OrderedList implements Element{
    private List<ListItem> content;
    OrderedList(List<ListItem> content) {
        this.content = content;
    }

    @Override
    public void toMarkdown(StringBuilder build) {

    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append("<ol>");
        for (Element e : content){
            e.toHtml(build);
        }
        build.append("</ol>");
    }

    @Override
    public void toTex(StringBuilder build) {
        build.append("\\begin{enumerate}");
        for (Element e : content){
            e.toTex(build);
        }
        build.append("\\end{enumerate}");
    }
}
