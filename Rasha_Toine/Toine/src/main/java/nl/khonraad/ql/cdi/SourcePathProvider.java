package nl.khonraad.ql.cdi;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped 
public class SourcePathProvider {

    String sourcePathQL;
    String sourcePathQLS;

    public String getSourcePathQL() {
        return sourcePathQL;
    }

    public void setSourcePathQL( String path ) {
        this.sourcePathQL = path;
    }

    public String getSourcePathQLS() {
        return sourcePathQLS;
    }
    
    public void setSourcePathQLS( String path ) {
        this.sourcePathQLS = path;
    }
}