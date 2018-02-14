package main.interpreter;

import com.google.common.collect.Lists;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import qlviz.QLBaseVisitor;
import qlviz.QLParser;
import qlviz.QLVisitor;
import qlviz.interpreter.FormVisitor;
import qlviz.model.Form;
import qlviz.model.Question;

import java.util.LinkedList;

import static org.mockito.Mockito.*;

public class FormVisitorTest {

    @Test
    public void TestAddsQuestion() {
        // Arrange
        QLVisitor<Question> visitorMock = (QLVisitor<Question>) mock(QLVisitor.class);
        QLParser.FormContext contextMock = (QLParser.FormContext)mock(QLParser.FormContext.class);
        QLParser.QuestionContext questionContextMock = mock(QLParser.QuestionContext.class);

        when(contextMock.question()).thenReturn(Lists.newArrayList(questionContextMock));

        FormVisitor listener = new FormVisitor(visitorMock);

        // Act
        Form result = listener.visitForm(contextMock);

        // Assert
        Assert.assertEquals(1, result.getQuestions().size());
        verify(visitorMock).visitQuestion(questionContextMock);
    }
}
