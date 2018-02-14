package main.interpreter;

import org.junit.Assert;
import org.junit.Test;
import qlviz.QLParser;
import qlviz.interpreter.FormListener;

public class FormListenerTest {

    @Test
    public void TestAddsQuestion() {
        // Arrange
        FormListener listener = new FormListener();

        // Act
        listener.enterQuestion(new QLParser.QuestionContext(null, 0));

        // Assert
        Assert.assertFalse(listener.getQuestions().isEmpty());
    }
}
