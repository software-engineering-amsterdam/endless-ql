package ql.analysis;

import org.junit.Test;
import ql.QLTestUtilities;
import ql.analysis.warning.DuplicateLabelDetector;
import ql.evaluation.SymbolTable;
import ql.model.Form;

import java.util.Set;

import static junit.framework.TestCase.assertEquals;

public class DuplicateLabelTest {

    @Test
    public void duplicateLabel() throws Exception {
        Form form = QLTestUtilities.buildForm(UnknownIdentifiersTest.class
                .getResourceAsStream("/ql/DuplicateLabels/DuplicateLabels.ql"));

        DuplicateLabelDetector duplicateLabelDetector = new DuplicateLabelDetector();
        Set<String> warnings = duplicateLabelDetector.analyze(form, new SymbolTable());

        assertEquals(1, warnings.size());
    }

    @Test
    public void noDuplicateLabel() throws Exception {
        Form form = QLTestUtilities.buildForm(UnknownIdentifiersTest.class
                .getResourceAsStream("/ql/ValidForms/SimpleForm.ql"));

        DuplicateLabelDetector duplicateLabelDetector = new DuplicateLabelDetector();
        Set<String> warnings = duplicateLabelDetector.analyze(form, new SymbolTable());

        assertEquals(0, warnings.size());
    }
}
