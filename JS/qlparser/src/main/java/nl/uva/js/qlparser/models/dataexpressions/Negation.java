package nl.uva.js.qlparser.models.dataexpressions;

import lombok.Builder;
import lombok.Data;
import nl.uva.js.qlparser.models.Expression;

@Data
@Builder
public class Negation implements DataExpression {
    private Expression expression;

    @Override
    public void toRepresentation() {

    }
}
