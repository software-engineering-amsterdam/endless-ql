package org.uva.sea.evaluate.visitor;

import org.uva.sea.evaluate.valueTypes.BooleanValue;
import org.uva.sea.visitor.BaseValueVisitor;

public class LookupBooleanValue extends BaseValueVisitor<Boolean> {
    /**
     * @param boolValue
     * @return
     */
    public Boolean visit(BooleanValue boolValue) {
        return boolValue.getBooleanValue();
    }
}
