package qlviz.style;

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

        when(contextMock.page()).thenReturn(List.of(mock(QLSParser.PageContext.class), mock(QLSParser.PageContext.class)));
        when(pageVisitorMock.visitPage(any())).thenReturn(mock(Page.class));

        // Act
        Stylesheet result = stylesheetVisitor.visitStylesheet(contextMock);

        // Assert
        Assert.assertEquals(2, result.getPages().size());
    }
}
