package nl.uva.js.qlparser.models.qls.style;

import lombok.Builder;
import lombok.Data;

import java.util.LinkedList;

@Builder
@Data
public class WidgetStyle {
    LinkedList<StyleRule> styleRules;
}
