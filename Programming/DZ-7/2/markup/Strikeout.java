package markup;

import java.util.List;

public class Strikeout extends Container {
    public Strikeout(List<Element> content) {
        super(content);
    }

    @Override
    public void toMarkdown(StringBuilder build) {
        build.append("~");
        super.toMarkdown(build);
        build.append("~");
    }
}
