package nl.uva.se.sc.niro.gui;

import nl.uva.se.sc.niro.model.Expressions.Answer;

public interface ModelUpdater {
    void updateModel(String questionId, Answer answer);
}
