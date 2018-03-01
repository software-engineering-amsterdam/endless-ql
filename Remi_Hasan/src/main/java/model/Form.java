package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import expression.Expression;
import expression.ExpressionFactory;
import expression.ReturnType;
import visitor.VisitorExpression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Form {

    public final String identifier;
    public final List<Question> questions;
    public final Map<String, Expression> answers;

    public Form(String identifier, List<Question> questions) {
        this.identifier = identifier;
        this.questions = questions;
        this.answers = new HashMap<String, Expression>();
    }

    public void setAnswer(String name, ReturnType type, String value){
        Expression expression = ExpressionFactory.createExpression(type, value);
        answers.put(name, expression);
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
