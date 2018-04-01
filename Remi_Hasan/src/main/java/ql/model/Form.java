package ql.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ql.model.statement.Statement;
import ql.visitor.IQLVisitor;

import java.util.List;

public class Form extends QLNode {

    private final String identifier;
    private final List<Statement> statements;

    public Form(String identifier, List<Statement> statements) {
        this.identifier = identifier;
        this.statements = statements;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
