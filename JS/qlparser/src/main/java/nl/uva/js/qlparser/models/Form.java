package nl.uva.js.qlparser.models;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.formexpressions.FormExpression;

import java.util.LinkedList;

@Data
@Builder
public class Form {
    @NonNull private String name;
    private LinkedList<FormExpression> formExpressions;
}
