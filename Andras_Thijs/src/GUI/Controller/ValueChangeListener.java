package GUI.Controller;

import QLExceptions.SyntaxException;
import QLExceptions.TypeException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Listens to changes in the forms and send an update signal to the Controller
 */
public class ValueChangeListener implements ActionListener {

    private final RefreshListener refreshListener;


    public ValueChangeListener(RefreshListener listener){
        this.refreshListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(refreshListener != null) {
            try {
                refreshListener.refreshQuestions();
            } catch(SyntaxException | TypeException e1) {
                e1.printStackTrace();
            }
        }
    }
}
