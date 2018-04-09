package nl.khonraad.ql.algebra.values;

import java.util.Objects;

class Storage {

    String string;

    Storage( String string ) {
        this.string = string;
    }

    String string() {
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
        final Storage other = (Storage) object;
        return Objects.equals( this.string, other.string );
    }

}