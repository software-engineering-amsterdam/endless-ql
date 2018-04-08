package nl.khonraad.ql.algebra.values;

import java.util.Objects;

class MutableValue {

    String string;

    MutableValue( String string ) {
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
        final MutableValue other = (MutableValue) object;
        return Objects.equals( this.string, other.string );
    }

}