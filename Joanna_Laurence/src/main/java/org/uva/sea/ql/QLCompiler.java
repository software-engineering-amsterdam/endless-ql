package org.uva.sea.ql;

import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.ql.parser.antlr.QLParser;;
import org.uva.sea.ql.parser.elements.Form;
import org.uva.sea.ql.typeCheck.QLTypeCheck;

public class QLCompiler {

    /**
     * Compile a form specification
     *
     * @param source Of the source location
     * @return
     */
    public Form compileScriptFile(CharStream source) {

        //Get tokens
        QLLexer lexer = new QLLexer(source);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        //Parse the tree
        QLParser parser = new QLParser(tokens);

        //Check the parsing result
        QLParser.FormContext form = parser.form();
        if(form.result == null)
            return null;

        //TODO: link variable information

        //Do the type check
        QLTypeCheck checker = new QLTypeCheck();
        if(!checker.doTypeCheck(form.result)) {
            return null;
        }

        //Show the parse tree
        Trees.inspect(form, parser);

        return form.result;
    }


}



