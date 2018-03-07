package qlviz.model.style;

import org.antlr.v4.runtime.ParserRuleContext;
import qlviz.model.Node;

import java.util.List;

public class Section extends Node {

    private final String name;
    private final List<Question> questions;
    private final List<DefaultWidgetDeclaration> defaultWidgetDeclarations;
    private final List<Section> sections;

    public Section(String name,
                   List<Question> questions,
                   List<DefaultWidgetDeclaration> defaultWidgetDeclarations,
                   List<Section> sections, ParserRuleContext context) {
        super(context);
        this.name = name;
        this.questions = questions;
        this.defaultWidgetDeclarations = defaultWidgetDeclarations;
        this.sections = sections;
    }

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<DefaultWidgetDeclaration> getDefaultWidgetDeclarations() {
        return defaultWidgetDeclarations;
    }
}
