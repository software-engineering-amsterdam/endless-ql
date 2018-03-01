package nl.uva.js.qlparser.ui;

import nl.uva.js.qlparser.helpers.NonNullRun;
import nl.uva.js.qlparser.models.expressions.form.Question;

public class UIBuilder {

    public static String getTextInput(Question question) {
        String id = question.getName();
        return
                "  <label for=\"" + id + "\">" + question.getQuestion() + "</label>\n"

                + "<input type=\"text\""
                + " name=\""  + id + "\""
                + " id=\""    + id + "\""
                + " value=\"" + extractValue(question) + "\""
                + "\\>";
    }

    private static String extractValue(Question question) {
        return
                question.getValue() != null
                ? (question.getValue().value() != null
                   ? question.getValue().value().toString()
                   : "")
                : "";
    }
}
