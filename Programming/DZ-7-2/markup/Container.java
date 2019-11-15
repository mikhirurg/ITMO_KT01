package markup;

import java.util.List;

abstract class Container implements Element {
    List<Element> content;

    Container(List<Element> content){
        this.content = content;
    }

    @Override
    public void toMarkdown(StringBuilder build) {
        for (Element e : content){
            e.toMarkdown(build);
        }
    }
    @Override
    public void toHtml(StringBuilder build){
        for (Element e : content){
            e.toHtml(build);
        }
    }
    @Override
    public void toTex(StringBuilder build){
        for (Element e : content){
            e.toTex(build);
        }
    }
}
