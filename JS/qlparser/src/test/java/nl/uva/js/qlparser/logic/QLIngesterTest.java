package nl.uva.js.qlparser.logic;

import nl.uva.js.qlparser.models.Form;
import nl.uva.js.qlparser.models.dataexpressions.Combinator;
import nl.uva.js.qlparser.models.dataexpressions.Value;
import nl.uva.js.qlparser.models.dataexpressions.Variable;
import nl.uva.js.qlparser.models.enums.ArithOp;
import nl.uva.js.qlparser.models.enums.CompOp;
import nl.uva.js.qlparser.models.enums.DataType;
import nl.uva.js.qlparser.models.formexpressions.FormExpression;
import nl.uva.js.qlparser.models.formexpressions.IfBlock;
import nl.uva.js.qlparser.models.formexpressions.Question;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class QLIngesterTest {

    private static final String INPUT_BASIC_FILE = "src/test/java/nl/uva/js/qlparser/logic/testdata/ql_input.jsql";
    private static final String INPUT_NULL_TEST = "src/test/java/nl/uva/js/qlparser/logic/testdata/null_test.jsql";

    @Test
    public void testGetFormFromLocation() throws IOException {
        // Questions
        Question hasSoldHouse = Question.builder()
                .name("hasSoldHouse")
                .question("Did you sell a house in 2010?")
                .dataType(DataType.BOOLEAN)
                .build();

        Question hasBoughtHouse = Question.builder()
                .name("hasBoughtHouse")
                .question("Did you buy a house in 2010?")
                .dataType(DataType.BOOLEAN)
                .value(Combinator.builder()
                        .operator(CompOp.EQ)
                        .left(Value.builder().dataType(DataType.BOOLEAN).value(true).build())
                        .right(Value.builder().dataType(DataType.BOOLEAN).value(true).build())
                        .build()) // TODO: Makes no sense
                .build();

        Question hasMaintLoan = Question.builder()
                .name("hasMaintLoan")
                .question("Did you enter a loan?")
                .dataType(DataType.BOOLEAN)
                .build();

        // Conditional questions
        Question sellingPrice = Question.builder()
                .name("sellingPrice")
                .question("What was the selling price?")
                .dataType(DataType.MONEY)
                .build();

        Question privateDebt = Question.builder()
                .name("privateDebt")
                .question("Private debts for the sold house:")
                .dataType(DataType.MONEY)
                .value(Value.builder().dataType(DataType.MONEY).value(1000.0).build())
                .build();

        Question valueResidue = Question.builder()
                .name("valueResidue")
                .question("Value residue:")
                .dataType(DataType.MONEY)
                .value(Combinator.builder()
                        .left(Variable.builder().dataType(DataType.MONEY).name("sellingPrice").build())
                        .operator(ArithOp.MIN)
                        .right(Variable.builder().dataType(DataType.MONEY).name("privateDebt").build())
                        .build())
                .build();

        IfBlock ifBlock = IfBlock.builder()
                .condition(Variable.builder()
                        .dataType(DataType.BOOLEAN)
                        .name("hasSoldHouse")
                        .build())
                .expressions(new LinkedList<>(Arrays.asList(
                        sellingPrice,
                        privateDebt,
                        valueResidue)))
                .build();

        LinkedList<FormExpression> expectedExpressions =
                new LinkedList<>(Arrays.asList(
                                hasSoldHouse,
                                hasBoughtHouse,
                                hasMaintLoan,
                                ifBlock
                ));

        Form expectedForm = Form.builder()
                .name("taxOfficeExample")
                .formExpressions(expectedExpressions)
                .build();

        Form actualForm = QLIngester.parseFormFromLocation(INPUT_BASIC_FILE);

        assertEquals(expectedForm.toString(), actualForm.toString());
    }

    @Test
    public void testNullValue() throws IOException {
        // Questions
        Question presetValue = Question.builder()
                .name("currentMonth")
                .question("The current month is")
                .dataType(DataType.STRING)
                .value(Value.builder().dataType(DataType.STRING).value("February").build())
                .build();

        // Questions
        Question nullValue = Question.builder()
                .name("favoriteMonth")
                .question("What is your favorite month?")
                .dataType(DataType.STRING)
                .build();

        LinkedList<FormExpression> expectedExpressions =
                new LinkedList<>(Arrays.asList(
                        presetValue,
                        nullValue
                ));

        Form expectedForm = Form.builder()
                .name("nullValueTest")
                .formExpressions(expectedExpressions)
                .build();

        Form actualForm = QLIngester.parseFormFromLocation(INPUT_NULL_TEST);

        assertEquals(expectedForm.toString(), actualForm.toString());
    }
}