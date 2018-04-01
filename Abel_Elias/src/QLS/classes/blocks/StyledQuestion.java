package QLS.classes.blocks;

import QL.classes.Question;
import QLS.classes.properties.Property;
import gui.widgets.Widget;

import java.util.List;

public class StyledQuestion extends Element {

    private String name;
    private Question question;
    private String parentId;
    private Widget widget;
    private List<Property> properties;

    public StyledQuestion(String name, Question question, String parentId, Widget widget, List<Property> properties) {
        super();
        this.name = name;
        this.widget = widget;
        this.question = question;
        this.parentId = parentId;
        this.properties = properties;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Question getQuestion() {
        return this.question;
    }

    public String getParentId() {
        return this.parentId;
    }

    public Widget getWidget(){
        return this.widget;
    }

    public List<Property> getProperties() { return properties; }

}