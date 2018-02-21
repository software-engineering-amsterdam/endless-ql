package nl.uva.js.qlparser.models.enums;

import java.util.List;

public interface Operator {
    List<DataType> requiredType();
}
