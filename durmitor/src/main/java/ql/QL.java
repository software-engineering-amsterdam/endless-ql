package ql;

import java.io.InputStream;
import java.nio.charset.Charset;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import ql.ast.form.Form;
import ql.grammar.QLLexer;
import ql.grammar.QLParser;
import ql.visitors.QLVisitorToAst;

public class QL {

    // private static final Logger logger = LogManager.getLogger(QL.class);
    private String filePath;

    public QL(String filePath) {
        // logger.info("Invoke constructor with: {}", filePath);
        this.filePath = filePath;
    }

    public Form getForm() throws Exception { 
        
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
        QLVisitorToAst visitor  = new QLVisitorToAst();
        
        CharStream charStream;
        QLParser parser;
        ParseTree tree;
        Form form;

        if (inputStream == null) {
            charStream = CharStreams.fromFileName(filePath, Charset.forName("UTF-8"));
        } else {
            charStream = CharStreams.fromStream(inputStream, Charset.forName("UTF-8"));
        }

        parser  = new QLParser(new CommonTokenStream(new QLLexer(charStream)));
        tree    = parser.form();
        form    = (Form) visitor.visit(tree);
        System.out.println(form.toString());
        return form;
    }
}
