package org.uva.ql.validation;


import java.util.logging.Logger;

abstract class Checker {

    Logger logger;

    Checker() {
        this.logger = Logger.getGlobal();
    }

    abstract void runCheck();
}
