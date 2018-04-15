package GUI;

import QL.AST.Form;
import QL.QLAntlrGen.QLLexer;
import QL.QLAntlrGen.QLParser;
import QL.QLBuilder.FormVisitor;
import QLS.AST.Stylesheet;
import QLS.QLSAntlrGen.QLSLexer;
import QLS.QLSAntlrGen.QLSParser;
import QLS.QLSVisitor.StylesheetVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileInputStream;
import java.io.InputStream;

public class Parser {

    public Form parseInputToForm(String file) throws UnsupportedOperationException{
        try{
            InputStream stream = new FileInputStream(file);
            QLLexer lexer = new QLLexer(CharStreams.fromStream(stream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            QLParser parser = new QLParser(tokens);

            FormVisitor visitor = new FormVisitor();
            return visitor.visit(parser.head());
        } catch(Exception e) {
            System.out.println("Unable to Parse Selected QL File");
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public Stylesheet parseInputToStyleSheet(String file) throws UnsupportedOperationException{
        try{
            InputStream stream = new FileInputStream(file);
            QLSLexer lexer = new QLSLexer(CharStreams.fromStream(stream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            QLSParser parser = new QLSParser(tokens);

            StylesheetVisitor visitor = new StylesheetVisitor();
            return visitor.visit(parser.head());
        } catch(Exception e) {
            System.out.println("Unable to Parse Selected QLS File");
            throw new UnsupportedOperationException(e.getMessage());
        }
    }
}
