package nl.khonraad.ql.algebra;

import java.util.Objects;

public class Label implements StringAble {

    private String string;

    public Label( String string ) {
        this.string = string;
    }

    @Override
    public String string() {
        return string;
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.string );
    }

    @Override
    public boolean equals( Object object ) {

        if ( object == null || getClass() != object.getClass() )
            return false;

        final Label other = (Label) object;

        return Objects.equals( this.string, other.string );
    }
}