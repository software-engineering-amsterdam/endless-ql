package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Form {

    public final String identifier;
    public final ArrayList<Statement> statements;

    public Form(String identifier, ArrayList<Statement> statements) {
        this.identifier = identifier;
        this.statements = statements;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
