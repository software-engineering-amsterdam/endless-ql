package org.uva.sea.ql.interpreter.evaluate.visitor;

import org.uva.sea.ql.interpreter.evaluate.valueTypes.BooleanValue;
import org.uva.sea.ql.interpreter.visitor.BaseValueVisitor;

public class LookupBooleanValue extends BaseValueVisitor<Boolean> {
    /**
     * @param boolValue
     * @return
     */
    public Boolean visit(BooleanValue boolValue) {
        return boolValue.getBooleanValue();
    }
}
