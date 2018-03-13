package qls.model;

import java.util.List;

public class StyleSheet {
    public final String identifier;

    // TODO: define element class within model
    public final List<Page> pages;

    public StyleSheet(String identifier, List<Page> pages) {
        this.identifier = identifier;
        this.pages = pages;
    }
}
