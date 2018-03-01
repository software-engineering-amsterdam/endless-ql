package tool;


import tool.model.variables.BooleanVariable;
import tool.model.Question;

public class Main {
    public static void main(String [ ] args){
//        try {
//            ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(args[0]));
//            FormLexer lexer = new FormLexer(input);
//            FormParser parser = new FormParser(new CommonTokenStream(lexer));
//            FormParser.FormBuilderContext tree = parser.formBuilder();
//            QLLoader loader = new QLLoader();
//            ParseTreeWalker.DEFAULT.walk(loader, tree);
//            for (Question qs : loader.getFormNode().getFormData().getPlainQuestionStructures()){
//                System.out.println(qs.getText());
//                System.out.println(qs.getQuestionVariable().getText());
//            }
//            System.out.println(loader.getFormNode().getFormData().getConditionQuestions().size());
//            for (Map.Entry<List<Condition>, List<Question>> entry : loader.getFormNode().getFormData().getConditionQuestions().entrySet()) {
//                for (Question qs : entry.getValue()) {
//                    System.out.println(qs.getText());
//                    System.out.println(qs.getQuestionVariable().getText());
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        BooleanVariable bq = new BooleanVariable("hasBoughtHouse", false);
        Question q = new Question("Did you buy a house?", bq);



        System.out.println(q);


    }

}
