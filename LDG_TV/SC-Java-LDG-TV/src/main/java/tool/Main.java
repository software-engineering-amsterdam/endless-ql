package tool;

import antlr.FormLexer;
import antlr.FormParser;
import domain.model.Condition;
import domain.model.question.QuestionStructure;
import loader.QLLoader;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String [ ] args){
        try {
            ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(args[0]));
            FormLexer lexer = new FormLexer(input);
            FormParser parser = new FormParser(new CommonTokenStream(lexer));
            FormParser.FormBuilderContext tree = parser.formBuilder();
            QLLoader loader = new QLLoader();
            ParseTreeWalker.DEFAULT.walk(loader, tree);
            for (QuestionStructure qs : loader.getFormNode().getFormData().getPlainQuestionStructures()){
                System.out.println(qs.getLabel());
                System.out.println(qs.getQuestionVariable().getLabel());
            }
            System.out.println(loader.getFormNode().getFormData().getConditionQuestions().size());
            for (Map.Entry<List<Condition>, List<QuestionStructure>> entry : loader.getFormNode().getFormData().getConditionQuestions().entrySet()) {
                for (QuestionStructure qs : entry.getValue()) {
                    System.out.println(qs.getLabel());
                    System.out.println(qs.getQuestionVariable().getLabel());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
