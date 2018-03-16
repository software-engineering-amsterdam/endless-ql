package qls.model;

import java.util.List;

public class Section {
    public final String identifier;
    public final List<Section> sections;
    public final List<Question> questions;
    public final List<Default> defaults;

    public Section(String identifier, List<Section> sections, List<Question> questions, List<Default> defaults) {
        this.identifier = identifier;
        this.sections = sections;
        this.questions = questions;
        this.defaults = defaults;
    }
}
