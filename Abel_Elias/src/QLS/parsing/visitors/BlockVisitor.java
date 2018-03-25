package QLS.parsing.visitors;
import QLS.classes.Page;
import QLS.classes.blocks.Block;
import QLS.classes.blocks.Element;
import QLS.classes.blocks.Question;
import QLS.classes.blocks.Section;
import QLS.classes.widgets.CheckBoxWidget;
import QLS.classes.widgets.DropdownWidget;
import QLS.classes.widgets.RadioWidget;
import QLS.classes.widgets.SliderWidget;
import QLS.classes.widgets.SpinBoxWidget;
import QLS.classes.widgets.TextWidget;
import QLS.classes.widgets.Widget;
import QLS.classes.widgets.WidgetType;
import QLS.parsing.gen.QLSBaseVisitor;
import QLS.parsing.gen.QLSParser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BlockVisitor extends QLSBaseVisitor {

    private WidgetVisitor widgetVisitor;
    private final LinkedHashMap<String, Section> sections;
    private final LinkedHashMap<String, Question> questions;
    private final LinkedHashMap<String, Element> parents;

    public BlockVisitor() {
        this.widgetVisitor = new WidgetVisitor();
        this.parents = new LinkedHashMap<>();
        this.sections = new LinkedHashMap<>();
        this.questions = new LinkedHashMap<>();
    }

    @Override
    public Element visitElement(QLSParser.ElementContext ctx) {
        if (ctx.section() != null) {
            return visitSection(ctx.section());
        } else if (ctx.question() != null) {
            return visitQuestion(ctx.question());
        } else {
            return null;
        }
    }

    @Override
    public Section visitSection(QLSParser.SectionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        List<Element> elements = new ArrayList<>();
        for (QLSParser.ElementContext c : ctx.element()) {
            elements.add(this.visitElement(c));
        }
        Section section = new Section(id, elements);
        sections.put(id, section);
        return section;
    }

    @Override
    public Question visitQuestion(QLSParser.QuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        Widget widget = widgetVisitor.visitWidget(ctx.widget());
        Question question = new Question(id, widget);
        questions.put(id, question);
        return question;
    }

    public LinkedHashMap<String,Section> getSections() {
        return sections;
    }

    public LinkedHashMap<String,Question> getQuestions() {
        return questions;
    }

    public LinkedHashMap<String,Element> getParents() {
        return parents;
    }
}