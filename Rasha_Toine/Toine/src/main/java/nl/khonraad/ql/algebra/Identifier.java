package nl.khonraad.ql.algebra;

import java.util.Objects;

public class Identifier {

    private String string;

    public Identifier( String string ) {
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
        final Identifier other = (Identifier) object;
        return Objects.equals( this.string, other.string );
    }
}