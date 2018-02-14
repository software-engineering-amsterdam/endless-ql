package qlviz.interpreter;

import qlviz.QLParser;
import qlviz.model.Form;


/**
 * Creates a qlviz representation of a QL Parser.
 */
public interface ITreeToFormConverter {
    Form Convert(QLParser parser);
}
