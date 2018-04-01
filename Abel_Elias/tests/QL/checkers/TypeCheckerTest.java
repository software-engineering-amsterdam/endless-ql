package tests.QL.checkers;

import QL.parsing.TreeBuilder;
import QL.parsing.checkers.CyclicChecker;
import QL.parsing.checkers.TypeChecker;
import QL.parsing.gen.QLParser;
import org.junit.Before;

import java.io.FileInputStream;
import java.io.IOException;

public class TypeCheckerTest {
    private TypeChecker typeChecker;

    @Before
    public void init() {
        this.typeChecker = new TypeChecker();
    }


}
