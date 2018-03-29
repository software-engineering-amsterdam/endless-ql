package qls.model;

import qls.IQLSVisitor;

import java.util.List;

public class StyleSheet extends QLSNode {

    private final String identifier;
    private final List<Page> pages;

    public StyleSheet(String identifier, List<Page> pages) {
        this.identifier = identifier;
        this.pages = pages;
    }

    public String getIdentifier() {
        return identifier;
    }

    public List<Page> getPages() {
        return pages;
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
