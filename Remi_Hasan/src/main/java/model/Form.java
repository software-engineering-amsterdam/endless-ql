package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import expression.Expression;
import expression.ExpressionVariableUndefined;

import java.awt.image.LookupTable;
import java.util.ArrayList;
import java.util.Map;

public class Form {

    public final String identifier;
    public final ArrayList<BlockElement> elements;

    public Form(String identifier, ArrayList<BlockElement> elements) {
        this.identifier = identifier;
        this.elements = elements;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
