package qlviz.model.style;

public interface ParameterVisitor {
    void visit(StringParameter stringParameter);
    void visit(NumericParameter numericParameter);
    void visit(ColorParameter colorParameter);
}
