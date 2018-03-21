import Nodes.Question;
import QLExceptions.SyntaxException;

import java.util.ArrayList;
import java.util.List;

public class TwoLists {
    private List<String> names, labels;
    private List<String> duplicateLabels;

    public TwoLists() {
        names = new ArrayList<>();
        labels = new ArrayList<>();
    }

    public List<String> getDuplicateLabels() {
        return duplicateLabels;
    }

    public void checkQuestions(List<Question> question) throws SyntaxException {
        for(Question q : question) {
            String name = q.getName(), label = q.getLabel();

            if(names.contains(name)) {
                throw new SyntaxException("Duplicate question name: " + name);
            } else {
                names.add(name);
            }

            if(labels.contains(label)) {
                if(!duplicateLabels.contains(label))
                    duplicateLabels.add(label);
            } else {
                labels.add(label);
            }
        }
    }
}