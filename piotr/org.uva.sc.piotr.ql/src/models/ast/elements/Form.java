package models.ast.elements;

import models.ast.elements.statement.Statement;

import javax.json.Json;
import java.util.ArrayList;

public class Form {

    private String name;
    private ArrayList<Statement> statementList = new ArrayList<>();

    public Form(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Statement> getStatementList() {
        return statementList;
    }

    public void setStatementList(ArrayList<Statement> statementList) {
        this.statementList = statementList;
    }

    public boolean addStatement(Statement statement) {
        return this.statementList.add(statement);
    }

    public void debugPrint() {
        System.out.println("Form: " + this.name);
        for (Statement statement : this.statementList) {
            statement.debugPrint();
        }
    }

}
