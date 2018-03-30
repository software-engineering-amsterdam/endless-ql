package com.chariotit.uva.sc.qdsl.ast.ql.node;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;

public abstract class BlockElement extends FormElement {

    BlockElement(SourceFilePosition filePosition) {
        super(filePosition);
    }
}
