package markup;

public class Image implements ParagraphElement{
    private String href;
    private String alt;

    public Image(String href, String alt) {
        this.href = href;
        this.alt = alt;
    }

    @Override
    public void toMarkdown(StringBuilder build) {
        build.append("![");
        build.append(alt);
        build.append("]");
        build.append("(");
        build.append(href);
        build.append(")");
    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append("<img alt='");
        build.append(alt);
        build.append("' src='");
        build.append(href);
        build.append("'>");
    }

    @Override
    public void toTex(StringBuilder build) {
        build.append("\n\\begin{figure}\n");
        build.append("\\centering");
        build.append("\\includegraphics[width=90mm]{");
        build.append(href);
        build.append("}\n\\caption{");
        build.append(alt);
        build.append(" \\label{overflow}}\n");
        build.append("\\end{figure}");
    }
}
