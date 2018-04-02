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


    /**
     * Adds a listener to this event
     * @param listener
     */
    public ValueChangeListener(RefreshListener listener){
        this.refreshListener = listener;
    }

    /**
     * Signals the listeners that an action has been performed
     * @param e
     */
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
