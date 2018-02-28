package model.stylesheet;

import java.util.ArrayList;

public class Page {

    public final String identifier;
    private final ArrayList<Default> defaults;
    private final ArrayList<Section> sections;

    public Page(String identifier, ArrayList<Default> defaults, ArrayList<Section> sections){
        this.identifier = identifier;
        this.defaults = defaults;
        this.sections = sections;
    }
}
