import md2html.Document;
import md2html.Md2Html;

import java.io.IOException;
import java.util.List;

public class Main {
    private static List<String> test = List.of(
     "# Заголовок первого уровня\n\n",
     "## Второго\n\n",
     "### Третьего ## уровня\n\n",
     "#### Четвертого\n# Все еще четвертого\n\n",
     "Этот абзац текста,\nсодержит две строки.",
     "# Может показаться, что это заголовок.\nНо нет, это абзац начинающийся с `#`.\n\n",
     "#И это не заголовок.\n\n",
     "###### Заголовки могут быть многострочными\n(и с пропуском заголовков предыдущих уровней)\n\n",
     "\n\n\nЛишние пустые строки должны игнорироваться.\n\n\n\n",
     "# *Выделение* и `код` в заголовках",
     "Выделение может *начинаться на одной строке,\n а заканчиваться* на другой"
    );
    public static void main(String[] args) throws IOException {
	    Document d = Md2Html.parse(test.get(0));
	    StringBuilder builder = new StringBuilder();
	    d.toHtml(builder);
        System.out.println(builder.toString());
    }
}
