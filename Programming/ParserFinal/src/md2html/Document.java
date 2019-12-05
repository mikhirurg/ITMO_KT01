package md2html;

import markup.Htmlable;

import java.util.List;

public class Document {

    List<Htmlable> content;

    public Document(List<Htmlable> content) {
        this.content = content;
    }

    public void toHtml(StringBuilder build){
        for (Htmlable e : content){
            e.toHtml(build);
        }
    }

    public void toTex(StringBuilder build){
        build.append("\\documentclass{article}\n");
        build.append("\\usepackage[russian]{babel}\n");
        build.append("\\usepackage{moreverb, amsfonts, amssymb, fancybox, fancyhdr, amsmath, ulem}\n");
        build.append("\\begin{document}");
        for (Htmlable e : content){
            e.toTex(build);
        }
        build.append("\n\\end{document}");
    }



}
