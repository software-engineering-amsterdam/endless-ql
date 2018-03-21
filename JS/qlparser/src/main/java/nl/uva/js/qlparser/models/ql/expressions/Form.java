package nl.uva.js.qlparser.models.ql.expressions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.helpers.NonNullRun;
import nl.uva.js.qlparser.models.ql.expressions.form.FormExpression;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

@Data
@Builder
public class Form implements Expression, Expression.TypeCheckable, Expression.Visualizable {

    @JsonIgnore @NonNull private String name;
    @JsonProperty("questions") private LinkedList<FormExpression> formExpressions;

    @Override
    @JsonIgnore
    public LinkedList<Component> getComponents() {
        LinkedList<Component> components = new LinkedList<>();

        NonNullRun.consumer(formExpressions, fe -> fe.stream()
                .map(FormExpression::getComponents)
                .forEach(components::addAll));

        return components;
    }

    @Override
    public void checkType() {
        formExpressions.forEach(Expression.TypeCheckable::checkType);
    }

    public String getHumanizedName() {
        return StringUtils.capitalize(
                Arrays.stream(StringUtils.splitByCharacterTypeCamelCase(name))
                        .map(String::toLowerCase)
                        .filter(StringUtils::isNotBlank)
                        .collect(Collectors.joining(" ")));
    }
}
