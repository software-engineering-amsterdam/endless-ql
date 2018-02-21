package org.uva.sea.ql;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

import java.io.IOException;

public class QLGui {

    /**
     * Generate the GUI
     * @param guiSpecification Specification of the GUI
     */
    public void start(String guiSpecification) {
        try {
            QLCompiler compiler = new QLCompiler();
            compiler.compileScriptFile(toCharStream(guiSpecification));
        } catch (IOException e) {
            System.err.println("The gui specification cannot be found: " + guiSpecification);
            e.printStackTrace();
        }
    }

    /**
     * Convert file name to resource
     * @param fileName
     * @return
     * @throws IOException
     */
    private CharStream toCharStream(String fileName) throws IOException {
        return CharStreams.fromStream(getClass().getResourceAsStream(fileName));
    }
}
