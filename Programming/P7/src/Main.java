import markup.ListItem;
import markup.Paragraph;
import markup.Text;
import markup.UnorderedList;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UnorderedList list = new UnorderedList(
                List.of(
                        new ListItem(List.of(new Text("A"))),
                        new ListItem(List.of(new Text("B"))),
                        new ListItem(List.of(new Text("C")))
                )
        );
        StringBuilder builder = new StringBuilder();
        list.toHtml(builder);
        System.out.println(builder.toString());
    }
}
