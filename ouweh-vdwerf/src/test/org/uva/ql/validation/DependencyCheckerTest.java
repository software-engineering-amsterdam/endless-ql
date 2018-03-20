package org.uva.ql.validation;

import org.junit.Before;
import org.junit.Test;
import org.uva.app.InputHandler;
import org.uva.app.LogHandler;
import org.uva.ql.ast.Form;
import org.uva.ql.ast.expression.unary.Parameter;
import org.uva.ql.parsing.ASTBuilder;
import org.uva.ql.validation.checker.DependencyChecker;
import org.uva.ql.validation.collector.ParameterMapping;

import java.util.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DependencyCheckerTest {

    private LogHandler logHandler;

    @Before
    public void setUp() {
        Logger logger = Logger.getGlobal();
        LogManager.getLogManager().reset();
        this.logHandler = new LogHandler();
        logger.addHandler(logHandler);
    }

    @Test
    public void runCheckTestInput() {

        String input = new InputHandler().readFile("input/test/circularDependency.ql");
        ASTBuilder builder = new ASTBuilder();
        Form form = builder.buildAST(input);

        DependencyChecker dependencyChecker = new DependencyChecker(new ParameterMapping(form).getParameterMapping());
        dependencyChecker.runCheck();

        assertTrue(this.logHandler.hasErrors());
    }

    @Test
    public void runCheckSelfDependant() {
        Map<String, List<Parameter>> parameterMapping = new HashMap<>();
        List<Parameter> parameters;

        parameters = new ArrayList<>(Arrays.asList(new Parameter("Q1")));
        parameterMapping.put("Q1", parameters);

        DependencyChecker dependencyChecker = new DependencyChecker(parameterMapping);
        dependencyChecker.runCheck();

        assertTrue(this.logHandler.hasWarnings());
    }

    @Test
    public void runCheckLoopDependency() {
        Map<String, List<Parameter>> parameterMapping = new HashMap<>();
        List<Parameter> parameters;

        parameters = new ArrayList<>(Arrays.asList(new Parameter("Q2")));
        parameterMapping.put("Q1", parameters);

        parameters = new ArrayList<>(Arrays.asList(new Parameter("Q1")));
        parameterMapping.put("Q2", parameters);

        DependencyChecker dependencyChecker = new DependencyChecker(parameterMapping);
        dependencyChecker.runCheck();

        assertTrue(this.logHandler.hasWarnings());
    }

    @Test
    public void runCheckMultiLayderDependency() {
        Map<String, List<Parameter>> expressions = new HashMap<>();
        List<Parameter> parameters;

        parameters = new ArrayList<>(Arrays.asList(new Parameter("Q2")));
        expressions.put("Q1", parameters);

        parameters = new ArrayList<>(Arrays.asList(new Parameter("Q3")));
        expressions.put("Q2", parameters);

        parameters = new ArrayList<>(Arrays.asList(new Parameter("Q1")));
        expressions.put("Q3", parameters);

        DependencyChecker dependencyChecker = new DependencyChecker(expressions);
        dependencyChecker.runCheck();

        assertTrue(this.logHandler.hasWarnings());
    }

    @Test
    public void runCheckNonCircular() {
        Map<String, List<Parameter>> expressions = new HashMap<>();
        List<Parameter> parameters;

        parameters = new ArrayList<>(Arrays.asList(new Parameter("Q3")));
        expressions.put("Q1", parameters);

        parameters = new ArrayList<>(Arrays.asList(new Parameter("Q3")));
        expressions.put("Q2", parameters);

        DependencyChecker dependencyChecker = new DependencyChecker(expressions);
        dependencyChecker.runCheck();

        assertFalse(this.logHandler.hasWarnings());
    }
}