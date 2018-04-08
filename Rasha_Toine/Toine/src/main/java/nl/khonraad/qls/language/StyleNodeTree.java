package nl.khonraad.qls.language;

import java.util.LinkedList;
import java.util.List;

public class StyleNodeTree<T> {

    public enum NodeType {
        Stylesheet, Page, Section, Question, StyleELement, DefaultStyle, Widget, Width, Color, Font, FontSize

    }

    T                      data;
    StyleNodeTree<T>       parent;
    List<StyleNodeTree<T>> children;

    public StyleNodeTree( T data ) {

        this.data = data;

        this.children = new LinkedList<>();
    }

    public StyleNodeTree<T> addChild( T child ) {

        StyleNodeTree<T> childNode = new StyleNodeTree<>( child );
        childNode.parent = this;
        this.children.add( childNode );
        return childNode;
    }

    public StyleNodeTree<T> parent() {
        return parent;
    }

    public List<StyleNodeTree<T>> children() {
        return children;
    }

    public StyleNode data() {
        return (StyleNode) data;
    }
}