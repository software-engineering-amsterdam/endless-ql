// src https://stackoverflow.com/questions/16834523/communicating-between-components-in-swing-application
// http://www.oodesign.com/observer-pattern.html

public interface MessageListener {
    public void notify(Message msg);
}

public class MainWindow implements MessageListener {
    public void notify(Message msg) {
        // UI Action
    }
}

public class Client {
    private MessageListener listener;

    public void setMessageListener(MessageListener listener) {
        this.listener = listener;
    }
}
