package markup;

import java.util.List;

public class Header extends Container {
    int number;
    Header(int number, List<Element> content){
        super(content);
        this.number = number;
    }
    @Override
    public void toMarkdown(StringBuilder build) {
        for (int i = 0; i < number; i++){
            build.append("#");
        }
        build.append(" ");
        super.toMarkdown(build);
    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append("<h").append(number).append(">");
        super.toHtml(build);
        build.append("</h").append(number).append(">");
    }
}
