package ParsingTest.QL;

import GUI.Parser;
import QL.AST.Form;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

public class ParserQuestionTest {

    private String example1 = "./src/test/resources/PassingForms/example1.ql";
    private String example2 = "./src/test/resources/PassingForms/example2.ql";

    public ArrayList questionListTest(String file){
        ArrayList questions;
        ArrayList question = new ArrayList();
        Form parsed = new Parser().parseInputToForm(file);

        questions = parsed.getQuestions();
        for (int i = 0; i < questions.size(); i++){
            question.add(parsed.getQuestions().get(i).getText());
        }
        return question;
    }

    @Test
    public void evaluateQuestionsExample1(){
        ArrayList example1Expected = new ArrayList();
        example1Expected.add("\"View the questions?\"");
        example1Expected.add("\"Are you sure?\"");
        example1Expected.add("\"Do you want to give your birthdate?\"");
        example1Expected.add("\"What is your name?\"");
        example1Expected.add("\"What is your name?\"");
        example1Expected.add("\"What is your favorite number?\"");
        example1Expected.add("\"Favorite decimal (really..)?\"");
        example1Expected.add("\"How much money do you have?\"");
        example1Expected.add("\"How much money do you want\"");
        example1Expected.add("\"You need this much:\"");
        example1Expected.add("\"You hate birthdays?\"");
        example1Expected.add("\"What is your birthdate?\"");

        assertEquals(example1Expected, questionListTest(example1));

    }

    @Test
    public void evaluateQuestionsExample2(){
        ArrayList example2Expected = new ArrayList();
        example2Expected.add("\"View the questions?\"");
        example2Expected.add("\"What is the number of life?\"");
        example2Expected.add("\"Where is my mind?\"");
        example2Expected.add("\"ready to see the answear?\"");
        example2Expected.add("\"Your right if you said pixies\"");
        example2Expected.add("\"This is a number larger than 5\"");
        example2Expected.add("\"If the number is larger than 5\"");
        example2Expected.add("\"This number is 3\"");
        example2Expected.add("\"q6 condition faild:\"");
        example2Expected.add("\"This condition should fail:\"");


        assertEquals(example2Expected, questionListTest(example2));

    }







}