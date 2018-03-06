package org.uva.sea.ql.evaluate.visitor;

import org.uva.sea.ql.evaluate.valueTypes.BooleanValue;
import org.uva.sea.ql.visitor.BaseValueVisitor;

public class LookupBooleanValue extends BaseValueVisitor<Boolean> {
    /**
     * @param boolValue
     * @return
     */
    public Boolean visit(BooleanValue boolValue) {
        return boolValue.getBooleanValue();
    }
}
