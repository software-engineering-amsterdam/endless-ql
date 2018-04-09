package nl.khonraad.qls.language;

import java.util.LinkedList;
import java.util.List;

public class StyleTree<T> {

    public enum StyleType {
        
        Stylesheet, 
        Page, 
        Section, 
        Question, 
        StyleELement, 
        DefaultStyle, 
        Widget, 
        Width, 
        Color, 
        Font, 
        FontSize
    }

    T                  data;
    StyleTree<T>       parent;
    List<StyleTree<T>> children;

    public StyleTree( T data ) {

        this.data = data;
        this.children = new LinkedList<>();
    }

    public StyleTree<T> addChild( T child ) {

        StyleTree<T> childNode = new StyleTree<>( child );
        childNode.parent = this;
        this.children.add( childNode );
        return childNode;
    }

    public StyleTree<T> parent() {
        return parent;
    }

    public List<StyleTree<T>> children() {
        return children;
    }

    public Style data() {
        return (Style) data;
    }
}