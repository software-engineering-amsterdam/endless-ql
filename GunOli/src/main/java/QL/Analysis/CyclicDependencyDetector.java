package QL.Analysis;

import QL.AST.Expressions.Expression;
import QL.AST.Expressions.IdentifierExpression;
import QL.AST.Form;
import QL.AST.Question;
import QL.Visitors.GenericExpressionVisitor;
import org.jgrapht.Graph;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CyclicDependencyDetector {

    private final Form form;

    public CyclicDependencyDetector(Form form) {
        this.form = form;
    }

    public void detectCycles() {
        Graph<Question, DefaultEdge> dependencyGraph = buildDependencyGraph();

        CycleDetector<Question, DefaultEdge> cycleDetector = new CycleDetector<>(dependencyGraph);
        Set<Question> cyclicDependencies = cycleDetector.findCycles();

        if (!cyclicDependencies.isEmpty()) {
            throw new IllegalArgumentException(
                "Cyclic dependencies detected: " + getCycleIdentifiers(cyclicDependencies));
        }
    }

    private Graph<Question, DefaultEdge> buildDependencyGraph() {
        Graph<Question, DefaultEdge> dependencyGraph = new DefaultDirectedGraph<Question, DefaultEdge>(
            DefaultEdge.class);

        buildVerticesFromQuestions(dependencyGraph);
        buildEdgesFromReferences(dependencyGraph);

        return dependencyGraph;
    }

    private void buildVerticesFromQuestions(Graph<Question, DefaultEdge> dependencyGraph) {
        for (Question question : form.getQuestions()) {
            dependencyGraph.addVertex(question);
        }
    }

    private void buildEdgesFromReferences(Graph<Question, DefaultEdge> dependencyGraph) {
        for (Question question : dependencyGraph.vertexSet()) {
            Set<Question> referencedQuestions;

            if (!question.isPredefined()) {
                continue;
            }

            referencedQuestions = detectReferences(question.getAnswer());

            for (Question referencedQuestion : referencedQuestions) {
                dependencyGraph.addEdge(question, referencedQuestion);
            }
        }
    }

    private Set<Question> detectReferences(Expression answer) {
        Set<Question> referencedQuestions = new HashSet<>();

        GenericExpressionVisitor referenceVisitor = new GenericExpressionVisitor() {
            @Override
            public Object visit(IdentifierExpression expression) {
                Question referencedQuestion = form.getQuestion(expression.getIdentifier());
                referencedQuestions.add(referencedQuestion);
                return super.visit(expression);
            }
        };

        answer.accept(referenceVisitor);

        return referencedQuestions;
    }

    private List<String> getCycleIdentifiers(Set<Question> cyclicDependencies) {
        List<String> identifiers = new ArrayList<>();
        for (Question question : cyclicDependencies) {
            identifiers.add(question.getIdentifier());
        }
        return identifiers;
    }
}