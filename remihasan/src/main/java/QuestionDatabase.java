import java.util.ArrayList;

public class QuestionDatabase {

    private static QuestionDatabase instance;
    private final ArrayList<Question> questions;
    private QuestionDatabase(){
        questions = new ArrayList<Question>();
    }

    public static QuestionDatabase getInstance(){
        if(instance == null){
            instance = new QuestionDatabase();
        }
        return instance;
    }

    public void addQuestion(Question question){
        questions.add(question);
    }

}


// QuestionDatabase.getInstance().addQuestion(q)