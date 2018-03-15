package org.uva.ql.validation;


import java.util.logging.Logger;

public abstract class Checker {

    public Logger logger;

    public Checker() {
        this.logger = Logger.getGlobal();
    }

    public abstract void runCheck();
}
