package org.uva.ql.validation.checker;


import org.uva.ql.validation.ValidationResult;

import java.util.logging.Logger;

public abstract class Checker {

    public abstract ValidationResult runCheck();
}
