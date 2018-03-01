package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Form {

    public final String identifier;
    public final List<Statement> statements;

    public Form(String identifier, List<Statement> statements) {
        this.identifier = identifier;
        this.statements = statements;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
