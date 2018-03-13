package ql.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class Form extends Node {

    public final String identifier;
    public final List<Question> questions;

    public Form(Token token, String identifier, List<Question> questions) {
        super(token);
        this.identifier = identifier;
        this.questions = questions;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
