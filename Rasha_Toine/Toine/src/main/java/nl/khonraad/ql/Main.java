package nl.khonraad.ql;

import java.io.IOException;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import nl.khonraad.ql.cdisupport.QLSource;

public class Main {

    public static void main( String[] args ) throws IOException {

        SeContainer container = SeContainerInitializer.newInstance().initialize();

        container.select( QLSource.class ).get().setPath( "/Gui" );
        container.select( Visualizer.class ).get().run();

    }

}