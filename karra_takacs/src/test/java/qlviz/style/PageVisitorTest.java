package qlviz.style;

        import org.antlr.v4.runtime.tree.TerminalNode;
        import org.junit.Assert;
        import org.junit.Test;
        import qlviz.QLSBaseVisitor;
        import qlviz.QLSParser;
        import qlviz.interpreter.style.PageVisitor;
        import qlviz.interpreter.style.StylesheetVisitor;
        import qlviz.model.style.Page;
        import qlviz.model.style.Section;
        import qlviz.model.style.Stylesheet;

        import java.util.List;

        import static org.mockito.ArgumentMatchers.any;
        import static org.mockito.Mockito.mock;
        import static org.mockito.Mockito.when;

public class PageVisitorTest {

    @Test
    public void testVisitPage() {
        // Arrange
        QLSBaseVisitor<Section> sectionVisitorMock = mock(QLSBaseVisitor.class);
        QLSParser.PageContext contextMock = mock(QLSParser.PageContext.class);
        PageVisitor pageVisitor = new PageVisitor(sectionVisitorMock);
        TerminalNode identifierMock = mock(TerminalNode.class);

        when(contextMock.IDENTIFIER()).thenReturn(identifierMock);
        when(contextMock.section()).thenReturn(
                List.of(mock(QLSParser.SectionContext.class), mock(QLSParser.SectionContext.class)));
        when(sectionVisitorMock.visitPage(any())).thenReturn(mock(Section.class));

        // Act
        Page result = pageVisitor.visitPage(contextMock);

        // Assert
        Assert.assertEquals(2, result.getSections().size());
    }


    @Test
    public void testName() {
        // Arrange
        final String name = "test_name";
        QLSBaseVisitor<Section> sectionVisitorMock = mock(QLSBaseVisitor.class);
        QLSParser.PageContext contextMock = mock(QLSParser.PageContext.class);
        PageVisitor pageVisitor = new PageVisitor(sectionVisitorMock);
        TerminalNode identifierMock = mock(TerminalNode.class);
        when(contextMock.IDENTIFIER()).thenReturn(identifierMock);
        when(contextMock.section()).thenReturn(
                List.of(mock(QLSParser.SectionContext.class), mock(QLSParser.SectionContext.class)));
        when(sectionVisitorMock.visitPage(any())).thenReturn(mock(Section.class));
        when(identifierMock.getText()).thenReturn(name);

        // Act
        Page result = pageVisitor.visitPage(contextMock);

        // Assert
        Assert.assertEquals(name, result.getName());
    }
}
