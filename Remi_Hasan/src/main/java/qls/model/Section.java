package qls.model;

import java.util.List;

public class Section {
    public final String identifier;
    public final List<Section> sections;
    public final List<Default> defaults;
    public final List<Question> questions;

    public Section(String identifier, List<Section> sections, List<Default> defaults, List<Question> questions) {
        this.identifier = identifier;
        this.sections = sections;
        this.defaults = defaults;
        this.questions = questions;
    }
}
