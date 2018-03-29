package nl.uva.js.qlparser.models.ql.expressions.form;

import nl.uva.js.qlparser.models.ql.enums.DataType;
import nl.uva.js.qlparser.models.ql.expressions.Expression;
import nl.uva.js.qlparser.models.ql.expressions.data.Variable;

public interface FormExpression
        extends Expression, Expression.TypeCheckable, Expression.Visualizable, Expression.JsonSerializable {

    String getName();

    default DataType getType() {
        return null;
    }

    default Variable getVariable(){
        return null;
    }
}
