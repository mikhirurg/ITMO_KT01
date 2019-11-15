package markup;

public class Text implements Element{
    private String text;
    Text(String text){
        this.text = text;
    }
    public String getText(){
        return text;
    }

    @Override
    public void toMarkdown(StringBuilder build) {
        build.append(text);
    }

    @Override
    public void toHtml(StringBuilder build) {
        build.append(text);
    }

    @Override
    public void toTex(StringBuilder build) {
        build.append(text);
    }
}
