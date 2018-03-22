package qlviz.style;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Assert;
import org.junit.Test;
import qlviz.QLSBaseVisitor;
import qlviz.QLSParser;
import qlviz.interpreter.style.SectionVisitor;
import qlviz.model.style.DefaultWidgetDeclaration;
import qlviz.model.style.Question;
import qlviz.model.style.Section;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class SectionVisitorTest {

    @Test
    public void testName() {
        // Arrange
        final String name = "test_name";
        QLSParser.SectionContext contextMock = mock(QLSParser.SectionContext.class);
        TerminalNode stringMock = mock(TerminalNode.class);
        when(contextMock.question()).thenReturn(new ArrayList<>());
        when(contextMock.defaultWidgetDeclaration()).thenReturn(new ArrayList<>());
        when(contextMock.STRING()).thenReturn(stringMock);
        when(stringMock.getText()).thenReturn(name);
        Question questionMock = mock(Question.class);
        DefaultWidgetDeclaration widgetMock = mock(DefaultWidgetDeclaration.class);

        QLSBaseVisitor<Question> questionVisitorMock = mock(QLSBaseVisitor.class);
        QLSBaseVisitor<DefaultWidgetDeclaration> widgetVisitorMock = mock(QLSBaseVisitor.class);
        when(questionVisitorMock.visitQuestion(any())).thenReturn(questionMock);
        when(widgetVisitorMock.visitDefaultWidgetDeclaration(any())).thenReturn(widgetMock);

        SectionVisitor sectionVisitor = new SectionVisitor(questionVisitorMock, widgetVisitorMock);

        // Act
        Section result = sectionVisitor.visitSection(contextMock);

        // Assert
        Assert.assertEquals(name, result.getName());
    }

    @Test
    public void testQuestions() {
        // Arrange
        final String name = "test_name";
        QLSParser.QuestionContext questionContextMock = mock(QLSParser.QuestionContext.class);
        QLSParser.SectionContext contextMock = mock(QLSParser.SectionContext.class);
        TerminalNode stringMock = mock(TerminalNode.class);
        when(contextMock.question()).thenReturn(List.of(questionContextMock, questionContextMock));
        when(contextMock.defaultWidgetDeclaration()).thenReturn(new ArrayList<>());
        when(contextMock.STRING()).thenReturn(stringMock);
        when(stringMock.getText()).thenReturn(name);
        Question questionMock = mock(Question.class);
        DefaultWidgetDeclaration widgetMock = mock(DefaultWidgetDeclaration.class);

        QLSBaseVisitor<Question> questionVisitorMock = mock(QLSBaseVisitor.class);
        QLSBaseVisitor<DefaultWidgetDeclaration> widgetVisitorMock = mock(QLSBaseVisitor.class);
        when(questionVisitorMock.visitQuestion(any())).thenReturn(questionMock);
        when(widgetVisitorMock.visitDefaultWidgetDeclaration(any())).thenReturn(widgetMock);

        SectionVisitor sectionVisitor = new SectionVisitor(questionVisitorMock, widgetVisitorMock);

        // Act
        Section result = sectionVisitor.visitSection(contextMock);

        // Assert
        Assert.assertEquals(2, result.getQuestions().size());
        Assert.assertTrue(result.getQuestions().stream().noneMatch(Objects::isNull));
    }

    @Test
    public void testDefaultWidgets() {
        // Arrange
        final String name = "test_name";
        QLSParser.DefaultWidgetDeclarationContext widgetContext = mock(QLSParser.DefaultWidgetDeclarationContext.class);
        QLSParser.SectionContext contextMock = mock(QLSParser.SectionContext.class);
        TerminalNode stringMock = mock(TerminalNode.class);
        when(contextMock.question()).thenReturn(new ArrayList<>());
        when(contextMock.defaultWidgetDeclaration()).thenReturn(List.of(widgetContext, widgetContext));
        when(contextMock.STRING()).thenReturn(stringMock);
        when(stringMock.getText()).thenReturn(name);
        Question questionMock = mock(Question.class);
        DefaultWidgetDeclaration widgetMock = mock(DefaultWidgetDeclaration.class);

        QLSBaseVisitor<Question> questionVisitorMock = mock(QLSBaseVisitor.class);
        QLSBaseVisitor<DefaultWidgetDeclaration> widgetVisitorMock = mock(QLSBaseVisitor.class);
        when(questionVisitorMock.visitQuestion(any())).thenReturn(questionMock);
        when(widgetVisitorMock.visitDefaultWidgetDeclaration(any())).thenReturn(widgetMock);

        SectionVisitor sectionVisitor = new SectionVisitor(questionVisitorMock, widgetVisitorMock);

        // Act
        Section result = sectionVisitor.visitSection(contextMock);

        // Assert
        Assert.assertEquals(2, result.getDefaultWidgetDeclarations().size());
        Assert.assertTrue(result.getDefaultWidgetDeclarations().stream().noneMatch(Objects::isNull));
    }
}
