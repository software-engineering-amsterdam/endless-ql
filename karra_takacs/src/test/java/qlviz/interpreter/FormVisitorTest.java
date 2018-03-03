package qlviz.interpreter;

import com.google.common.collect.Lists;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Assert;
import org.junit.Test;
import qlviz.QLParser;
import qlviz.QLVisitor;
import qlviz.model.Form;
import qlviz.model.QuestionBlock;

import static org.mockito.Mockito.*;

public class FormVisitorTest {

    @Test
    public void TestAddsQuestion() {
        // Arrange
        QLVisitor<QuestionBlock> visitorMock = (QLVisitor<QuestionBlock>) mock(QLVisitor.class);
        QLParser.FormContext contextMock = (QLParser.FormContext)mock(QLParser.FormContext.class);
        QLParser.QuestionBlockContext questionContextMock = mock(QLParser.QuestionBlockContext.class);

        when(contextMock.questionBlock()).thenReturn(Lists.newArrayList(questionContextMock));

        FormVisitor listener = new FormVisitor(visitorMock);

        // Act
        Form result = listener.visitForm(contextMock);

        // Assert
        Assert.assertEquals(1, result.getQuestions().size());
        verify(visitorMock).visitQuestionBlock(questionContextMock);
    }
}
