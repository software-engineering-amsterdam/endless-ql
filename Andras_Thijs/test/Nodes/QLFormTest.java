package Nodes;

import AST.FormReader;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

class QLFormTest {
    private QLForm setup(String input) {
        FormReader formReader = new FormReader();
        return formReader.parseCharStream(CharStreams.fromString(input));
    }

    @Test
    void setParents() {
        String input = "form OrderOfOperations {\n" +
                "\ttest1: \"test1\" integer(3 * 2 + 7 * 2)\n" +
                "\tif(true) {\n" +
                "\t\ttest2: \"test2\" integer" +
                "\t}" +
                "}\n";
        QLForm root = setup(input);

        root.setParents();

        Predicate<ASTNode> p = (q) -> q.getParent() == root;

        assert root.getQuestions().stream().allMatch(p);
        assert root.getConditions().stream().allMatch(p);
    }

    @Test
    void getName() {
        String input = "form OrderOfOperations {}\n";
        QLForm root = setup(input);

        assert root.getName().equals("OrderOfOperations");
    }

    @Test
    void getQuestions() {
        String input = "form OrderOfOperations {\n" +
                "\ttest1: \"test1\" integer(3 * 2 + 7 * 2)\n" +
                "}\n";
        QLForm root = setup(input);

        assert root.getQuestions() != null;
    }

    @Test
    void getConditions() {
        String input = "form OrderOfOperations {\n" +
                "\tif(true) {\n" +
                "\t}" +
                "}\n";
        QLForm root = setup(input);

        assert root.getConditions() != null;
    }

    @Test
    void getAllQuestionsNoConditions() {
        String input = "form OrderOfOperations {\n" +
                "\ttest1: \"test1\" integer(3 * 2 + 7 * 2)\n" +
                "}\n";
        QLForm root = setup(input);

        assert root.getAllQuestions() != null;
        assert root.getAllQuestions().equals(root.getQuestions());
    }

    @Test
    void getAllQuestionsWithConditions() {
        String input = "form OrderOfOperations {\n" +
                "\ttest1: \"test1\" integer(3 * 2 + 7 * 2)\n" +
                "\tif(foo) {\n" +
                "\t\ttest2: \"test2\" integer" +
                "\t}" +
                "}\n";
        QLForm root = setup(input);

        assert root.getAllQuestions() != null;
        assert !root.getAllQuestions().equals(root.getQuestions());
        assert root.getAllQuestions().containsAll(root.getQuestions());
    }
}