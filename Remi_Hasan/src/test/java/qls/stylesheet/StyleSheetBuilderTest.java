package qls.stylesheet;

import org.junit.Test;
import qls.QLSTestUtilities;
import qls.model.Page;
import qls.model.StyleSheet;
import qls.model.statement.QuestionReference;
import qls.model.statement.Section;
import qls.visitor.QLSVisitor;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class StyleSheetBuilderTest {

    private List<Section> getPageSections(Page page) {
        List<Section> sections = new ArrayList<>();

        page.accept(new QLSVisitor<Void>() {
            @Override
            public Void visit(Section section) {
                sections.add(section);
                return null;
            }
        });

        return sections;
    }

    private List<QuestionReference> getSectionQuestions(Section section) {
        List<QuestionReference> questions = new ArrayList<>();

        section.accept(new QLSVisitor<Void>() {
            @Override
            public Void visit(QuestionReference questionReference) {
                questions.add(questionReference);
                return null;
            }
        });

        return questions;
    }

    @Test
    public void simpleForm() throws Exception {
        StyleSheet styleSheet = QLSTestUtilities.buildStyleSheet("/ql/ValidForms/SimpleForm.ql",
                "/qls/ValidStylesheets/SimpleForm.qls");

        assertEquals(2, styleSheet.getPages().size());

        Page firstPage = styleSheet.getPages().get(0);
        assertEquals("Numbers", firstPage.getIdentifier());

        List<Section> firstPageSections = this.getPageSections(firstPage);
        assertEquals(2, firstPageSections.size());
        assertEquals(2, this.getSectionQuestions(firstPageSections.get(1)).size());
    }
}
