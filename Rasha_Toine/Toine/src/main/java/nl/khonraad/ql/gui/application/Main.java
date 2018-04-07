package nl.khonraad.ql.gui.application;

import java.io.IOException;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import nl.khonraad.ql.cdi.SourcePathProvider;

public class Main {

    public static void main( String[] args ) throws IOException {

        SeContainer container = SeContainerInitializer.newInstance().initialize();

        container.select( SourcePathProvider.class ).get().setSourcePathQL( "/Kipling.ql" );

        container.select( SourcePathProvider.class ).get().setSourcePathQLS( "/CollegeExample.qls" );

        container.select( Visualizer.class ).get().run();

    }
}