package tests.visitors;

import QL.classes.Form;
import QL.classes.Question;
import QL.classes.values.BooleanValue;
import QL.classes.values.DateValue;
import QL.classes.values.IntegerValue;
import QL.classes.values.MoneyValue;
import QL.classes.values.StringValue;
import QL.parsing.visitors.FormVisitor;
import org.junit.Before;
import org.junit.Test;
import QL.parsing.TreeBuilder;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;

public class FormVisitorTest {

   private FormVisitor formVisitor;

    @Before
    public void init() throws Exception {
        formVisitor = new FormVisitor();
    }

//    @Test
//    public void visitForm() throws IOException {
//        LinkedHashMap<String, Question> expectationList = new LinkedHashMap<String, Question>();
//        expectationList.put("hasSoldHouse", new Question("Did you sell a house in 2010?", new BooleanValue(), false, true));
//        expectationList.put("hasBoughtHouse", new Question("Did you buy a house in 2010?", new BooleanValue(), false, true));
//        expectationList.put("hasMaintLoan", new Question("Did you enter a loan for maintenance/reconstruction?", new BooleanValue(), false, true));
//        expectationList.put("sellingPrice", new Question("Price the house was sold for:", new MoneyValue(), false, false));
//        expectationList.put("privateDebt", new Question("Private debts for the sold house:", new MoneyValue(), false, false));
//        expectationList.put("ValueResidue", new Question("Value residue:", new MoneyValue(), true, false));
//        LinkedHashMap<String, Question> actualList = (LinkedHashMap<String, Question>) formVisitor.visitForm(new TreeBuilder().build(new FileInputStream("resources/QL/exampleForm")));
//        assertEquals(expectationList, actualList);
//    }

    @Test
    public void visitBoolIdentifier() {
    }

    @Test
    public void visitBoolBraces() {
    }

    @Test
    public void visitNotOperation() {
    }

    @Test
    public void visitBoolOperation() {
    }

    @Test
    public void visitIfStatement() throws IOException {
     //   Object object = initVisitor.visitForm(new TreeBuilder().build(new FileInputStream("resources/exampleForm")));

    }
}
