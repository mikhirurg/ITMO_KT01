package md2html;

import markup.Htmlable;

import java.util.List;

public class Document {

    List<Htmlable> content;

    public Document(List<Htmlable> content) {
        this.content = content;
    }

    public void toHtml(StringBuilder build){
        for (Htmlable e : content){
            e.toHtml(build);
        }
    }

}
