package nl.uva.js.qlparser.models.qls.elements;

import lombok.Builder;
import lombok.Data;
import nl.uva.js.qlparser.models.qls.style.WidgetStyle;
import nl.uva.js.qlparser.models.qls.enums.WidgetType;

@Builder
@Data
public class QuestionReference {
    String name;
    WidgetStyle widgetStyle;
    WidgetType widgetType;

}
