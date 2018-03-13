package ParseObjectQLS.Widgets;

import java.util.List;

public class Radio extends Widget {
    private List<String> radioQuestions;

    public Radio(List<String> radioQuestions ){
        setRadioQuestions(radioQuestions);
    }


    public void setRadioQuestions(List<String> radioQuestions) {
        this.radioQuestions = radioQuestions;
    }
}
