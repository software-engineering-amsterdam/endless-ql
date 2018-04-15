package QLS.AST;

import QLS.Analysis.WidgetVisitorInterface;
import QLS.AST.Statements.QLSQuestion;
import QLS.AST.Statements.Statement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Stylesheet extends QLSNode {
    private ArrayList<Page> pages;
    List<QLSQuestion> qlsQuestions;

    private String identifier;

    public Stylesheet(ArrayList<Page> pages, String identifier, int line){
        super(line);
        this.identifier = identifier;
        this.pages = pages;
        this.qlsQuestions = gatherQuestions();
    }

    public ArrayList<Page> getPages() {
        return pages;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<QLSQuestion> getQlsQuestions() { return qlsQuestions; }

    private List<QLSQuestion> gatherQuestions(){
        List<QLSQuestion> questions = new ArrayList<>();
        for(Page page : pages){
            for(Statement statement : page.getStatements()){
                if(statement.getClass().getSimpleName().equals("QLSQuestion")){
                    questions.add((QLSQuestion) statement);
                }
            }
        }
        return questions;
    }

    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
