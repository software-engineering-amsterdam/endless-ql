package gui.observers;

import gui.model.GUIQuestion;

public class GUIQuestionObserver {
    private GUIQuestion guiQuestion;

    public GUIQuestionObserver(GUIQuestion guiQuestion) {
        this.guiQuestion = guiQuestion;
        this.guiQuestion.attach(this);
    }

    public void update() {
        System.out.println(this.guiQuestion.name + " updated to: " + this.guiQuestion.value);
    }
}
