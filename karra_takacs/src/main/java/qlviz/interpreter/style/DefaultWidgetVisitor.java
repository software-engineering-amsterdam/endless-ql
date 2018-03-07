package qlviz.interpreter.style;

import qlviz.QLSBaseVisitor;
import qlviz.QLSParser;
import qlviz.model.style.DefaultWidgetDeclaration;

public class DefaultWidgetVisitor extends QLSBaseVisitor<DefaultWidgetDeclaration> {

    @Override
    public DefaultWidgetDeclaration visitDefaultWidgetDeclaration(QLSParser.DefaultWidgetDeclarationContext ctx) {
        return super.visitDefaultWidgetDeclaration(ctx);
    }
}
