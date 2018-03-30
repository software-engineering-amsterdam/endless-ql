package ql.gui.view;

import javax.swing.*;

public final class ErrorMessageView {

    public static void showErrorDialog(WindowView windowView, String title, String message) {
        JOptionPane.showMessageDialog(windowView.getFrame(), message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void showWarningDialog(WindowView windowView, String title, String message) {
        JOptionPane.showMessageDialog(windowView.getFrame(), message, title, JOptionPane.WARNING_MESSAGE);
    }
}
