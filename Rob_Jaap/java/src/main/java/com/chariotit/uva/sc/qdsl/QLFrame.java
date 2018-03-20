package com.chariotit.uva.sc.qdsl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Set;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class QLFrame extends JFrame {

    public QLFrame(String s) throws HeadlessException {

        super(s);

        setDefaultSize(36);
    }

    public static void setDefaultSize(int size) {

        Set<Object> keySet = UIManager.getLookAndFeelDefaults().keySet();
        Object[] keys = keySet.toArray(new Object[keySet.size()]);

        for (Object key : keys) {

            if (key != null && key.toString().toLowerCase().contains("font")) {
                Font font = UIManager.getDefaults().getFont(key);
                if (font != null) {
                    font = font.deriveFont((float) size);
                    UIManager.put(key, font);
                }
            }
        }
    }

    public void windowActivated(WindowEvent event) {
        System.out.println("The window has been activated");
    }

    public void windowClosed(WindowEvent event) {
        System.out.println("The window has been closed");
    }

    public void windowClosing(WindowEvent event) {
        System.out.println("About to close the window");
    }

    public void windowDeactivated(WindowEvent event) {
        System.out.println("The window has been deactivated");
    }

    public void windowDeiconified(WindowEvent event) {
        System.out.println("The window has been restored");
    }

    public void windowIconified(WindowEvent event) {
        System.out.println("The window has been minimized");
    }

    public void windowOpened(WindowEvent event) {
        System.out.println("The window has been opened");
    }

}
