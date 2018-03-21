package tool;


import antlr.ql.FormLexer;
import antlr.ql.FormParser;
import antlr.qls.StylesheetLexer;
import antlr.qls.StylesheetParser;
import domain.model.stylesheet.Page;
import domain.model.stylesheet.Section;
import domain.model.variable.Variable;
import loader.QL.QLLoader;
import loader.QLS.QLSLoader;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(args[0]));
            FormLexer lexer = new FormLexer(input);
            FormParser parser = new FormParser(new CommonTokenStream(lexer));
            FormParser.FormBuilderContext tree = parser.formBuilder();
            QLLoader loader = new QLLoader();
            ParseTreeWalker.DEFAULT.walk(loader, tree);

            ANTLRInputStream sinput = new ANTLRInputStream(new FileInputStream(args[1]));
            StylesheetLexer slexer = new StylesheetLexer(sinput);
            StylesheetParser sparser = new StylesheetParser(new CommonTokenStream(slexer));
            StylesheetParser.StylesheetBuilderContext stree = sparser.stylesheetBuilder();
            QLSLoader qlsLoader = new QLSLoader(loader.getFormNode());
            ParseTreeWalker.DEFAULT.walk(qlsLoader, stree);

            for (Page p : qlsLoader.getStyleSheet().getPages()){
                for (Section s : p.getSections()){
                    for (Variable v : s.getVariables()){
                        System.out.println(v.getUiElement());
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}