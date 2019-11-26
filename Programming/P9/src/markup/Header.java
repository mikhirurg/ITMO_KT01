package markup;

import markup.Htmlable;
import markup.ParagraphElements;

public class Header implements Htmlable {
    String text;
    int level;

    public Header(String text, int level) {
        this.text = text;
        this.level = level;
    }

    public Header(Text text, int level) {
        this.text = text.getText();
        this.level = level;
    }


    @Override
    public void toHtml(StringBuilder build) {
        build.append("<h");
        build.append(level);
        build.append(">");
        build.append(text);
        build.append("</h");
        build.append(level);
        build.append(">");
    }
}
