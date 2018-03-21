package qls.analysis;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ql.QLFormBuilder;
import ql.model.Form;
import qls.QLSFormBuilder;

public class TypeCheckerTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    private void typeCheckFiles(String qlFile, String qlsFile) throws Exception {
        QLFormBuilder qlFormBuilder = new QLFormBuilder();
        Form form = qlFormBuilder.buildForm(TypeCheckerTest.class.getResourceAsStream(qlFile));

        QLSFormBuilder qlsFormBuilder = new QLSFormBuilder(form, qlFormBuilder.getSymbolTable());
        qlsFormBuilder.parseStyleSheet(TypeCheckerTest.class.getResourceAsStream(qlsFile));
    }

    @Test
    public void validTypes() throws Exception {
        this.typeCheckFiles("/ql/ValidForms/SimpleForm.ql", "/qls/ValidStylesheets/SimpleForm.qls");
    }

    @Test
    public void invalidIntegerCheckbox() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Incompatible widget type CHECKBOX for question of type INTEGER");
        this.typeCheckFiles("/ql/ValidForms/SimpleForm.ql", "/qls/InvalidStylesheets/SimpleFormIntegerCheckbox.qls");
    }

    @Test
    public void invalidBooleanTextbox() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Incompatible widget type TEXTBOX for question of type BOOLEAN");
        this.typeCheckFiles("/ql/ValidForms/SimpleForm.ql", "/qls/InvalidStylesheets/SimpleFormBooleanTextbox.qls");
    }

    @Test
    public void invalidDefaultStringSlider() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Incompatible widget type SLIDER for question of type STRING");
        this.typeCheckFiles("/ql/ValidForms/SimpleForm.ql", "/qls/InvalidStylesheets/SimpleFormDefaultStringSlider.qls");
    }

    @Test
    public void invalidDefaultDecimalRadio() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Incompatible widget type RADIO for question of type DECIMAL");
        this.typeCheckFiles("/ql/ValidForms/SimpleForm.ql", "/qls/InvalidStylesheets/SimpleFormDefaultDecimalRadio.qls");
    }

    @Test
    public void invalidUnplacedFields() throws Exception {
        String[] expectedStrings = {"Unplaced questions by QLS: ", "someInteger", "someBoolean"};

        expectedEx.expect(IllegalArgumentException.class);
        for (String expectedString : expectedStrings) {
            expectedEx.expectMessage(expectedString);
        }
        this.typeCheckFiles("/ql/ValidForms/SimpleForm.ql", "/qls/InvalidStylesheets/SimpleFormUnplacedFields.qls");
    }

    @Test
    public void invalidMultiplePlacedFields() throws Exception {
        String[] expectedStrings = {"Question(s) referenced more than once: ", "someDecimal", "someDate"};

        expectedEx.expect(IllegalArgumentException.class);
        for (String expectedString : expectedStrings) {
            expectedEx.expectMessage(expectedString);
        }
        this.typeCheckFiles("/ql/ValidForms/SimpleForm.ql", "/qls/InvalidStylesheets/SimpleFormMultiplePlacedFields.qls");
    }

}
