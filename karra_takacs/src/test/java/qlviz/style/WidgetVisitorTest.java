package qlviz.style;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import qlviz.QLBaseVisitor;
import qlviz.QLSBaseVisitor;
import qlviz.QLSParser;
import qlviz.interpreter.style.WidgetTypeTranslator;
import qlviz.interpreter.style.WidgetVisitor;
import qlviz.model.style.Parameter;
import qlviz.model.style.Widget;
import qlviz.model.style.WidgetType;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WidgetVisitorTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private WidgetTypeTranslator translatorMock;

    @Mock
    private QLSBaseVisitor<Parameter> parameterVisitorMock;

    @Mock
    private Parameter parameterMock;

    @Mock
    private QLSParser.ParameterContext parameterContextMock;

    @Before
    public void setUp() {
        when(translatorMock.translate(any())).thenReturn(WidgetType.Dropdown);
        when(parameterVisitorMock.visitParameter(any())).thenReturn(parameterMock);
    }


    @Test
    public void testSimpleWidget() {
        // Arrange
        QLSParser.WidgetContext widgetContextMock = mock(QLSParser.WidgetContext.class);
        QLSParser.SimpleWidgetContext simpleWidgetContextMock = mock(QLSParser.SimpleWidgetContext.class);

        when(widgetContextMock.simpleWidget()).thenReturn(simpleWidgetContextMock);

        WidgetVisitor widgetVisitor = new WidgetVisitor(translatorMock, parameterVisitorMock);

        // Act
        Widget result = widgetVisitor.visitWidget(widgetContextMock);

        // Assert
        Assert.assertEquals(0, result.getParameters().size());
        Assert.assertEquals(WidgetType.Dropdown, result.getType());
    }

    @Test
    public void testParametrizedWidget() {
        // Arrange
        QLSParser.WidgetContext widgetContextMock = mock(QLSParser.WidgetContext.class);
        QLSParser.ParametrizedWidgetContext parametrizedWidgetContext = mock(QLSParser.ParametrizedWidgetContext.class);
        List<QLSParser.ParameterContext> parameterContexts = Lists.newArrayList(parameterContextMock, parameterContextMock);

        when(widgetContextMock.parametrizedWidget()).thenReturn(parametrizedWidgetContext);
        when(parametrizedWidgetContext.parameter()).thenReturn(parameterContexts);

        WidgetVisitor widgetVisitor = new WidgetVisitor(translatorMock, parameterVisitorMock);

        // Act
        Widget result = widgetVisitor.visitWidget(widgetContextMock);

        // Assert
        Assert.assertEquals(2, result.getParameters().size());
        Assert.assertEquals(WidgetType.Dropdown, result.getType());
    }

}
