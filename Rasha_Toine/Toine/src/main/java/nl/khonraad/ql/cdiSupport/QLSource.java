package nl.khonraad.ql.cdiSupport;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped 
public class QLSource {

    String path;

    public String getPath() {
        return path;
    }

    public void setPath( String path ) {
        this.path = path;
    }

}