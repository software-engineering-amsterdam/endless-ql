package org.uva.sea.languages.ql.interpreter.dataObject.questionData;

import java.util.ArrayList;
import java.util.List;

public class Style {

    private String color = null;
    private String font = null;
    private Integer fontSize = null;
    private Integer width = null;

    private String page = null;
    private List<String> section = null;

    private WidgetParameters widget = null;

    public Style() {

    }

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

        if (this.section == null && style.section != null)
            this.section = new ArrayList<>(style.section);

        if (this.widget == null && style.widget != null)
            this.widget = new WidgetParameters(style.widget.getParameters());
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public WidgetParameters getWidget() {
        return widget;
    }

    public void setWidget(WidgetParameters widget) {
        this.widget = widget;
    }

    public String getPage() {
        return page;
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
                "color='" + color + '\'' +
                ", font='" + font + '\'' +
                ", fontSize=" + fontSize +
                ", width=" + width +
                ", page='" + page + '\'' +
                ", section=" + section +
                ", widget=" + widget +
                '}';
    }
}
