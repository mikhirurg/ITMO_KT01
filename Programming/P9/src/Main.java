import markup.Paragraph;
import markup.Strikeout;
import markup.Text;
import parser.Document;
import markup.Header;
import parser.MD;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
	    //Document d = MD.parse("# abcd\n\n# abcd\n\n# abcd\n\n### abcd\n\n## abcd\n\ndd*dd*d");
	   // Document d = MD.parse("dd*dd*d");
	    //Document d = MD.parse("dd*d* d *d* d");
	    Document d = MD.parse("");
	    StringBuilder builder = new StringBuilder();
	    d.toHtml(builder);
        System.out.println(builder.toString());
    }
}
