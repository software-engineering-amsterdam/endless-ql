package qlviz.style;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Assert;
import org.junit.Test;
import qlviz.QLSBaseVisitor;
import qlviz.QLSParser;
import qlviz.interpreter.style.QuestionVisitor;
import qlviz.model.style.Question;
import qlviz.model.style.Widget;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionVisitorTest {

    @Test
    public void testName() {
        // Arrange
        final String name = "test_name";
        QLSParser.QuestionContext contextMock = mock(QLSParser.QuestionContext.class);
        QLSParser.WidgetContext widgetContextMock = mock(QLSParser.WidgetContext.class);
        TerminalNode identifierMock = mock(TerminalNode.class);

        when(contextMock.IDENTIFIER()).thenReturn(identifierMock);
        when(identifierMock.getText()).thenReturn(name);

        QLSBaseVisitor<Widget> widgetVisitorMock = mock(QLSBaseVisitor.class);

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
        QLSParser.WidgetContext widgetContextMock = mock(QLSParser.WidgetContext.class);
        TerminalNode identifierMock = mock(TerminalNode.class);

        when(contextMock.IDENTIFIER()).thenReturn(identifierMock);
        when(identifierMock.getText()).thenReturn(name);

        QLSBaseVisitor<Widget> widgetVisitorMock = mock(QLSBaseVisitor.class);

        QuestionVisitor questionVisitor = new QuestionVisitor(widgetVisitorMock);

        // Act
        Question result = questionVisitor.visitQuestion(contextMock);

        // Assert
        Assert.assertEquals(null, result.getWidget());
    }

    @Test
    public void testHasWidget() {
        // Arrange
        final String name = "test_name";
        QLSParser.QuestionContext contextMock = mock(QLSParser.QuestionContext.class);
        QLSParser.WidgetContext widgetContextMock = mock(QLSParser.WidgetContext.class);
        TerminalNode identifierMock = mock(TerminalNode.class);

        when(contextMock.IDENTIFIER()).thenReturn(identifierMock);
        when(identifierMock.getText()).thenReturn(name);
        when(contextMock.widget()).thenReturn(widgetContextMock);

        QLSBaseVisitor<Widget> widgetVisitorMock = mock(QLSBaseVisitor.class);
        when(widgetVisitorMock.visitWidget(any())).thenReturn(mock(Widget.class));

        QuestionVisitor questionVisitor = new QuestionVisitor(widgetVisitorMock);

        // Act
        Question result = questionVisitor.visitQuestion(contextMock);

        // Assert
        Assert.assertNotNull(result.getWidget());
    }

}
