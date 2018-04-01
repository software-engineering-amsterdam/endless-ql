package nl.khonraad.ql.algebra;

public class Identifier implements StringAble {

    private String string;

    public Identifier(String string) {
        
        this.string = string;
    }

    @Override
    public String string() {

        return string;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((string == null) ? 0 : string.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        Identifier other = (Identifier) obj;
        if ( string == null ) {
            if ( other.string != null ) {
                return false;
            }
        } else
            if ( !string.equals( other.string ) ) {
                return false;
            }
        return true;
    }
}