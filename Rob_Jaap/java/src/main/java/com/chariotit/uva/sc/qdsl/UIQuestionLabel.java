package com.chariotit.uva.sc.qdsl;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

public class UIQuestionLabel extends JComponent{

    public void paint(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(30, 30, 100, 100);
    }

}
