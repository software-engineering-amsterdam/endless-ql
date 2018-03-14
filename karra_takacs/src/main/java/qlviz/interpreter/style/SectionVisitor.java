package qlviz.interpreter.style;

import qlviz.QLSBaseVisitor;
import qlviz.QLSParser;
import qlviz.model.style.DefaultWidgetDeclaration;
import qlviz.model.style.Question;
import qlviz.model.style.Scope;
import qlviz.model.style.Section;

import java.util.stream.Collectors;

public class SectionVisitor extends QLSBaseVisitor<Section> {

    private final QLSBaseVisitor<Question> questionVisitor;
    private final QLSBaseVisitor<DefaultWidgetDeclaration> defaultWidgetVisitor;

    public SectionVisitor(QLSBaseVisitor<Question> questionVisitor, QLSBaseVisitor<DefaultWidgetDeclaration> defaultWidgetVisitor) {
        this.questionVisitor = questionVisitor;
        this.defaultWidgetVisitor = defaultWidgetVisitor;
    }

    @Override
    public Section visitSection(QLSParser.SectionContext ctx) {
        Section section = new Section(
               ctx.STRING().getText(),
               ctx.question()
                    .stream()
                    .map(questionVisitor::visitQuestion)
                    .collect(Collectors.toList()),
               ctx.defaultWidgetDeclaration()
                    .stream()
                    .map(defaultWidgetVisitor::visitDefaultWidgetDeclaration)
                    .collect(Collectors.toList()),
                ctx.section()
                    .stream()
                    .map(this::visitSection)
                    .collect(Collectors.toList()),
                ctx
        );
        for (Section child : section.getSections()) {
            child.setParent(section);
        }
        return section;
    }
}
