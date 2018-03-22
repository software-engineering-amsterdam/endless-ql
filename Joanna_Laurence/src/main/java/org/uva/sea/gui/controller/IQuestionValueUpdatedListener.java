package org.uva.sea.gui.controller;

import org.uva.sea.languages.ql.interpreter.evaluate.valueTypes.Value;

public interface IQuestionValueUpdatedListener {
    void updateGuiVariable(String identifier, Value value);
}
