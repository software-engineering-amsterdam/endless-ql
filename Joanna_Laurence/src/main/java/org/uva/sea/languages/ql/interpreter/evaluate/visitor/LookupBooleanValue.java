package org.uva.sea.languages.ql.interpreter.evaluate.visitor;

import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.BooleanValue;
import org.uva.sea.languages.ql.parser.visitor.BaseValueVisitor;

public class LookupBooleanValue extends BaseValueVisitor<Boolean> {
    /**
     * @param boolValue
     * @return
     */
    public Boolean visit(BooleanValue boolValue) {
        return boolValue.getBooleanValue();
    }
}
