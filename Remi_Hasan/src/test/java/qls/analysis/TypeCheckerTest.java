package qls.analysis;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import qls.QLSTestUtilities;

public class TypeCheckerTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void validTypes() throws Exception {
        QLSTestUtilities.buildStyleSheet("/ql/ValidForms/SimpleForm.ql",
                "/qls/ValidStylesheets/SimpleForm.qls");
    }

    @Test
    public void invalidIntegerCheckbox() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Incompatible widget type CHECKBOX for question of type INTEGER");
        QLSTestUtilities.buildStyleSheet("/ql/ValidForms/SimpleForm.ql",
                "/qls/InvalidStylesheets/SimpleFormIntegerCheckbox.qls");
    }

    @Test
    public void invalidBooleanTextbox() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Incompatible widget type TEXTBOX for question of type BOOLEAN");
        QLSTestUtilities.buildStyleSheet("/ql/ValidForms/SimpleForm.ql",
                "/qls/InvalidStylesheets/SimpleFormBooleanTextbox.qls");
    }

    @Test
    public void invalidDefaultStringSlider() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Incompatible widget type SLIDER for question of type STRING");
        QLSTestUtilities.buildStyleSheet("/ql/ValidForms/SimpleForm.ql",
                "/qls/InvalidStylesheets/SimpleFormDefaultStringSlider.qls");
    }

    @Test
    public void invalidDefaultDecimalRadio() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Incompatible widget type RADIO for question of type DECIMAL");
        QLSTestUtilities.buildStyleSheet("/ql/ValidForms/SimpleForm.ql",
                "/qls/InvalidStylesheets/SimpleFormDefaultDecimalRadio.qls");
    }

}
