package qlviz.style;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Assert;
import org.junit.Test;
import qlviz.QLSBaseVisitor;
import qlviz.QLSParser;
import qlviz.interpreter.style.StylesheetVisitor;
import qlviz.model.style.Page;
import qlviz.model.style.Stylesheet;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StylesheetVisitorTest {

    @Test
    public void testVisitStylesheet() {
        // Arrange
        QLSBaseVisitor<Page> pageVisitorMock = mock(QLSBaseVisitor.class);
        QLSParser.StylesheetContext contextMock = mock(QLSParser.StylesheetContext.class);
        StylesheetVisitor stylesheetVisitor = new StylesheetVisitor(pageVisitorMock);
        TerminalNode identifierMock = mock(TerminalNode.class);

        when(contextMock.IDENTIFIER()).thenReturn(identifierMock);
        when(contextMock.page()).thenReturn(List.of(mock(QLSParser.PageContext.class), mock(QLSParser.PageContext.class)));
        when(pageVisitorMock.visitPage(any())).thenReturn(mock(Page.class));

        // Act
        Stylesheet result = stylesheetVisitor.visitStylesheet(contextMock);

        // Assert
        Assert.assertEquals(2, result.getPages().size());
    }

    @Test
    public void testName() {
        // Arrange
        final String name = "test_name";
        QLSBaseVisitor<Page> pageVisitorMock = mock(QLSBaseVisitor.class);
        QLSParser.StylesheetContext contextMock = mock(QLSParser.StylesheetContext.class);
        StylesheetVisitor stylesheetVisitor = new StylesheetVisitor(pageVisitorMock);
        TerminalNode identifierMock = mock(TerminalNode.class);

        when(contextMock.page()).thenReturn(List.of(mock(QLSParser.PageContext.class), mock(QLSParser.PageContext.class)));
        when(contextMock.IDENTIFIER()).thenReturn(identifierMock);
        when(pageVisitorMock.visitPage(any())).thenReturn(mock(Page.class));
        when(identifierMock.getText()).thenReturn(name);

        // Act
        Stylesheet result = stylesheetVisitor.visitStylesheet(contextMock);

        // Assert
        Assert.assertEquals(name, result.getName());
    }
}
