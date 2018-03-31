package nl.khonraad.ql;

import java.io.IOException;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import nl.khonraad.ql.cdi.SourcePathProvider;

public class Main {

    public static void main( String[] args ) throws IOException {

        SeContainer container = SeContainerInitializer.newInstance().initialize();

        container.select( SourcePathProvider.class ).get().setSourcePath( "/Gui" );
        container.select( Visualizer.class ).get().run();


    }

}