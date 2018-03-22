package ql;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import ql.ast.form.Form;
import ql.exceptions.QLException;
import ql.grammar.QLLexer;
import ql.grammar.QLParser;
import ql.helpers.MessageBag;
import ql.listeners.QLErrorListener;
import ql.visitors.TextToAST;

public class QL {

    private String filePath;
    private List<QLException> errors;

    public QL(String filePath) {
        
        this.filePath   = filePath;
        this.errors     = new MessageBag();
    }
    
    public QL(String filePath, List<QLException> errors) {
        
        this.filePath   = filePath;
        this.errors     = errors;
    }
    
    public Form getForm() throws Exception { 
        
        InputStream inputStream         = getClass().getClassLoader().getResourceAsStream(filePath);
        TextToAST visitor          = new TextToAST(errors);
        QLErrorListener errorListener   = new QLErrorListener(errors);
        
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
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
        
        tree    = parser.form();
        form    = (Form) tree.accept(visitor);
        
        return form;
    }
}
