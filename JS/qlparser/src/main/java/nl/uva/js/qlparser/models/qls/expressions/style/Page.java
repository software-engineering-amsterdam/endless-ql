package nl.uva.js.qlparser.models.qls.expressions.style;

import lombok.Builder;
import lombok.Data;
import nl.uva.js.qlparser.models.qls.elements.Section;

import java.util.LinkedList;

@Builder
@Data
public class Page implements StyleExpression {
    String name;
    LinkedList<Section> sections;
}
