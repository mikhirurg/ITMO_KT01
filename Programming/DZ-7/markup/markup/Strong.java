package markup;

import java.util.List;

public class Strong extends Container {

    public Strong(List<Element> content) {
        super(content);
    }

    @Override
    public void toMarkdown(StringBuilder build) {
        build.append("__");
        super.toMarkdown(build);
        build.append("__");
    }
}
