package Application;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;

import ParseObjects.Question;
import ParseObjects.Condition;
import ParseObjects.Form;
import antlrGen.QLParser;
import antlrGen.QLLexer;
import Visitor.FormVisitor;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;


public class Parser {

    public Form parseInputToForm(String file) throws UnsupportedOperationException{
        try{

            InputStream stream = new FileInputStream(file);
            QLLexer lexer = new QLLexer(CharStreams.fromStream(stream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            QLParser parser = new QLParser(tokens);
            //Trees.inspect(parser.head(), parser); Debug parse tree, change for later viewing
            FormVisitor visitor = new FormVisitor();
            return visitor.visit(parser.head());
        }catch(Exception e){
            System.out.println("Unable to Parse Selected File");
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public void printQLForm(Form form){
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
}
