package org.uva.sea.languages.ql.interpreter.dataObject.questionData;

import org.uva.sea.languages.ql.interpreter.dataObject.WidgetType;

import java.util.ArrayList;
import java.util.List;

public class Style {

    private String color;
    private String font;
    private Integer fontSize;
    private Integer width;

    private String page;
    private List<String> section;

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

    public void setColor(String color) {
        this.color = color;
    }

    public String getFont() {
        return this.font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public Integer getFontSize() {
        return this.fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Integer getWidth() {
        return this.width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public QLWidget getWidget() {
        return this.widget;
    }

    public void setWidget(QLWidget widget) {
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

    public void setSection(List<String> sections) {
        this.section = new ArrayList<>(sections);
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

    public void setWidgetType(WidgetType widgetType) {
        this.widget = new QLWidget(widgetType, new ArrayList<>());
    }
}
