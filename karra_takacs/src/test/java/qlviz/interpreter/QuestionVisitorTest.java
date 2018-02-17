package qlviz.interpreter;

import org.junit.Assert;
import org.junit.Test;
import qlviz.QLBaseVisitor;
import qlviz.QLParser;
import qlviz.model.Question;
import qlviz.model.QuestionType;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionVisitorTest {

    @Test
    public void TestQuestionIdentifier() {
        // Arrange
        final String testIdentifier = "test_identifier";
        QLBaseVisitor<QuestionType> typeVisitorMock = mock(QLBaseVisitor.class);
        QLParser.QuestionContext contextMock = mock(QLParser.QuestionContext.class);
        QLParser.QuestionNameContext nameContext = mock(QLParser.QuestionNameContext.class);
        QLParser.QuestionTextContext textContextMock = mock(QLParser.QuestionTextContext.class);

        when(contextMock.questionName()).thenReturn(nameContext);
        when(contextMock.questionText()).thenReturn(textContextMock);
        when(nameContext.getText()).thenReturn(testIdentifier);

        QuestionVisitor visitor = new QuestionVisitor(typeVisitorMock);

        // Act
        Question result = visitor.visitQuestion(contextMock);

        // Assert
        Assert.assertEquals(testIdentifier, result.getName());
    }
}
