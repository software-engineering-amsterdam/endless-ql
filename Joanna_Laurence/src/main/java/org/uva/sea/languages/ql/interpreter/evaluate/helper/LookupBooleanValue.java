package org.uva.sea.languages.ql.interpreter.evaluate.helper;

import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.BooleanValue;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public class LookupBooleanValue extends BaseValueVisitor<Boolean> {
    public final Boolean visit(final BooleanValue boolValue) {
        return boolValue.getBooleanValue();
    }
}
