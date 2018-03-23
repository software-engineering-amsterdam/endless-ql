package ql.analysis;

import org.junit.Test;
import ql.QLTestUtilities;
import ql.model.Form;

import java.util.Set;

import static junit.framework.TestCase.assertEquals;

public class DuplicateLabelTest {

    @Test
    public void duplicateLabel() throws Exception {
        Form form = QLTestUtilities.buildForm(UnknownIdentifiersTest.class
                .getResourceAsStream("/ql/DuplicateLabels/DuplicateLabels.ql"));

        DuplicateLabelDetector duplicateLabelDetector = new DuplicateLabelDetector(form);
        Set<String> warnings = duplicateLabelDetector.getDuplicateLabelWarnings();

        assertEquals(1, warnings.size());
    }

    @Test
    public void noDuplicateLabel() throws Exception {
        Form form = QLTestUtilities.buildForm(UnknownIdentifiersTest.class
                .getResourceAsStream("/ql/ValidForms/SimpleForm.ql"));

        DuplicateLabelDetector duplicateLabelDetector = new DuplicateLabelDetector(form);
        Set<String> warnings = duplicateLabelDetector.getDuplicateLabelWarnings();

        assertEquals(0, warnings.size());
    }
}
