package qls.stylesheet;

import org.junit.Test;
import ql.model.expression.ReturnType;
import qls.QLSTestUtilities;
import qls.model.Page;
import qls.model.Section;
import qls.model.StyleSheet;
import qls.model.style.StyleAttributeWidth;
import qls.model.widget.WidgetRadio;
import qls.model.widget.WidgetType;

import static junit.framework.TestCase.assertEquals;

public class StyleSheetBuilderTest {

    @Test
    public void simpleForm() throws Exception {
        StyleSheet styleSheet = QLSTestUtilities.buildStyleSheet("/ql/ValidForms/SimpleForm.ql",
                "/qls/ValidStylesheets/SimpleForm.qls");

        assertEquals(2, styleSheet.getPages().size());

        // First page TODO
        Page firstPage = styleSheet.getPages().get(0);
        assertEquals("Numbers", firstPage.getIdentifier());
//        assertEquals(2, firstPage.getSections().size());
//
//        Section firstPageFirstSection = firstPage.getSections().get(0);
//        assertEquals("Integer", firstPageFirstSection.getTitle());
//        assertEquals(1, firstPageFirstSection.getStatements().size());
//
//        Section firstPageSecondSection = firstPage.getSections().get(1);
//        assertEquals("Decimal", firstPageSecondSection.getTitle());
//        assertEquals(3, firstPageSecondSection.getStatements().size());
//
//        // Second page
//        Page secondPage = styleSheet.getPages().get(1);
//        assertEquals(1, secondPage.getSections().size());
//        assertEquals(3, secondPage.getSections().get(0).getStatements().size());
//
//        assertEquals(2, secondPage.getDefaultStyles().size());
//        assertEquals(ReturnType.BOOLEAN, secondPage.getDefaultStyles().get(0).getType());
//        assertEquals(WidgetType.RADIO, secondPage.getDefaultStyles().get(0).getWidget().getType());
//        assertEquals(1, secondPage.getDefaultStyles().get(0).getStyleAttributes().size());
//
//        StyleAttributeWidth styleAttributeWidth =
//                (StyleAttributeWidth) secondPage.getDefaultStyles().get(0).getStyleAttributes().get(0);
//        assertEquals(400, styleAttributeWidth.getWidth());
//
//        WidgetRadio radioWidget = (WidgetRadio) secondPage.getDefaultStyles().get(0).getWidget();
//        assertEquals("Yes", radioWidget.getTrueLabel());
//        assertEquals("No", radioWidget.getFalseLabel());
    }
}
