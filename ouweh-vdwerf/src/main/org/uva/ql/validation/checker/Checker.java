package org.uva.ql.validation.checker;


import java.util.logging.Logger;

public abstract class Checker {

    protected Logger logger;

    protected Checker() {
        this.logger = Logger.getGlobal();
    }

    public abstract void runCheck();
}
