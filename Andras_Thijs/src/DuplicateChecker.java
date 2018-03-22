import Nodes.Question;
import QLExceptions.SyntaxException;

import java.util.ArrayList;
import java.util.List;

/**
 * A small class holding two lists, to do duplicate label and name checking at the same time.
 */
public class DuplicateChecker {
    private List<String> names, labels, duplicateLabels;

    /**
     * Constructor method to initialize al lists.
     */
    public DuplicateChecker() {
        names = new ArrayList<>();
        labels = new ArrayList<>();
        duplicateLabels = new ArrayList<>();
    }

    /**
     * Because duplicate labels are just warnings, they don't throw an error, but are accumulated and retrieved in the end.
     * @return a list of duplicate labels.
     */
    public List<String> getDuplicateLabels() {
        return duplicateLabels;
    }

    /**
     * This method checks a list of Questions for duplicate names and labels.
     * It adds every new name or label to a list, which is then used for checking the next Question(s).
     * @param questions the list of Questions to be checked.
     * @throws SyntaxException when it finds a duplicate name.
     */
    public void checkQuestions(List<Question> questions) throws SyntaxException {
        for(Question q : questions) {
            // Get the name and label.
            String name = q.getName(), label = q.getLabel();

            // Check the name.
            if(names.contains(name)) {
                throw new SyntaxException("Duplicate question name: " + name);
            } else {
                names.add(name);
            }

            // Check the label.
            if(labels.contains(label)) {
                // Check if the label is already marked as duplicate.
                if(!duplicateLabels.contains(label))
                    duplicateLabels.add(label);
            } else {
                labels.add(label);
            }
        }
    }
}