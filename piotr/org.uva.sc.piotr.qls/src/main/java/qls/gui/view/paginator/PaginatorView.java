package qls.gui.view.paginator;

import qls.gui.model.Paginator;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PaginatorView extends JPanel {

    public PaginatorView(Paginator paginator, JPanel pagePanel) {
        JButton buttonPrevious = new JButton("previous");
        JButton buttonNext = new JButton("next");
        buttonNext.setEnabled(paginator.hasNext());
        buttonPrevious.setEnabled(paginator.hasPrevious());
        this.add(buttonPrevious);
        this.add(buttonNext);

        JLabel pagesLabel = new JLabel("Page " + paginator.getCurrentPageNumber() + " of " + paginator.getTotalNumberPages());
        this.add(pagesLabel);

        buttonNext.addActionListener((ActionEvent e) -> {
            paginator.moveToNext();
            buttonNext.setEnabled(paginator.hasNext());
            buttonPrevious.setEnabled(paginator.hasPrevious());
            pagesLabel.setText("Page " + paginator.getCurrentPageNumber() + " of " + paginator.getTotalNumberPages());
            pagePanel.removeAll();
            pagePanel.add(paginator.getCurrentPage());
            pagePanel.revalidate();
            pagePanel.repaint();
        });

        buttonPrevious.addActionListener((ActionEvent e) -> {
            paginator.moveToPrevious();
            pagePanel.removeAll();
            pagePanel.add(paginator.getCurrentPage());
            buttonNext.setEnabled(paginator.hasNext());
            buttonPrevious.setEnabled(paginator.hasPrevious());
            pagesLabel.setText("Page " + paginator.getCurrentPageNumber() + " of " + paginator.getTotalNumberPages());
            pagePanel.revalidate();
            pagePanel.repaint();

        });
    }
}
