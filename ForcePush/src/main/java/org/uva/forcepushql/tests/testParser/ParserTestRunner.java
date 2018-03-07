package org.uva.forcepushql.tests.testParser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.uva.forcepushql.antlr.GrammarLexer;
import org.uva.forcepushql.antlr.GrammarParser;

public class ParserTestRunner {

    public static void main(String[] args){
        Result result = JUnitCore.runClasses(ParserTest.class);

        for(Failure failure : result.getFailures()){
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());

    }
}
