package org.uva.app;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class LogHandler extends Handler {

    private List<LogRecord> logs = new ArrayList<>();

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

    public boolean hasWarnings() {
        return getLogs(Level.WARNING).size() > 0;
    }

    public boolean hasErrors() {
        return getLogs(Level.SEVERE).size() > 0;
    }

    public List<LogRecord> getLogs() {
        return getLogs(Level.FINEST);
    }

    public List<LogRecord> getLogs(Level level) {
        return logs.stream()
                .filter(logRecord -> logRecord.getLevel().intValue() >= level.intValue())
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
