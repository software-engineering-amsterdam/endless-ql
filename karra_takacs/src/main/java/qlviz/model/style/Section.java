package qlviz.model.style;

import java.util.List;

public class Section {

    private final String name;
    private final List<Question> questions;
    private final List<DefaultWidgetDeclaration> defaultWidgetDeclarations;

    public Section(String name, List<Question> questions, List<DefaultWidgetDeclaration> defaultWidgetDeclarations) {
        this.name = name;
        this.questions = questions;
        this.defaultWidgetDeclarations = defaultWidgetDeclarations;
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
