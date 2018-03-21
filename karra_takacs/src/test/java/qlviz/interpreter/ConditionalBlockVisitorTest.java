package qlviz.interpreter;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import qlviz.QLVisitor;
import qlviz.QLParser;
import qlviz.model.booleanExpressions.BooleanExpression;
import qlviz.model.ConditionalBlock;
import qlviz.model.QuestionBlock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ConditionalBlockVisitorTest {

    @Test
    public void testVisitConditionalBlock() {
        // Arrange
        QLParser.ConditionalBlockContext conditionalContextMock = mock(QLParser.ConditionalBlockContext.class);
        QLParser.BooleanExpressionContext booleanExpressionContextMock = mock(QLParser.BooleanExpressionContext.class);
        QLParser.QuestionBlockContext questionBlockContextMock = mock(QLParser.QuestionBlockContext.class);
        when(conditionalContextMock.booleanExpression()).thenReturn(booleanExpressionContextMock);
        when(conditionalContextMock.questionBlock()).thenReturn(Lists.newArrayList(questionBlockContextMock, questionBlockContextMock));

        QLVisitor<BooleanExpression> booleanVisitorMock = (QLVisitor<BooleanExpression>) mock(QLVisitor.class);
        QLVisitor<QuestionBlock> questionBlockVisitor = (QLVisitor<QuestionBlock>) mock(QLVisitor.class);
        when(booleanVisitorMock.visitBooleanExpression(booleanExpressionContextMock)).thenReturn(mock(BooleanExpression.class));

        ConditionalBlockVisitor visitor = new ConditionalBlockVisitor(booleanVisitorMock, questionBlockVisitor);

        // Act
        ConditionalBlock result = visitor.visitConditionalBlock(conditionalContextMock);

        // Assert
        Assert.assertEquals(2, result.getQuestionBlocks().size());
        Assert.assertNotNull(result.getCondition());
        verify(booleanVisitorMock).visitBooleanExpression(booleanExpressionContextMock);
    }
}