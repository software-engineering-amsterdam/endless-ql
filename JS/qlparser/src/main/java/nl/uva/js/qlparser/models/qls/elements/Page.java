package nl.uva.js.qlparser.models.qls.elements;

import lombok.Builder;
import lombok.Data;
import nl.uva.js.qlparser.models.qls.Expression;

import java.util.LinkedList;

@Builder
@Data
public class Page implements Expression {
    String name;
    LinkedList<Section> sections;
}
