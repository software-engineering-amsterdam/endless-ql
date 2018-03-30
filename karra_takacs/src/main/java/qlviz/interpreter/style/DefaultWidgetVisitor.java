package qlviz.interpreter.style;

import com.google.inject.Inject;
import qlviz.QLSBaseVisitor;
import qlviz.QLSVisitor;
import qlviz.QLSParser;
import qlviz.interpreter.QuestionTypeTranslator;
import qlviz.model.style.DefaultWidgetDeclaration;
import qlviz.model.style.PropertySetting;
import qlviz.model.style.Widget;

import java.util.stream.Collectors;

public class DefaultWidgetVisitor extends QLSBaseVisitor<DefaultWidgetDeclaration> {

    private final QLSVisitor<PropertySetting> propertySettingVisitor;
    private final QLSVisitor<Widget> widgetVisitor;
    private final QuestionTypeTranslator questionTypeTranslator;

    @Inject
    public DefaultWidgetVisitor(QLSVisitor<PropertySetting> propertySettingVisitor, QLSVisitor<Widget> widgetVisitor, QuestionTypeTranslator questionTypeTranslator) {
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
