package org.uva.sea.languages.ql.interpreter.dataObject.questionData;

import java.util.ArrayList;
import java.util.List;

public class WidgetParameters {

    List<String> parameters = new ArrayList<>();

    public WidgetParameters() {
    }

    public WidgetParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public List<String> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "WidgetParameters{" +
                "parameters=" + parameters +
                '}';
    }
}
