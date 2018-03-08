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

    public QLFrame() {

        // used for scaling on 4K monitors
        setDefaultSize(36);

//        addQuestion("What is your name?");
//        addQuestion("Do you own a house?");
//        addQuestion("Do you have children?");
//
//        JPasswordField ps=new JPasswordField(10);
//
//        JButton but=new JButton();
//        but.setText("Submit");
//        add(ps);
//        add(but);
//
//
//        but.addActionListener( new ActionListener(){
//            public void actionPerformed( ActionEvent e){
////                l.setText("Button clicked");
//                System.out.println("Clicked on the button");
//            }
//        });
//
////        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        setLayout(new FlowLayout());
//        setSize(1000,1600);
//        setVisible(true);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

//    public void addQuestion(String question) {
//        JLabel label = new JLabel();
//        label.setText(question);
//        JTextField txt = new JTextField();
//        txt.setText("Dit is een test");
//
//        add(label);
//        add(txt);
//    }

    public static void setDefaultSize(int size) {

        Set<Object> keySet = UIManager.getLookAndFeelDefaults().keySet();
        Object[] keys = keySet.toArray(new Object[keySet.size()]);

        for (Object key : keys) {

            if (key != null && key.toString().toLowerCase().contains("font")) {
                Font font = UIManager.getDefaults().getFont(key);
                if (font != null) {
                    font = font.deriveFont((float)size);
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
