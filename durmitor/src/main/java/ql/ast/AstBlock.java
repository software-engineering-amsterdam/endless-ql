package ql.ast;

import java.util.ArrayList;

public class AstBlock extends AstNode {

    private ArrayList<Object> store = new ArrayList<Object>(0);

    AstBlock(Ast ast) {
        super(ast);
    }

    @Override
    int getNodeType0() {
        return AstNode.BLOCK;
    }

    public void addChild(AstNode node) {
        this.store.add(node);
    }

    public int size() {
        return this.store.size();
    }

    public AstNode get(int index) {
        return (AstNode) this.store.get(index);
    }
}
