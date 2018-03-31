package nl.uva.js.qlparser.models.qls.style;

import lombok.Builder;
import lombok.Data;
import nl.uva.js.qlparser.models.qls.enums.Property;

import java.util.HashMap;

@Builder
@Data
public class WidgetStyle {
    HashMap<Property, String> styleRules;
}
