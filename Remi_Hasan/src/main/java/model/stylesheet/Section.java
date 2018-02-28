package model.stylesheet;

import java.util.ArrayList;

public class Section {
    public final String identifier;
    public final ArrayList<Section> sections;
    // TODO add question
    public final ArrayList<Default> defaults;

    public Section(String identifier, ArrayList<Section> sections, ArrayList<Default> defaults){
        this.identifier = identifier;
        this.sections = sections;
        this.defaults = defaults;
    }
}
