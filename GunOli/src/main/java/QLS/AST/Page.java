package QLS.AST;

import QLS.Analysis.WidgetVisitorInterface;
import QLS.AST.Statements.Statement;

import java.util.ArrayList;

public class Page extends QLSNode {

    private ArrayList<Statement> statements;
    //private ArrayList<Default> defaultSections;
    private String identifier;

    //public Page(ArrayList<Section> sections, ArrayList<Default> defaultSections, String identifier, int line){
    public Page(ArrayList<Statement> statements, String identifier, int line){
        super(line);
        //setDefaultSection(defaultSections);
        this.statements = statements;
        this.identifier = identifier;

    }

    public ArrayList<Statement> getStatements() {
        return statements;
    }

    /*public ArrayList<Default> getDefaultSections() {
        return defaultSections;
    }

    public void setDefaultSection(ArrayList<Default> defaultSections) {
        this.defaultSections = defaultSections;
    }*/

    public String getIdentifier() {
        return identifier;
    }

    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }
}
