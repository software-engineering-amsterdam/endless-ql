package Application;

import java.io.FileInputStream;
import java.io.InputStream;

import QL.ParseObjectsQL.QuestionMap;
import QL.QLVisitor.ExpressionTable;
import QL.ParseObjectsQL.Question;
import QL.ParseObjectsQL.Form;
import QL.QLAntlrGen.QLParser;
import QL.QLAntlrGen.QLLexer;
import QL.QLVisitor.FormVisitor;
import QLS.ParseObjectQLS.*;
import QLS.ParseObjectQLS.Widgets.Widget;
import QLS.QLSAntlrGen.QLSLexer;
import QLS.QLSAntlrGen.QLSParser;
import QLS.QLSVisitor.StylesheetVisitor;
import org.antlr.v4.gui.Trees; //for debug printing
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
            //parser.reset();

            ExpressionTable expressionTable = new ExpressionTable();
            FormVisitor visitor = new FormVisitor(expressionTable);
            return visitor.visit(parser.head());
        }catch(Exception e){
            System.out.println("Unable to Parse Selected File");
            throw new UnsupportedOperationException(e.getMessage());
        }
    }

    public Stylesheet parseInputToStyleSheet(String file) throws UnsupportedOperationException{
        try{
            InputStream stream = new FileInputStream(file);
            QLSLexer lexer = new QLSLexer(CharStreams.fromStream(stream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            QLSParser parser = new QLSParser(tokens);

            //Trees.inspect(parser.head(), parser); //Debug QLS parse tree, change later
            //parser.reset();

            StylesheetVisitor visitor = new StylesheetVisitor();
            return visitor.visit(parser.head());
        }catch(Exception e){
            System.out.println("Unable to Parse Selected File");
            throw new UnsupportedOperationException(e.getMessage());
        }
    }


    //debugging prints, remove after completion

    public void printQLForm(Form form){
        for(Question question : form.getBlock().getQuestions()){
            String questionName = question.getIdentifier();
            ExpressionTable expressionTable = form.getExpressionTable();
            System.out.println( question.getIdentifier() + " : " +
                                question.getText()+" : "+
                                question.getType()+" : "+
                                //question.isEnabled()+" : "+
                                expressionTable.getExpression(questionName).evaluate().getValue());
        }
    }

    public void printQLSStyleSheet(Stylesheet ss){
        for(Page page : ss.getPages()){
            System.out.println(page.getIdentifier());
            for(Section sec : page.getSections()){
                System.out.println("    section : " + sec.getSectionName());

                for(Default d : sec.getDefaultSection()){
                    System.out.println("    default : "+ d.getType());
                    for(Widget w : d.getWidgets()){
                        System.out.println("    widget");
                    }
                }

                for(QLSQuestion q : sec.getQuestions()){
                    System.out.println("    question : " + q.getIdentifier());
                    for(Widget w : q.getWidget()){
                        System.out.println("    widget");
                    }
                }
            }
            System.out.println("<EOP>");
        }
    }
}
