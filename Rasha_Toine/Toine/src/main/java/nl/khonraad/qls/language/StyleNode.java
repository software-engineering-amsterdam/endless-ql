package nl.khonraad.qls.language;

import java.util.Objects;

import nl.khonraad.qls.language.StyleNodeTree.NodeType;

public class StyleNode {

    private NodeType nodeType;
    private String   string;

    public StyleNode( NodeType nodeType, String string ) {
        this.nodeType = nodeType;
        this.string = string;
    }

    public String string() {
        return string;
    }

    public NodeType nodeType() {
        return this.nodeType;
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.string );
    }

    @Override
    public boolean equals( Object object ) {
        if ( object == null || getClass() != object.getClass() ) {
            return false;
        }
        final StyleNode other = (StyleNode) object;
        return Objects.equals( this.string, other.string ) && (this.nodeType == other.nodeType);
    }
}