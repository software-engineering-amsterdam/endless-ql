package qlviz.interpreter;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import qlviz.QLBaseVisitor;
import qlviz.QLParser;
import qlviz.model.BooleanExpression;
import qlviz.model.ConditionalBlock;
import qlviz.model.QuestionBlock;

import static org.junit.Assert.*;
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

        QLBaseVisitor<BooleanExpression> booleanVisitorMock = (QLBaseVisitor<BooleanExpression>) mock(QLBaseVisitor.class);
        QLBaseVisitor<QuestionBlock> questionBlockVisitor = (QLBaseVisitor<QuestionBlock>) mock(QLBaseVisitor.class);
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