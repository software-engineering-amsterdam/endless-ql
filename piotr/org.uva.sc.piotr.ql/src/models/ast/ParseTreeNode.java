package models.ast;

import javax.json.Json;
import java.util.ArrayList;

abstract public class ParseTreeNode {

    public abstract Json toJson();

    private ArrayList<ParseTreeNode> children;

    private ParseTreeNode parent;

    public void addChild(ParseTreeNode node) {
        this.children.add(node);
    }

    public ArrayList<ParseTreeNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<ParseTreeNode> children) {
        this.children = children;
    }

    public ParseTreeNode getParent() {
        return parent;
    }

    public void setParent(ParseTreeNode parent) {
        this.parent = parent;
    }
}
