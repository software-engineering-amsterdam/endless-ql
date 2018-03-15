package nl.uva.js.qlparser.models.qls;

import lombok.Builder;
import lombok.Data;

import java.util.LinkedList;

@Data
@Builder
public class Stylesheet {
    private String name;
    private LinkedList<Expression> styleExpressions;

    public void checkType() {
//
    }
}
