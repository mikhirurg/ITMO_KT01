package markup;

import java.util.List;

public class ListItem implements Element {
    List<Element> content;
    ListItem(List<Element> content) {
        this.content = content;
    }

    @Override
    public void toMarkdown(StringBuilder build) {

    }

    @Override
    public void toHtml(StringBuilder build){
        build.append("<li>");
        for (Element e : content){
            e.toHtml(build);
        }
        build.append("</li>");
    }

    @Override
    public void toTex(StringBuilder build){
        build.append("\\item ");
        for (Element e : content){
            e.toTex(build);
        }
    }

}
