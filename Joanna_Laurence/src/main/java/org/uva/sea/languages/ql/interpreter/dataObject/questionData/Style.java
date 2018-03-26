package org.uva.sea.languages.ql.interpreter.dataObject.questionData;

import javafx.scene.Node;
import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;

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

    public final void fillNullFields(final Style style) {
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


    public final String getColor() {
        return this.color;
    }

    public final void setColor(final String color) {
        this.color = color;
    }

    public final String getFont() {
        return this.font;
    }

    public final void setFont(final String font) {
        this.font = font;
    }

    public final Integer getFontSize() {
        return this.fontSize;
    }

    public final void setFontSize(final Integer fontSize) {
        this.fontSize = fontSize;
    }

    public final Integer getWidth() {
        return this.width;
    }

    public final void setWidth(final Integer width) {
        this.width = width;
    }

    private QLWidget getWidget() {
        return this.widget;
    }

    public final void setWidget(final QLWidget widget) {
        this.widget = widget;
    }

    public final String getPage() {
        return this.page;
    }

    public final void setPage(final String page) {
        this.page = page;
    }

    public final List<String> getSection() {
        return new ArrayList<>(this.section);
    }

    public final void setSection(final List<String> sections) {
        this.section = new ArrayList<>(sections);
    }

    @Override
    public final String toString() {
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

    public final WidgetType getWidgetType() {
        return this.widget.getWidgetType();
    }

    public final void setWidgetType(final WidgetType widgetType) {
        this.widget = new QLWidget(widgetType, new ArrayList<>());
    }

    public final void updateNode(final Node node) {
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
