package nl.khonraad.qls.language;

import java.util.Objects;

import nl.khonraad.qls.language.StyleTree.StyleType;

public class Style {

    private StyleType styleType;
    private String    string;

    public Style( StyleType styleType, String string ) {
        this.styleType = styleType;
        this.string = string;
    }

    public String string() {
        return string;
    }

    public StyleType styleType() {
        return this.styleType;
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
        final Style other = (Style) object;
        return Objects.equals( this.string, other.string ) && (this.styleType == other.styleType);
    }
}