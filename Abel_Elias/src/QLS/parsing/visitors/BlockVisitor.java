package QLS.parsing.visitors;
import QL.classes.Question;
import QL.classes.values.Value;
import QLS.classes.blocks.Element;
import QLS.classes.blocks.Section;
import QLS.classes.blocks.StyledQuestion;
import QLS.classes.properties.Property;
import QLS.parsing.gen.QLSBaseVisitor;
import QLS.parsing.gen.QLSParser;
import gui.widgets.Widget;
import gui.widgets.WidgetFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BlockVisitor extends QLSBaseVisitor {

    private final LinkedHashMap<String, Section> sections;
    private final LinkedHashMap<String, StyledQuestion> styledQuestions;
    private LinkedHashMap<String, Question> questionMap;
    private final LinkedHashMap<String, Element> parents;
    private String currentParentId;
    private WidgetVisitor widgetVisitor;
    private PropertyVisitor propertyVisitor;

    public BlockVisitor(LinkedHashMap<String, Question> questionMap) {
        this.parents = new LinkedHashMap<>();
        this.sections = new LinkedHashMap<>();
        this.styledQuestions = new LinkedHashMap<>();
        this.widgetVisitor = new WidgetVisitor();
        this.propertyVisitor = new PropertyVisitor();
        this.questionMap = questionMap;
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
        String id = ctx.STR().getText();
        List<Element> elements = new ArrayList<>();
        currentParentId = id;
        for (QLSParser.ElementContext c : ctx.element()) {
            elements.add(this.visitElement(c));
        }
        Section section = new Section(id, elements);
        sections.put(id, section);
        return section;
    }


    //TODO: Write code for visitQuestion()
    @Override
    public StyledQuestion visitQuestion(QLSParser.QuestionContext ctx) {
        String id = ctx.IDENTIFIER().getText();
        Question question = questionMap.get(id);

        Widget widget = null;
        if(ctx.widget() != null){
            widget = widgetVisitor.visitWidget(ctx.widget(), question.getValue());
        }else{
            widget = WidgetFactory.getDefaultWidget(question.getValue());
        }

        List<Property> properties = new ArrayList<>();
        if(ctx.style() != null) {
            for(QLSParser.WidgetPropertyContext c: ctx.style().widgetProperty()) {
                properties.add((Property) propertyVisitor.visitWidgetProperty(c));
            }
        }

        StyledQuestion styledQuestion = new StyledQuestion(id, question, currentParentId, widget, properties);
        this.styledQuestions.put(id, styledQuestion);

        return styledQuestion;
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