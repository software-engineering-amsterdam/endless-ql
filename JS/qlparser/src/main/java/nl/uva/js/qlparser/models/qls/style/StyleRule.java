package nl.uva.js.qlparser.models.qls.style;

import lombok.Builder;
import lombok.Data;
import nl.uva.js.qlparser.models.qls.enums.Property;

@Builder
@Data
public class StyleRule {
    private Property property;
    private String value;
}
