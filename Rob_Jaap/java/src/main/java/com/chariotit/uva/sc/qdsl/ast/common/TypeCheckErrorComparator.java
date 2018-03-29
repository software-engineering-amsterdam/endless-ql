package com.chariotit.uva.sc.qdsl.ast.common;

import java.util.Comparator;

public class TypeCheckErrorComparator implements Comparator<TypeCheckError> {

    @Override
    public int compare(TypeCheckError t1, TypeCheckError t2) {

        if (t1.getSourceFilePosition().getLineNumber().equals(t2.getSourceFilePosition()
                .getLineNumber())) {
            return t1.getSourceFilePosition().getColumnNumber() - t2.getSourceFilePosition()
                    .getColumnNumber();
        }

        return t1.getSourceFilePosition().getLineNumber() -
                t2.getSourceFilePosition().getLineNumber();
    }
}
