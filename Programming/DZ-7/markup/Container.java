package markup;

import java.util.List;

abstract class Container implements Element {
    List<Element> content;
    public Container(List<Element> content){
        this.content = content;
    }

    @Override
    public void toMarkdown(StringBuilder build) {
        for (Element e : content){
            e.toMarkdown(build);
        }
    }
}
