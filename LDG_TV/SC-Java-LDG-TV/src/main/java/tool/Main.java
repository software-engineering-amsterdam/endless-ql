package tool;


import tool.model.variables.BooleanVariable;
import tool.model.Question;

public class Main {
    public static void main(String [ ] args){
        try {
            ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(args[0]));
            FormLexer lexer = new FormLexer(input);
            FormParser parser = new FormParser(new CommonTokenStream(lexer));
            FormParser.FormBuilderContext tree = parser.formBuilder();
            QLLoader loader = new QLLoader();
            ParseTreeWalker.DEFAULT.walk(loader, tree);
            for (QuestionStructure qs : loader.getFormNode().getFormData().getAllQuestions()){
                System.out.println(qs.getLabel());
                System.out.println(qs.getQuestionVariable().getLabel());
                System.out.println(qs.getQuestionVariable().getQuestionVariableType());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
