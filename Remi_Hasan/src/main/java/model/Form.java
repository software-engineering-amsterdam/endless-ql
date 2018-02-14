package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Form {

    public final String identifier;
    public final Block block;

    public Form(String identifier, Block block){
        this.identifier = identifier;
        this.block = block;
    }

    @Override
    public String toString(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
