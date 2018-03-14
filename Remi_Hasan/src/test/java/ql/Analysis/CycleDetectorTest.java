package ql.Analysis;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ql.QLTestUtilities;

public class CycleDetectorTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void cycleToSelf() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Cycles detected in the following variables: [someString]");

        QLTestUtilities.buildForm(UnknownIdentifiersTest.class
                .getResourceAsStream("/Cycles/CycleToSelf.ql"));
    }

    @Test
    public void cycleBetweenTwo() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);

        String[] expectedStrings = {"Cycles detected in the following variables:", "someString1", "someString2"};
        for (String expectedString : expectedStrings) {
            expectedEx.expectMessage(expectedString);
        }

        QLTestUtilities.buildForm(UnknownIdentifiersTest.class
                .getResourceAsStream("/Cycles/CycleBetweenTwo.ql"));
    }

    @Test
    public void cycleBetweenMultiple() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);

        String[] expectedStrings = {"Cycles detected in the following variables:", "someString1", "someString2",
                "someString3", "someString4"};
        for (String expectedString : expectedStrings) {
            expectedEx.expectMessage(expectedString);
        }

        QLTestUtilities.buildForm(UnknownIdentifiersTest.class
                .getResourceAsStream("/Cycles/CycleBetweenMultiple.ql"));
    }
}
