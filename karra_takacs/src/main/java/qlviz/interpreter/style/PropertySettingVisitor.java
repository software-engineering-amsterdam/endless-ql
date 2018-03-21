package qlviz.interpreter.style;

import com.google.inject.Inject;
import qlviz.QLSBaseVisitor;
import qlviz.QLSParser;
import qlviz.model.style.Parameter;
import qlviz.model.style.PropertySetting;

public class PropertySettingVisitor extends QLSBaseVisitor<PropertySetting> {

    private final QLSBaseVisitor<Parameter> parameterVisitor;

    @Inject
    public PropertySettingVisitor(QLSBaseVisitor<Parameter> parameterVisitor) {
        this.parameterVisitor = parameterVisitor;
    }

    @Override
    public PropertySetting visitPropertySetting(QLSParser.PropertySettingContext ctx) {
        return new PropertySetting(
                ctx.PROPERTY_KEY().getText(),
                this.parameterVisitor.visitParameter(ctx.parameter())
        );
    }
}
