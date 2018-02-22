package nl.uva.js.qlparser.models;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.models.formexpressions.FormExpression;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.LinkedList;

@Data
@Builder
public class Form implements Expression, Expression.TypeCheckable {
    @NonNull private String name;
    private LinkedList<FormExpression> formExpressions;

    @Override
    public ArrayList<Component> getComponents() {
        ArrayList<Component> components = new ArrayList<>();

        Label formTitle = new Label(
                "<h1>" + HtmlUtils.htmlEscape(name) + "</h1>",
                ContentMode.HTML
        );

        components.add(formTitle);

        for (FormExpression formExpression : formExpressions) {
            components.addAll(formExpression.getComponents());
        }

        return components;
    }

    @Override
    public void checkType() {
        formExpressions.forEach(Expression.TypeCheckable::checkType);
    }
}
