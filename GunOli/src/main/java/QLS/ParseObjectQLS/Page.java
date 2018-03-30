package QLS.ParseObjectQLS;

import QLS.Analysis.WidgetVisitorInterface;

import java.util.ArrayList;

public class Page extends QLSNode {

    private ArrayList<Section> sections;
    //private ArrayList<Default> defaultSections;
    private String identifier;

    //public Page(ArrayList<Section> sections, ArrayList<Default> defaultSections, String identifier, int line){
    public Page(ArrayList<Section> sections, String identifier, int line){
        super(line);
        //setDefaultSection(defaultSections);
        setSections(sections);
        setIdentifier(identifier);

    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    /*public ArrayList<Default> getDefaultSections() {
        return defaultSections;
    }

    public void setDefaultSection(ArrayList<Default> defaultSections) {
        this.defaultSections = defaultSections;
    }*/

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public <T> T accept(WidgetVisitorInterface<T> visitor) {
        return visitor.visit(this);
    }
}
