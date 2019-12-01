import markup.Paragraph;
import markup.Strikeout;
import markup.Text;
import parser.Document;
import markup.Header;
import parser.MD;
import parser.Md2Html;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static List<String> test = List.of(
     "# Заголовок первого уровня\n\n",
     "## Второго\n\n",
     "### Третьего ## уровня\n\n",
     "    # Может показаться, что это заголовок.\nНо нет, это абзац начинающийся с `#`.\n\n",
     "\n\n\nЛишние пустые строки должны игнорироваться.\n\n\n\n",
     "#И это не заголовок.\n\n"
    );
    public static void main(String[] args) throws IOException {
	    Document d = Md2Html.parse(test.get(5));
	    StringBuilder builder = new StringBuilder();
	    d.toHtml(builder);
        System.out.println(builder.toString());
    }
}
