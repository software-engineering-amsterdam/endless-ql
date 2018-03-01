package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Form {

    public final String identifier;
    public final List<Question> questions;

    public Form(String identifier, List<Question> questions) {
        this.identifier = identifier;
        this.questions = questions;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
