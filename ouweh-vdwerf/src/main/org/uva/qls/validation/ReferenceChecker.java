package org.uva.qls.validation;

import org.uva.ql.validation.Checker;
import java.util.Collections;

import java.util.List;
import java.util.HashSet;
import java.util.stream.*;
import java.util.ArrayList;

class ReferenceChecker extends Checker {

    private List<String> qlQuestionIds;
    private List<String> qlsQuestionIds;

    public ReferenceChecker(List<String> qlQuestionIds, List<String> qlsQuestionIds) {
        this.qlQuestionIds = qlQuestionIds;
        this.qlsQuestionIds = qlsQuestionIds;

        Collections.sort(this.qlQuestionIds);
        Collections.sort(this.qlsQuestionIds);
    }
    @Override
    public void runCheck() {
        HashSet uniqueIds = new HashSet();
        List<String> duplicateIds = this.qlsQuestionIds.stream()
                .filter(e -> !uniqueIds.add(e))
                .collect(Collectors.toList());
        if (duplicateIds.size() > 0) {
            logger.severe(String.format("Questions: %s are referenced multiple times by the QLS", duplicateIds.toString()));
        }

        List qlsQuestionIdsCopy = new ArrayList(qlsQuestionIds);
        qlsQuestionIdsCopy.removeAll(qlQuestionIds);

        if (qlsQuestionIdsCopy.size() > 0) {
            logger.severe(String.format("Questions:%s are in QLS but not in QL", qlsQuestionIdsCopy.toString()));
        }

        qlQuestionIds.removeAll(qlsQuestionIds);
        if (qlQuestionIds.size() > 0) {
            logger.severe(String.format("Questions: %s are in QL but not in QLS", qlQuestionIds.toString()));
        }
    }
}
