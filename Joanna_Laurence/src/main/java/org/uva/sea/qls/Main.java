package org.uva.sea.qls;

import org.uva.sea.ql.interpreter.exceptions.StaticAnalysisError;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        try {
            interpreter.generate("src/main/resources/test.qls");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (StaticAnalysisError staticAnalysisError) {
            staticAnalysisError.printStackTrace();
        }
    }

}
