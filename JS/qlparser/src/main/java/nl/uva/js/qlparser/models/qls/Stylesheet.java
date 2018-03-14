package nl.uva.js.qlparser.models.qls;

import lombok.Builder;
import lombok.Data;
import nl.uva.js.qlparser.models.qls.expressions.style.StyleExpression;

import java.util.LinkedList;

@Data
@Builder
public class Stylesheet {
    private String name;
    private LinkedList<StyleExpression> styleExpressions;

    public void checkType() {
//
    }
}
