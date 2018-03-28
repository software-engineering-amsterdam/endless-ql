package QL.Analysis;


import QL.ParseObjectsQL.Form;
import QL.ParseObjectsQL.Question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LabelDuplicationDetector {

    private final Form form;

    public LabelDuplicationDetector(Form form){
        this.form = form;
    }

    public Map<String, Set<Question>> detectDuplicateLabels(){
        Map<String, Set<Question>> labelMap = new HashMap<>();

        for(Question question : form.getQuestions()){
            Set<Question> referencedQuestions = new HashSet<>();

            if(labelMap.containsKey(question.getText())){
                referencedQuestions = labelMap.get(question.getText());
            }

            referencedQuestions.add(question);
            labelMap.put(question.getText(), referencedQuestions);
        }

        return labelMap;
    }
}
