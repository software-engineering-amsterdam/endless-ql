package nl.uva.js.qlparser.models.ql.expressions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import nl.uva.js.qlparser.helpers.JsonRepresentationHelper;
import nl.uva.js.qlparser.models.ql.expressions.form.FormExpression;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Form implements FormExpression {

    @JsonIgnore @NonNull private String name;
    @JsonIgnore @NonNull private LinkedList<FormExpression> formExpressions;
    @JsonIgnore @Getter(lazy = true) private final Map<String, FormExpression> expressionsByName
            = formExpressions.stream().collect(Collectors.toMap(FormExpression::getName, x -> x));

    @Override
    @JsonIgnore
    public LinkedList<Component> getComponents() {
        return formExpressions.stream()
                .map(FormExpression::getComponents)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public void checkType() {
        formExpressions.forEach(Expression.TypeCheckable::checkType);
    }

    @JsonProperty("name")
    public String getHumanizedName() {
        return StringUtils.capitalize(
                Arrays.stream(StringUtils.splitByCharacterTypeCamelCase(name))
                        .map(String::toLowerCase)
                        .filter(StringUtils::isNotBlank)
                        .collect(Collectors.joining(" ")));
    }

    @JsonProperty("expressionReferences")
    public List<Object> getJsonRepresentation() {
        return JsonRepresentationHelper.visualInformation(formExpressions);
    }
}
