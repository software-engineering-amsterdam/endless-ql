import java.io.InputStream;
import ParseObjects.Question;
import ParseObjects.Condition;
import ParseObjects.Form;
import antlrGen.QLParser;
import antlrGen.QLLexer;
import Visitor.FormVisitor;
import org.antlr.v4.gui.Trees;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Main {

    public Form parseForm(InputStream stream){
        try{
            QLLexer lexer = new QLLexer(CharStreams.fromStream(stream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            QLParser parser = new QLParser(tokens);
            //Trees.inspect(parser.head(), parser); Debug parse tree, change for later viewing

            FormVisitor visitor = new FormVisitor();
            return visitor.visit(parser.head());
        }catch(Exception e){
            System.out.println(e);

            return null;
        }
    }

    public void start(){
        String file = "example.ql";
        InputStream stream = getClass().getResourceAsStream(file);
        Form form = parseForm(stream);
        
        //Debug form
        for(Question question : form.getBlock().getQuestions()){
            System.out.println(question.getIdentifier()+ " : " + question.getText()+" : "+ question.getType());
        }

        for(Condition condition : form.getBlock().getConditions()){
            for(Question question : condition.getBlock().getQuestions()){
                System.out.println(question.getIdentifier()+ " : " + question.getText()+" : "+ question.getType());
            }
        }
    }

    public static void main(String[] args) {
        new Main().start();
    }
}