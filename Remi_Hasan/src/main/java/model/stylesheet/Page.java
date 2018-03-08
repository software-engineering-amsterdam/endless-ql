package model.stylesheet;

import java.util.List;

public class Page {

    public final String identifier;
    private final List<Default> defaults;
    private final List<Section> sections;

    public Page(String identifier, List<Default> defaults, List<Section> sections) {
        this.identifier = identifier;
        this.defaults = defaults;
        this.sections = sections;
    }
}
