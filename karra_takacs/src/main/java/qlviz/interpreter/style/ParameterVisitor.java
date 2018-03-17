package qlviz.interpreter.style;

import qlviz.QLSBaseVisitor;
import qlviz.QLSParser;
import qlviz.model.style.ColorParameter;
import qlviz.model.style.NumericParameter;
import qlviz.model.style.Parameter;
import qlviz.model.style.StringParameter;

import java.math.BigDecimal;

public class ParameterVisitor extends QLSBaseVisitor<Parameter> {

    @Override
    public Parameter visitParameter(QLSParser.ParameterContext ctx) {
        if (ctx.STRING() != null) {
            return new StringParameter(ctx.STRING().getText());
        }
        else if (ctx.COLOR() != null) {
            return new ColorParameter(ctx.COLOR().getText());
        }
        else if (ctx.NUMBER() != null) {
            return new NumericParameter(new BigDecimal(ctx.NUMBER().getText()));
        }
        throw new IllegalArgumentException("ParameterContext has no parameter.");
    }
}
