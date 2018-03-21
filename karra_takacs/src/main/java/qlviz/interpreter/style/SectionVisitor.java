package qlviz.interpreter.style;

import com.google.inject.Inject;
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

    @Inject
    public SectionVisitor(QLSBaseVisitor<Question> questionVisitor, QLSBaseVisitor<DefaultWidgetDeclaration> defaultWidgetVisitor) {
        this.questionVisitor = questionVisitor;
        this.defaultWidgetVisitor = defaultWidgetVisitor;
    }

    @Override
    public Section visitSection(QLSParser.SectionContext ctx) {
        String header = ctx.STRING().getText() ;
        header = header.substring(1, header.length()-1);
        Section section = new Section(
               header,
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
