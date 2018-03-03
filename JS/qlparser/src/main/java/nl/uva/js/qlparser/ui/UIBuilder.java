package nl.uva.js.qlparser.ui;

import nl.uva.js.qlparser.models.expressions.form.Question;

public class UIBuilder {

    public static String getLabeledField(Question question) {
        String id   = question.getName();
        String type = question.getHtmlType();

        return getLabel(id, question.getQuestion())
               + getInput(type, getInputProperties(id, extractValue(question)));
    }

    private static String extractValue(Question question) {
        return  // TODO: Question needs a proper String-returning method for this
                question.getValue() != null
                ? (question.getValue().value() != null
                   ? "A value~"
                   : "")
                : "";
    }

    private static String getLabel(String id, String question) {
        return "<label for=\"" + id + "\">" + question + "</label>";
    }

    private static String getInputProperties(String id, String value) {
        return "name=\"" + id + "\" "
               + "id=\"" + id + "\" "
               + "value=\"" + value + "\"";
    }

    private static String getInput(String type, String properties) {
        return "<input type=\"" + type + "\" " + properties + ">";
    }
}