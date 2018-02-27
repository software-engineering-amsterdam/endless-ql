package qlviz.interpreter;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.Assert;
import org.junit.Test;
import qlviz.QLParser;
import qlviz.model.question.Question;
import qlviz.model.question.QuestionType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionVisitorTest {

    @Test
    public void TestQuestionIdentifier() {
        // Arrange
        final String testIdentifier = "test_identifier";
        NumericExpressionParser expressionVisitorMock = mock(NumericExpressionParser.class);
        QLParser.QuestionContext contextMock = mock(QLParser.QuestionContext.class);
        QLParser.QuestionNameContext nameContext = mock(QLParser.QuestionNameContext.class);
        QLParser.QuestionTextContext textContextMock = mock(QLParser.QuestionTextContext.class);
        QuestionTypeTranslator translatorMock = mock(QuestionTypeTranslator.class);

        when(contextMock.questionName()).thenReturn(nameContext);
        when(contextMock.questionText()).thenReturn(textContextMock);
        when(nameContext.getText()).thenReturn(testIdentifier);
        when(translatorMock.translate(null)).thenReturn(QuestionType.Integer);

        QuestionVisitor visitor = new QuestionVisitor(translatorMock, expressionVisitorMock);

        // Act
        Question result = visitor.visitQuestion(contextMock);

        // Assert
        Assert.assertEquals(testIdentifier, result.getName());
    }
}
