package nl.uva.js.qlparser.models.qls.expressions.style;

import lombok.Builder;
import lombok.Data;
import nl.uva.js.qlparser.models.ql.enums.DataType;
import nl.uva.js.qlparser.models.qls.enums.WidgetStyle;

@Builder
@Data
public class DefaultStyle implements StyleExpression {
    private DataType dataType;
    private String widget;
    private WidgetStyle style;
}
