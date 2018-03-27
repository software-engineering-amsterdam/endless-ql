package GUI.Controller;

import QLExceptions.SyntaxException;
import QLExceptions.TypeException;

public interface RefreshListener {
    void refreshQuestions() throws SyntaxException, TypeException;
}
