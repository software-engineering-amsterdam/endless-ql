package nl.uva.js.qlparser.models.dataexpressions;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.Expression;
import nl.uva.js.qlparser.models.enums.Operator;

@Data
@Builder
public class Combinator implements DataExpression {
    @NonNull private Operator operator;
    @NonNull private Expression left;
    @NonNull private Expression right;

    @Override
    public void toRepresentation() {

    }
}
