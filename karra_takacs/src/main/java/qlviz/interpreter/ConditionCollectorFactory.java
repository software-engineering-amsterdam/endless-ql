package qlviz.interpreter;

import qlviz.model.Form;

public interface ConditionCollectorFactory {
    ConditionCollector create(Form form);
}
