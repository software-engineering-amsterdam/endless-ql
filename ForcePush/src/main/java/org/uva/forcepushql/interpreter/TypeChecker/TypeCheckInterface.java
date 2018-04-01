package org.uva.forcepushql.interpreter.TypeChecker;

import org.uva.forcepushql.interpreter.TypeChecker.Helpers.Messages;
import org.uva.forcepushql.parser.ast.elements.FormNode;

public interface TypeCheckInterface
{
    Messages doCheck(FormNode node);
}
