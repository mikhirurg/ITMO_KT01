package markup;

import java.util.List;

abstract class AbstractList implements ListItemElement {
    private List<ListItem> content;

    AbstractList(List<ListItem> content) {
        this.content = content;
    }

    @Override
    public void toHtml(StringBuilder build) {
        for (ListItem e : content) {
            e.toHtml(build);
        }
    }

    @Override
    public void toTex(StringBuilder build) {
        for (ListItem e : content) {
            e.toTex(build);
        }
    }
}
