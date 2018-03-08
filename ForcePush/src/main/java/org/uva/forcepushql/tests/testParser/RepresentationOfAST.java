package org.uva.forcepushql.tests.testParser;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.*;

abstract class ParseTreeElement extends ParserRuleContext {

    abstract String multiLineString(String identation );
}

class ParseTreeLeaf extends ParseTreeElement{

    public String text;

    public ParseTreeLeaf(String text){
        this.text = text;
    }

    public String toString(){
        return "T[$text]";
    }

    String multiLineString(String identation) {
        return "${identation}T[$text]\n";
    }
}

class ParseTreeNode extends ParseTreeElement{
    public String name;
    public LinkedList<ParseTreeElement> children = new LinkedList<ParseTreeElement>();

    public ParseTreeNode(String context) {
        name = context;
    }

    public ParseTreeNode child (ParseTreeElement c){
        children.add(c);
        return this;
    }

    @Override
    public String toString(){
        return "Node($name) $children";
    }

    public String multiLineString(final String identation) {
        StringBuilder sb = new StringBuilder();
        sb.append("${identation}$name\n");
        children.forEach(c -> sb.append((c.multiLineString(identation + " "))));

        return sb.toString();
    }

}

public class RepresentationOfAST{

    public RepresentationOfAST(){

    }

    public ParseTreeNode toParseTree (ParserRuleContext node){
        ParseTreeNode res = new ParseTreeNode(node.getClass().getSimpleName());
        res.children.forEach( c -> {
            System.out.println("C IS HERE" + c);
            if(c instanceof ParserRuleContext)
                res.child(toParseTree(c));

            else if (c instanceof TerminalNode)
                res.child(new ParseTreeLeaf(c.getText()));});

        return res;
    }

}
