package QLS.parsing.checkers;

import QL.classes.Question;
import QLS.parsing.gen.QLSParser;

import java.util.HashMap;

public class Checks {
    public static void checkStyleSheet(QLSParser.StylesheetContext stylesheet, HashMap<String, Question> questionMap) {
        new QuestionChecker(questionMap).checkStyleSheet(stylesheet);
        new WidgetChecker(questionMap).checkStyleSheet(stylesheet);
    }
}
