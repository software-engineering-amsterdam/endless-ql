package nl.uva.js.qlparser.models.formexpressions;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.dataexpressions.DataExpression;
import nl.uva.js.qlparser.models.enums.DataType;

@Data
@Builder
public class Question implements FormExpression {
    @NonNull private String name;
    @NonNull private String question;
    @NonNull private DataType dataType;
    private DataExpression value;

    @Override
    public void toRepresentation() {

    }
}
