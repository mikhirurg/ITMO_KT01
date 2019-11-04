package markup;

import java.util.List;

abstract class AbstractListItem implements Element{
    List<Element> content;
    AbstractListItem(List<Element> content){
        this.content = content;
    }
}
