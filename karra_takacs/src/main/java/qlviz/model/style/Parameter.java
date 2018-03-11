package qlviz.model.style;

import java.math.BigDecimal;

public interface VoidParameterVisitor {
    void visit(StringParameter stringParameter);
    void visit(NumericParameter numericParameter);
    void visit(ColorParameter colorParameter);
}

public interface Parameter {
    void accept(VoidParameterVisitor voidParameterVisitor);
}

public class StringParameter implements Parameter {

}

public class NumericParameter implments Parameter {

}

public class ColorParameter implements Parameter {

}
