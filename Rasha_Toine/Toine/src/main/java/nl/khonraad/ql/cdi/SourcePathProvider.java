package nl.khonraad.ql.cdi;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped 
public class SourcePathProvider {

    String sourcePath;

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath( String path ) {
        this.sourcePath = path;
    }

}