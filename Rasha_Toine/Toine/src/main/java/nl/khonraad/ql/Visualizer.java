package nl.khonraad.ql;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.swing.JFrame;

import org.slf4j.Logger;

import nl.khonraad.ql.ast.data.Questionnaire;
import nl.khonraad.ql.gui.visuals.WidgetContainer;

@ApplicationScoped public class Visualizer {

    @Inject
    Logger          logger;

    @Inject
    Questionnaire   questionnaire;

    @Inject
    WidgetContainer widgetContainer;

    JFrame          canvas;

    public void eventListener( @Observes VisualizeEvent event ) {

        widgetContainer.visualize();

        canvas.validate();
        canvas.repaint();

    }

    public void run() {

        canvas = buildCanvas();

        widgetContainer.visualize();

        canvas.setVisible( true );

    }

    private JFrame buildCanvas() {

        JFrame jFrame = new JFrame();

        jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        jFrame.setTitle( getClass().getSimpleName() );

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int height = screenSize.height * 2 / 3;

        final double GOLDEN_RATIO = 1.61803398875;

        Double goldenHeight = height * GOLDEN_RATIO;

        int width = goldenHeight.intValue();

        jFrame.setSize( new Dimension( width, height ) );

        // Center to screen
        jFrame.setLocationRelativeTo( null );

        jFrame.add( widgetContainer, BorderLayout.NORTH );
        return jFrame;
    }

}