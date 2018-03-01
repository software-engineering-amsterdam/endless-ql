package model.stylesheet;

import java.util.List;

public class Section {
    public final String identifier;
    public final List<Section> sections;
    // TODO add question
    public final List<Default> defaults;

    public Section(String identifier, List<Section> sections, List<Default> defaults){
        this.identifier = identifier;
        this.sections = sections;
        this.defaults = defaults;
    }
}
