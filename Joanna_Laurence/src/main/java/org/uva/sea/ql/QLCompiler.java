package org.uva.sea.ql;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.uva.sea.ql.parser.antlr.QLBaseListener;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.ql.parser.antlr.QLParser;
import org.uva.sea.ql.parser.elements.Condition;
import org.uva.sea.ql.parser.elements.Question;
import org.uva.sea.ql.parser.elements.Statement;
import org.uva.sea.ql.typeCheck.QLTypeCheck;

public class QLCompiler {

    /**
     * Compile a form specification
     *
     * @param source Of the source location
     * @return
     */
    public Object compileScriptFile(CharStream source) {

        //Get tokens
        QLLexer lexer = new QLLexer(source);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        //Parse the tree
        QLParser parser = new QLParser(tokens);

        //parser.addErrorListener(new ErrorListener());
        QLParser.FormContext form = parser.form();

        //TODO: link variable information

        //Do the type check
        QLTypeCheck checker = new QLTypeCheck();
        checker.doTypeCheck(form.result);

        //Show the parse tree
        Trees.inspect(form, parser);

        return null;
    }


}



