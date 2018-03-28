package AST;

import Nodes.QLForm;
import Nodes.Question;
import QLExceptions.SyntaxException;
import QLExceptions.TypeException;
import org.antlr.v4.runtime.CharStreams;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

class FormReaderTest {

    @Test
    void parseCharStream() {
        String input = "form OrderOfOperations {\n" +
            "\ttest1: \"test1\" integer(3 * 2 + 7 * 2)\n" +
            "}\n";

        FormReader formReader = new FormReader();

        assert formReader.parseCharStream(CharStreams.fromString(input)) != null;
    }

    @Test
    void testExpressionOrder() throws IOException, TypeException, SyntaxException {
        // Read and instantiate form.
        String file = "resources/test_order_of_operations.txt";

        FormReader formReader = new FormReader();

        QLForm form = formReader.parseCharStream(CharStreams.fromFileName(file));
        form.setParents();

        // Get and evaluate questions.
        List<Question> questions = form.getQuestions();
        for(Question q :questions)
            q.getExpressionValue();

        // Make sure questions match their corresponding answers.
        Predicate<Question> p = (q) -> {
            try {
                Float result = q.getResult().getFloat();
                switch (q.getName()) {
                    case "test1": return result == 20.0;
                    case "test2": return result == 55.0;
                    case "test3": return result == 10.0;
                    case "test4": return result == 1414.0;
                    case "test5": return result == 570.0;
                    default: return false;
                }
            } catch (TypeException e) {
                e.printStackTrace();
                return false;
            }
        };
        assert questions.stream().allMatch(p);
    }
}
