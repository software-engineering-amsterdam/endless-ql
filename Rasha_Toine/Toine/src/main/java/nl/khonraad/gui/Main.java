package nl.khonraad.gui;

import java.io.IOException;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import nl.khonraad.cdi.producers.SourcePathProvider;

public class Main {

    public static void main( String[] args ) throws IOException {

        SeContainer container = SeContainerInitializer.newInstance().initialize();

        container.select( SourcePathProvider.class ).get().setSourcePathQL( "/Kipling.ql" );

        container.select( SourcePathProvider.class ).get().setSourcePathQLS( "/CollegeExample.qls" );

        container.select( MainView.class ).get().run();

    }
}