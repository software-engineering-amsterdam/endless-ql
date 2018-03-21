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

        assertEquals(styleSheet.getPages().size(), 2);

        // First page
        Page firstPage = styleSheet.getPages().get(0);
        assertEquals(firstPage.identifier, "Numbers");
        assertEquals(firstPage.getSections().size(), 2);

        Section firstPageFirstSection = firstPage.getSections().get(0);
        assertEquals(firstPageFirstSection.identifier, "Integer");
        assertEquals(firstPageFirstSection.getQuestions().size(), 1);
        assertEquals(firstPageFirstSection.getQuestions().get(0).name, "someInteger");
        assertEquals(firstPageFirstSection.getQuestions().get(0).getWidget().type, WidgetType.SPINBOX);

        Section firstPageSecondSection = firstPage.getSections().get(1);
        assertEquals(firstPageSecondSection.identifier, "Decimal");
        assertEquals(firstPageSecondSection.getQuestions().size(), 2);
        assertEquals(firstPageSecondSection.getQuestions().get(0).name, "someDecimal");
        assertEquals(firstPageSecondSection.getQuestions().get(0).getWidget().type, WidgetType.SLIDER);

        assertEquals(firstPageSecondSection.getDefaultStyles().size(), 1);
        assertEquals(firstPageSecondSection.getDefaultStyles().get(0).type, ReturnType.MONEY);
        assertEquals(firstPageSecondSection.getDefaultStyles().get(0).getWidget().type, WidgetType.TEXTBOX);

        // Second page
        Page secondPage = styleSheet.getPages().get(1);
        assertEquals(secondPage.getSections().size(), 1);
        assertEquals(secondPage.getSections().get(0).getSections().size(), 2);

        assertEquals(secondPage.getDefaultStyles().size(), 2);
        assertEquals(secondPage.getDefaultStyles().get(0).type, ReturnType.BOOLEAN);
        assertEquals(secondPage.getDefaultStyles().get(0).getWidget().type, WidgetType.RADIO);
        assertEquals(secondPage.getDefaultStyles().get(0).getStyleAttributes().size(), 1);

        StyleAttributeWidth styleAttributeWidth =
                (StyleAttributeWidth) secondPage.getDefaultStyles().get(0).getStyleAttributes().get(0);
        assertEquals(styleAttributeWidth.getWidth(), 400);

        WidgetRadio radioWidget = (WidgetRadio) secondPage.getDefaultStyles().get(0).getWidget();
        assertEquals(radioWidget.getTrueLabel(), "Yes");
        assertEquals(radioWidget.getFalseLabel(), "No");
    }
}
