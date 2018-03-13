package Application;

import java.io.FileInputStream;
import java.io.InputStream;

import ParseObjects.QuestionMap;
import Visitor.ExpressionTable;
import ParseObjects.Question;
import ParseObjects.Form;
import QLAntlrGen.QLParser;
import QLAntlrGen.QLLexer;
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

            //Trees.inspect(parser.head(), parser); //Debug parse tree, change for later viewing

            ExpressionTable expressionTable = new ExpressionTable();
            FormVisitor visitor = new FormVisitor(expressionTable);
            return visitor.visit(parser.head());
        }catch(Exception e){
            System.out.println("Unable to Parse Selected File");
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public void printQLForm(Form form, QuestionMap qm){
        //Debug form
        for(Question question : form.getBlock().getQuestions()){
            String questionName = question.getIdentifier();
            System.out.println( qm.getQuestion(questionName).getIdentifier() + " : " +
                                qm.getQuestion(questionName).getText()+" : "+
                                qm.getQuestion(questionName).getType()+" : "+
                                qm.getQuestion(questionName).getAnswer().evaluate().getValue());
        }
    }
}
