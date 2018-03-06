package nl.uva.js.qlparser.ui;

import nl.uva.js.qlparser.models.expressions.Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIBuilder {
    public static Frame getGUI(Form form) {
        Frame frame = new Frame(form.getHumanizedName());
        frame.setLayout(new BoxLayout(frame, BoxLayout.PAGE_AXIS));
        frame.setVisible(true);

        form.getComponents().forEach(frame::add);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        return frame;
    }


}

