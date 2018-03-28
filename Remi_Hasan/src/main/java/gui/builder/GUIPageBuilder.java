package gui.builder;

import gui.model.GUIPage;
import gui.model.GUIQuestion;
import gui.model.GUISection;
import qls.QLSVisitor;
import qls.model.DefaultStyle;
import qls.model.Page;
import qls.model.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GUIPageBuilder extends QLSVisitor<GUIPage> {

    private final Map<String, List<GUIQuestion>> guiQuestionMap;

    GUIPageBuilder(Map<String, List<GUIQuestion>> guiQuestionMap) {
        this.guiQuestionMap = guiQuestionMap;
    }

    @Override
    public GUIPage visit(Page page) {
        List<GUISection> guiSections = new ArrayList<>();

        // Collect defaultStyles for this page TODO: separate class?
        List<DefaultStyle> defaultStyles = new ArrayList<>();
        page.accept(new QLSVisitor<Void>() {
            @Override
            public Void visit(DefaultStyle defaultStyle) {
                defaultStyles.add(defaultStyle);
                return null;
            }
        });

        // Add sections
        GUISectionBuilder guiSectionBuilder = new GUISectionBuilder(guiQuestionMap, defaultStyles);
        for(Section section : page.getSections()) {
            guiSections.add(guiSectionBuilder.visit(section));
        }

        return new GUIPage(page.getIdentifier(), guiSections);
    }
}
