package org.uva.ql.validation;


import java.util.logging.Logger;

public abstract class Checker {

    Logger logger;

    public Checker() {
        this.logger = Logger.getGlobal();
    }

    public abstract void runCheck();
}
