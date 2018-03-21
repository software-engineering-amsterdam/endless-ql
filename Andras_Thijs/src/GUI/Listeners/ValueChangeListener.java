package GUI.Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ValueChangeListener implements ActionListener {

    private RefreshListener refreshListener;

    public ValueChangeListener(RefreshListener listener){
        this.refreshListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (refreshListener != null) refreshListener.refreshQuestions();
    }
}
