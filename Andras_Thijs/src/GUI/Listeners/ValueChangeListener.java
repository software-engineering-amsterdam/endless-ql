package GUI.Listeners;

import QLExceptions.SyntaxException;
import QLExceptions.TypeException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ValueChangeListener implements ActionListener {

    private RefreshListener refreshListener;

    public ValueChangeListener(RefreshListener listener){
        this.refreshListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (refreshListener != null) {
            try {
                refreshListener.refreshQuestions();
            } catch (SyntaxException e1) {
                e1.printStackTrace();
            } catch (TypeException e1) {
                e1.printStackTrace();
            }
        }
    }
}
