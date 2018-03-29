package org.uva.sea.languages.qls.interpreter.widget;

import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class Style {

    private String color = null;
    private String font = null;
    private Integer fontSize = null;
    private Integer width = null;

    private String page = null;
    private List<String> section = null;

    private QLSWidget widget = new QLSWidget(WidgetType.DEFAULT, new ArrayList<>());

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
            this.widget = new QLSWidget(style.widget.getWidgetType(), style.widget.getParameters());
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

    private QLSWidget getWidget() {
        return this.widget;
    }

    public void setWidget(QLSWidget widget) {
        this.widget = widget;
    }

    public String getPage() {
        return this.page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<String> getSection() {
        return new ArrayList<>(this.section);
    }

    public void setSection(List<String> section) {
        this.section = section;
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

    public void setColor(String color) {
        this.color = color;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public void setWidth(Integer width) {
        this.width = width;
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

    public void setWidgetType(WidgetType widgetType) {
        this.widget = new QLSWidget(widgetType, new ArrayList<>());
    }
}
