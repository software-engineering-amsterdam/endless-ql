package ParseObjectQLS;

import java.util.ArrayList;

public class Page {

    private ArrayList<Section> sections;
    private ArrayList<Default> defaultSections;
    private String identifier;

    public Page(ArrayList<Section> sections, ArrayList<Default> defaultSections, String identifier){

        setDefaultSection(defaultSections);
        setSections(sections);
        setIdentifier(identifier);

    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public ArrayList<Default> getDefaultSections() {
        return defaultSections;
    }

    public void setDefaultSection(ArrayList<Default> defaultSections) {
        this.defaultSections = defaultSections;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
