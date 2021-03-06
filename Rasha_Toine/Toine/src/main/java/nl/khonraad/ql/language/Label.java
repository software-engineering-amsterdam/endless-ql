package nl.khonraad.ql.language;

import java.util.Objects;

public class Label {

    private String string;

    public Label( String string ) {
        this.string = string;
    }

    public String string() {
        return string;
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
        final Label other = (Label) object;
        return Objects.equals( this.string, other.string );
    }
}