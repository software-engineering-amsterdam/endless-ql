package nl.uva.js.qlparser.models.qls.style;

import lombok.Builder;
import lombok.Data;
import nl.uva.js.qlparser.models.ql.enums.DataType;
import nl.uva.js.qlparser.models.qls.Expression;
import nl.uva.js.qlparser.models.qls.enums.WidgetType;

@Builder
@Data
public class DefaultStyle implements Expression {
    private DataType dataType;
    private WidgetType widgetType;
    private WidgetStyle style;
}
