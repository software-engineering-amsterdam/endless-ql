package org.uva.ql.validation;

import org.uva.ql.ast.*;
import org.uva.ql.visitor.StatementVisitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionChecker extends Checker {


    private List<Question> questions;

    QuestionChecker(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public void runCheck() {
        Set<String> questionIDs = new HashSet<>();
        Set<String> questionTexts = new HashSet<>();

        for (Question question : this.questions) {
            if (!questionIDs.add(question.getName())) {
                logger.warning("WARNING: (var could be overwritten) question name " + question.getName() + " already exists");
            }

            if (!questionTexts.add(question.getContent())) {
                logger.warning("WARNING: QuestionReference content " + question.getContent() + " already exists");
            }
        }
    }
}
