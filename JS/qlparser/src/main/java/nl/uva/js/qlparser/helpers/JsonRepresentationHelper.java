package nl.uva.js.qlparser.helpers;

import nl.uva.js.qlparser.models.ql.expressions.form.FormExpression;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class JsonRepresentationHelper {
    public static List<Object> visualInformation(List<FormExpression> expressions) {
        return expressions.stream()
                .map(FormExpression::getJsonRepresentation)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
