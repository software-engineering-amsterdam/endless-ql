package org.uva.qls.validation;

import org.uva.ql.ast.Question;
import org.uva.ql.validation.Checker;
import org.uva.ql.validation.LogHandler;
import org.uva.qls.ast.Stylesheet;

import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.stream.*;
import java.util.*;

public class Validator {

    private Stylesheet stylesheet;
    private ArrayList<Question> qlQuestions;
    private LogHandler logHandler;
    private List<Checker> checkers;

    public Validator (ArrayList<Question> qlQuestions, Stylesheet stylesheet){
        this.qlQuestions = qlQuestions;
        this.stylesheet = stylesheet;
        this.checkers = new ArrayList<>();

        this.logHandler = (LogHandler) Logger.getGlobal().getHandlers()[0];

        initializeCheckers();
    }

    private void initializeCheckers() {
        List<String> qlQuestionIds = qlQuestions.stream().map(Question::getName).collect(Collectors.toList());
        List<String> qlsQuestionIds = stylesheet.getQuestions().stream().map(org.uva.qls.ast.Segment.Question::getId).collect(Collectors.toList());

        checkers.add(new ReferenceChecker(qlQuestionIds, qlsQuestionIds));
    }

    public void run() {
        for (Checker checker : checkers) {
            checker.runCheck();
        }
    }

}
