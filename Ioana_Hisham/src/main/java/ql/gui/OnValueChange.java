package ql.gui;

import ql.ast.expressions.Identifier;
import ql.values.Value;

public interface OnValueChange {
    void changed(Identifier identifier, Value value);
}
