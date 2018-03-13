package qlviz.interpreter.style;

import qlviz.QLSBaseVisitor;
import qlviz.QLSParser;
import qlviz.interpreter.QuestionTypeTranslator;
import qlviz.model.style.DefaultWidgetDeclaration;
import qlviz.model.style.PropertySetting;
import qlviz.model.style.Widget;

import java.util.stream.Collectors;

public class DefaultWidgetVisitor extends QLSBaseVisitor<DefaultWidgetDeclaration> {

    private final QLSBaseVisitor<PropertySetting> propertySettingVisitor;
    private final QLSBaseVisitor<Widget> widgetVisitor;
    private final QuestionTypeTranslator questionTypeTranslator;

    public DefaultWidgetVisitor(QLSBaseVisitor<PropertySetting> propertySettingVisitor, QLSBaseVisitor<Widget> widgetVisitor, QuestionTypeTranslator questionTypeTranslator) {
        this.propertySettingVisitor = propertySettingVisitor;
        this.widgetVisitor = widgetVisitor;
        this.questionTypeTranslator = questionTypeTranslator;
    }

    @Override
    public DefaultWidgetDeclaration visitDefaultWidgetDeclaration(QLSParser.DefaultWidgetDeclarationContext ctx) {
        return new DefaultWidgetDeclaration(
               ctx.propertySetting()
                    .stream()
                    .map(propertySettingVisitor::visitPropertySetting)
                    .collect(Collectors.toList()),
                widgetVisitor.visitWidget(ctx.widget()),
                questionTypeTranslator.translate(ctx.QUESTION_TYPE())
        );
    }
}
