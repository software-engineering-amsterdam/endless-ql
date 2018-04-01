package nl.khonraad.ql.algebra.value;

import java.util.Objects;

import nl.khonraad.ql.algebra.StringAble;

public class Storage implements StringAble {

    private String  string;
    
    public Storage( String string) {
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

        final Storage other = (Storage) object;

        return Objects.equals( this.string, other.string );
    }
}