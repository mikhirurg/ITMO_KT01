package markup;

import java.util.List;

public class Link implements ParagraphElements {
    private List<Markable> content;
    private String href;

    public Link(String href, List<Markable> content) {
        this.content = content;
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    @Override
    public void toMarkdown(StringBuilder build) {
        build.append("[");
        for (Markable e : content) {
            e.toHtml(build);
        }
        build.append(href);
    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append("<a href='").append(href).append("'>");
        for (Markable e : content) {
            e.toHtml(build);
        }
        build.append("</a>");
    }

    @Override
    public void toTex(StringBuilder build) {
        build.append("\\href{").append(href).append("}{");
        for (Markable e : content) {
            e.toTex(build);
        }
        build.append("}");
    }
}
