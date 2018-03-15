package org.uva.sea.languages.ql.interpreter.staticAnalysis;

import org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers.Messages;

public interface IStaticAnalysis<T> {
    Messages doCheck(T node);
}
