package QLS.parsing.visitors;
import QL.classes.Question;
import QLS.classes.Page;
import QLS.classes.blocks.Block;
import QLS.classes.blocks.Element;
import QLS.classes.blocks.Section;
import QLS.classes.blocks.StyledQuestion;
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
    private final LinkedHashMap<String, StyledQuestion> styledQuestions;
    private LinkedHashMap<String, Question> questions;
    private final LinkedHashMap<String, Element> parents;
    private String currentParentId;

    public BlockVisitor(LinkedHashMap<String, Question> questions) {
        this.widgetVisitor = new WidgetVisitor();
        this.parents = new LinkedHashMap<>();
        this.sections = new LinkedHashMap<>();
        this.styledQuestions = new LinkedHashMap<>();
        this.questions = questions;
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
        currentParentId = id;
        for (QLSParser.ElementContext c : ctx.element()) {
            elements.add(this.visitElement(c));
        }
        Section section = new Section(id, elements);
        sections.put(id, section);
        return section;
    }

    @Override
    public StyledQuestion visitQuestion(QLSParser.QuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();

        WidgetType widget = null;
        if(ctx.widget() != null){
            widget = widgetVisitor.visitWidget(ctx.widget());
        }

        StyledQuestion question = new StyledQuestion(id, widget, questions.get(id), currentParentId);
        styledQuestions.put(id, question);
        return question;
    }

    public LinkedHashMap<String,Section> getSections() {
        return sections;
    }

    public LinkedHashMap<String,StyledQuestion> getQuestions() {
        return styledQuestions;
    }

    public LinkedHashMap<String,Element> getParents() {
        return parents;
    }
}