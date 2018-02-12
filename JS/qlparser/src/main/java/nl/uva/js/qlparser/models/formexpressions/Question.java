package nl.uva.js.qlparser.models.formexpressions;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.dataexpressions.DataExpression;

@Data
@Builder
public class Question implements FormExpression {
    @NonNull private String name;
    @NonNull private String question;
    private DataExpression value;

    @Override
    public void toRepresentation() {

    }
}
