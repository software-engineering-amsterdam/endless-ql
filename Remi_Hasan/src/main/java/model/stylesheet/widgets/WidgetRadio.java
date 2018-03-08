package model.stylesheet.widgets;

import java.util.List;

public class WidgetRadio extends Widget {

    private final List<String> options;

    public WidgetRadio(List<String> options) {
        this.options = options;
    }
}
