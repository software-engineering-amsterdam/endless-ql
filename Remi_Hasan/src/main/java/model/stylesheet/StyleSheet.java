package model.stylesheet;

import java.util.ArrayList;
import java.util.List;

public class StyleSheet {
    public final String identifier;

    // TODO: define element class within stylesheet
    public final List<Page> pages;

    public StyleSheet(String identifier, List<Page> pages){
        this.identifier = identifier;
        this.pages = pages;
    }
}
