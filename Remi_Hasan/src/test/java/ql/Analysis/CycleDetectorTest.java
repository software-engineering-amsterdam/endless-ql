package ql.Analysis;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ql.QLTestUtilities;

public class CycleDetectorTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testCycleToSelf() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Cycles detected in the following variables: [someString]");

        QLTestUtilities.buildForm(UnknownIdentifiersTest.class
                .getResourceAsStream("/Cycles/CycleToSelf.ql"));
    }

    @Test
    public void testCycleBetweenTwo() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Cycles detected in the following variables: [someString2, someString1]");

        QLTestUtilities.buildForm(UnknownIdentifiersTest.class
                .getResourceAsStream("/Cycles/CycleBetweenTwo.ql"));
    }

    @Test
    public void testCycleBetweenMultiple() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Cycles detected in the following variables: " +
                "[someString2, someString1, someString4, someString3]");

        QLTestUtilities.buildForm(UnknownIdentifiersTest.class
                .getResourceAsStream("/Cycles/CycleBetweenMultiple.ql"));
    }
}
