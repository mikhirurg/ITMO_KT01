package markup;

import java.util.List;

public class Emphasis extends Container {

    public Emphasis(List<Element> content) {
        super(content);
    }

    @Override
    public void toMarkdown(StringBuilder build) {
        build.append("*");
        super.toMarkdown(build);
        build.append("*");
    }
}
