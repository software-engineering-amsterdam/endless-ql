package model;

import java.util.ArrayList;

public class StyleSheet {
    public final String identifier;

    // TODO: define element class within stylesheet
    public final ArrayList<Page> pages;

    public StyleSheet(String identifier, ArrayList<Page> pages){
        this.identifier = identifier;
        this.pages = pages;
    }
}
