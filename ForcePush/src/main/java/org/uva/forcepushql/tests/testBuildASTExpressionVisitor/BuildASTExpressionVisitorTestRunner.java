package org.uva.forcepushql.tests.testBuildASTExpressionVisitor;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.uva.forcepushql.tests.testBuildASTVisitor.BuildASTVisitorTest;

public class BuildASTExpressionVisitorTestRunner {
    public static void main(String[] args)
    {
        Result result = JUnitCore.runClasses(BuildASTExpressionVisitorTest.class);

        for (Failure failure : result.getFailures())
        {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }
}
