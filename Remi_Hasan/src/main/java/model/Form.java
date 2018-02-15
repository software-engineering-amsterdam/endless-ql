package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import expression.Expression;
import expression.ExpressionVariableUndefined;

import java.util.ArrayList;
import java.util.Map;

public class Form {

    public final String identifier;
    public final ArrayList<BlockElement> elements;
    public final Map<String, Question> lookupTable;

    public Form(String identifier, ArrayList<BlockElement> elements, Map<String, Question> lookupTable){
        this.identifier = identifier;
        this.elements = elements;
        this.lookupTable = lookupTable;
    }

    @Override
    public String toString(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public Expression getQuestionAnswer(String identifier) {
        Question question = lookupTable.get(identifier);
        if(question != null) {
            return question.answer;
        } else {
            return new ExpressionVariableUndefined();
        }
    }
}
