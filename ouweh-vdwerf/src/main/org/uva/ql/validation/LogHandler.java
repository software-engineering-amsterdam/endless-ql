package org.uva.ql.validation;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

import java.util.*;

public class LogHandler extends Handler {

    private ArrayList<LogRecord> logs = new ArrayList<>();

    @Override
    public void publish(LogRecord record) {
        logs.add(record);
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }

    public boolean hasErrors() {
        // TODO info may not break this
        return logs.size() > 0;
    }

    public ArrayList<LogRecord> getLogs() {
        return logs;
    }
}
