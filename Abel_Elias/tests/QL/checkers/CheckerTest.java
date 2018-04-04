package tests.QL.checkers;

import QL.parsing.TreeBuilder;
import QL.parsing.gen.QLParser;

import java.io.FileInputStream;
import java.io.IOException;

public class CheckerTest {

    QLParser.FormContext getFormContext(String qlFilePath) {
        try {
            FileInputStream qlInputStream = new FileInputStream(qlFilePath);
            return new TreeBuilder().build(qlInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Could not find QL file");
        }
    }
}
