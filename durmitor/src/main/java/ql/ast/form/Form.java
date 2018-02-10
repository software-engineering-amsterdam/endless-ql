package ql.ast.form;

import ql.ast.Identifier;
import ql.ast.statement.Block;

public class Form {
    
    private Identifier identifier;
    private Block block;
    
    public Form(Identifier identifier, Block block) {
        this.identifier = identifier;
        this.block = block;
    }

    }
