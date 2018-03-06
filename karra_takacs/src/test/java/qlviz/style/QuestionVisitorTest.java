package qlviz.style;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Assert;
import org.junit.Test;
import qlviz.QLSBaseVisitor;
import qlviz.QLSParser;
import qlviz.interpreter.style.QuestionVisitor;
import qlviz.model.style.Question;
import qlviz.model.style.WidgetType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyVararg;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionVisitorTest {

    @Test
    public void testName() {
        // Arrange
        final String name = "test_name";
        QLSParser.QuestionContext contextMock = mock(QLSParser.QuestionContext.class);
        QLSParser.WidgetTypeContext widgetTypeContextMock = mock(QLSParser.WidgetTypeContext.class);
        TerminalNode identifierMock = mock(TerminalNode.class);

        when(contextMock.IDENTIFIER()).thenReturn(identifierMock);
        when(identifierMock.getText()).thenReturn(name);

        QLSBaseVisitor<WidgetType> widgetVisitorMock = mock(QLSBaseVisitor.class);

        QuestionVisitor questionVisitor = new QuestionVisitor(widgetVisitorMock);

        // Act
        Question result = questionVisitor.visitQuestion(contextMock);

        // Assert
        Assert.assertEquals(name, result.getName());
    }

    @Test
    public void testNoWidget() {
        // Arrange
        final String name = "test_name";
        QLSParser.QuestionContext contextMock = mock(QLSParser.QuestionContext.class);
        QLSParser.WidgetTypeContext widgetTypeContextMock = mock(QLSParser.WidgetTypeContext.class);
        TerminalNode identifierMock = mock(TerminalNode.class);

        when(contextMock.IDENTIFIER()).thenReturn(identifierMock);
        when(identifierMock.getText()).thenReturn(name);

        QLSBaseVisitor<WidgetType> widgetVisitorMock = mock(QLSBaseVisitor.class);

        QuestionVisitor questionVisitor = new QuestionVisitor(widgetVisitorMock);

        // Act
        Question result = questionVisitor.visitQuestion(contextMock);

        // Assert
        Assert.assertEquals(null, result.getWidgetType());
    }

    @Test
    public void testHasWidget() {
        // Arrange
        final String name = "test_name";
        QLSParser.QuestionContext contextMock = mock(QLSParser.QuestionContext.class);
        QLSParser.WidgetTypeContext widgetTypeContextMock = mock(QLSParser.WidgetTypeContext.class);
        TerminalNode identifierMock = mock(TerminalNode.class);

        when(contextMock.IDENTIFIER()).thenReturn(identifierMock);
        when(identifierMock.getText()).thenReturn(name);
        when(contextMock.widgetType()).thenReturn(widgetTypeContextMock);

        QLSBaseVisitor<WidgetType> widgetVisitorMock = mock(QLSBaseVisitor.class);
        when(widgetVisitorMock.visitWidgetType(any())).thenReturn(mock(WidgetType.class));

        QuestionVisitor questionVisitor = new QuestionVisitor(widgetVisitorMock);

        // Act
        Question result = questionVisitor.visitQuestion(contextMock);

        // Assert
        Assert.assertNotNull(result.getWidgetType());
    }

}
