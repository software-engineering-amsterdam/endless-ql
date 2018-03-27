package org.uva.sea.languages.qls.interpreter.style;

import javafx.scene.Node;
import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;
import org.uva.sea.languages.ql.interpreter.dataObject.questionData.QLWidget;

import java.util.ArrayList;
import java.util.List;

public class Style {

    private String color = null;
    private String font = null;
    private Integer fontSize = null;
    private Integer width = null;

    private String page = null;
    private List<String> section = null;

    private QLWidget widget = new QLWidget(WidgetType.DEFAULT, new ArrayList<>());

    public void fillNullFields(Style style) {
        if (this.color == null)
            this.color = style.color;

        if (this.font == null)
            this.font = style.font;

        if (this.fontSize == null)
            this.fontSize = style.fontSize;

        if (this.width == null)
            this.width = style.width;

        if (this.page == null)
            this.page = style.page;

        if ((this.section == null) && (style.section != null))
            this.section = new ArrayList<>(style.section);

        if (this.widget.getWidgetType() == WidgetType.DEFAULT)
            this.widget = new QLWidget(style.widget.getWidgetType(), style.widget.getParameters());
    }


    public String getColor() {
        return this.color;
    }

    public String getFont() {
        return this.font;
    }

    public Integer getFontSize() {
        return this.fontSize;
    }

    public Integer getWidth() {
        return this.width;
    }

    private QLWidget getWidget() {
        return this.widget;
    }

    public String getPage() {
        return this.page;
    }

    public List<String> getSection() {
        return new ArrayList<>(this.section);
    }

    @Override
    public String toString() {
        return "Style{" +
                "color='" + this.color + '\'' +
                ", font='" + this.font + '\'' +
                ", fontSize=" + this.fontSize +
                ", width=" + this.width +
                ", page='" + this.page + '\'' +
                ", section=" + this.section +
                ", widget=" + this.widget +
                '}';
    }

    public WidgetType getWidgetType() {
        return this.widget.getWidgetType();
    }

    public void updateNode(Node node) {
        String css = "";

        if (this.fontSize != null)
            css += "-fx-font-size: " + this.fontSize + " pt;";

        if (this.color != null)
            css += "-fx-text-fill: " + this.color + ';';

        if (this.font != null)
            css += "-fx-font-family: " + this.font + ';';

        if (this.width != null)
            css += "-fx-width: " + this.width + ';';

        node.setStyle(css);
    }
}
