package qls.model;

import org.antlr.v4.runtime.Token;

import java.util.List;

public class StyleSheet extends QLSNode {
    public final String identifier;
    public final List<Page> pages;

    public StyleSheet(Token token, String identifier, List<Page> pages) {
        super(token);
        this.identifier = identifier;
        this.pages = pages;
    }
}
