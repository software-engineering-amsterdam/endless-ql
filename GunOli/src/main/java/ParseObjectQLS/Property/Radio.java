package ParseObjectQLS.Property;

import java.util.List;

public class Radio {
    private List<String> radioQuestions;

    public Radio(List<String> radioQuestions ){
        setRadioQuestions(radioQuestions);
    }


    public void setRadioQuestions(List<String> radioQuestions) {
        this.radioQuestions = radioQuestions;
    }
}
