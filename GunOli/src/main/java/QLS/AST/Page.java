package QLS.AST;

import QLS.Analysis.WidgetVisitorInterface;
import QLS.AST.Statements.Statement;

import java.util.ArrayList;

public class Page extends QLSNode {

    private ArrayList<Statement> statements;
    private String identifier;

    public Page(ArrayList<Statement> statements, String identifier, int line){
        super(line);
        this.statements = statements;
        this.identifier = identifier;
    }

    public ArrayList<Statement> getStatements() {
        return statements;
    }


    public String getIdentifier() {
        return identifier;
    }

    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }
}
