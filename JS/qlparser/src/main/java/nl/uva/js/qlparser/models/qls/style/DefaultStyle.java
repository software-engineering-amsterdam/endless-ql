package nl.uva.js.qlparser.models.qls.style;

import lombok.Builder;
import lombok.Data;
import nl.uva.js.qlparser.models.ql.enums.DataType;
import nl.uva.js.qlparser.models.qls.Expression;

@Builder
@Data
public class DefaultStyle implements Expression {
    private DataType dataType;
    private String widget;
    private WidgetStyle style;
}
