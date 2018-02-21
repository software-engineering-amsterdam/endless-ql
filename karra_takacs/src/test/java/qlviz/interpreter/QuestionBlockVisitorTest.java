package qlviz.interpreter;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import qlviz.QLParser;
import qlviz.model.QuestionBlock;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionBlockVisitorTest {

    @Test
    public void TestAddsQuestions() {
        // Arrange
        QuestionVisitor questionVisitor = mock(QuestionVisitor.class);
        ConditionalBlockVisitor conditionalBlockVisitor = mock(ConditionalBlockVisitor.class);
        QLParser.QuestionBlockContext contextMock = mock(QLParser.QuestionBlockContext.class);
        QLParser.QuestionContext questionContextMock = mock(QLParser.QuestionContext.class);
        when(contextMock.conditionalBlock()).thenReturn(new ArrayList<>());
        when(contextMock.question()).thenReturn(Lists.newArrayList(questionContextMock, questionContextMock));

        QuestionBlockVisitor visitor = new QuestionBlockVisitor(questionVisitor, q -> conditionalBlockVisitor);

        // Act
        QuestionBlock questionBlock = visitor.visitQuestionBlock(contextMock);

        // Assert
        Assert.assertEquals(2, questionBlock.getQuestions().size());
    }

   @Test
    public void TestAddConditionalBlock() {
        // Arrange
        QuestionVisitor questionVisitor = mock(QuestionVisitor.class);
        ConditionalBlockVisitor conditionalBlockVisitor = mock(ConditionalBlockVisitor.class);
        QLParser.QuestionBlockContext contextMock = mock(QLParser.QuestionBlockContext.class);
        QLParser.ConditionalBlockContext conditionalContextBlock = mock(QLParser.ConditionalBlockContext.class);

        when(contextMock.conditionalBlock()).thenReturn(Lists.newArrayList(conditionalContextBlock));

        QuestionBlockVisitor visitor = new QuestionBlockVisitor(questionVisitor, q -> conditionalBlockVisitor);

        // Act
        QuestionBlock questionBlock = visitor.visitQuestionBlock(contextMock);

        // Assert
        Assert.assertEquals(0, questionBlock.getQuestions().size());
        Assert.assertEquals(1, questionBlock.getBlocks().size());
    }
}
