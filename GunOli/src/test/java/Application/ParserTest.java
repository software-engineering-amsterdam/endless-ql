package Application;

import QL.ParseObjectsQL.Form;
import QL.ParseObjectsQL.Question;
import QL.ParseObjectsQL.QuestionMap;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ParserTest {
    private ArrayList<String> expectedIdentifier = new ArrayList<>();
    private ArrayList<String> expectedTypes = new ArrayList<>();
    private ArrayList<String> expectedText = new ArrayList<>();
    private ArrayList<Object> expectedAnswear = new ArrayList<>();

    private ArrayList<String> ActualIdentifier = new ArrayList<>();
    private ArrayList<String> ActualText = new ArrayList<>();
    private ArrayList<String> ActualType = new ArrayList<>();
    private ArrayList<Object> ActualAnswer = new ArrayList<>();

    @Test
    public void ParserTester(){
        getValue();
        BuildExpectedLists();


        assertEquals(expectedIdentifier, ActualIdentifier);
        assertEquals(expectedText, ActualText);
        assertEquals(expectedTypes, ActualType);
        assertEquals(expectedAnswear, ActualAnswer);
    }

    private void BuildExpectedLists(){
        String[] identifiers = {"hasSoldHouse", "hasBoughtHouse", "hasMaintLoan", "sellingPrice", "privateDebt", "valueResidue"};

        for (int i = 0; i < identifiers.length; i++){
            expectedIdentifier.add(identifiers[i]);
        }

        String[] types = {"Boolean", "Boolean", "Boolean", "Money", "Money", "Money"};

        for(int i = 0; i < types.length; i++){
            expectedTypes.add(types[i]);
        }

        String[] text = {"\"Did you sell a house in 2010?\"", "\"Did you buy a house in 2010?\"", "\"Did you enter a loan for maintenance/reconstruction?\"", "\"Price the house was sold for:\"", "\"Private debts for the sold house:\"", "\"Value residue:\""};

        for(int i = 0; i < text.length; i++){
            expectedText.add(text[i]);
        }

        Boolean[] answer = {false, false, false};
        Double[] answer2 = {0.0, 0.0, 0.0};

        for(int i = 0; i < answer.length; i++){
            expectedAnswear.add(answer[i]);
        }
        for(int i = 0; i < answer2.length; i++){
            expectedAnswear.add(answer2[i]);
        }

    }

    private void getValue(){
        String formPath = "./src/main/resources/example.ql";
        File formFile = new File(formPath);
        Parser parser = new Parser();

        Form form = parser.parseInputToForm(formFile.getPath());
        QuestionMap qm = new QuestionMap(form);


        for(Question question : form.getBlock().getQuestions()){
            String QuestionName = question.getIdentifier();
            ActualIdentifier.add(qm.getQuestion(QuestionName).getIdentifier());
            ActualText.add(qm.getQuestion(QuestionName).getText());
            ActualType.add(qm.getQuestion(QuestionName).getType().toString());
            ActualAnswer.add(qm.getQuestion(QuestionName).getAnswer().evaluate().getValue());
        }

    }

}