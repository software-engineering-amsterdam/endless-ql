package tests.QL.checkers;

import QL.classes.Question;
import QL.parsing.TreeBuilder;
import QL.parsing.checkers.CyclicChecker;
import QL.parsing.gen.QLParser;
import QL.parsing.visitors.FormVisitor;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CycicCheckerTest {

    private CyclicChecker cyclicChecker;

    @Before
    private void init() {
        CyclicChecker cyclicChecker = new CyclicChecker();
    }

    private QLParser.FormContext getFormContext(String qlFilePath) {
        try {
            FileInputStream qlInputStream = new FileInputStream(qlFilePath);
            QLParser.FormContext form = new TreeBuilder().build(qlInputStream);
            return form;
        } catch (IOException e) {
            throw new RuntimeException("Could not find QL file");
        }
    }

    @Test
    private void issueCyclicError() {
//        QLParser.this.getFormContext()
//        cyclicChecker.checkForm("");
    }

}
