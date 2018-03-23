package ql.analysis;

import ql.QLBaseVisitor;
import ql.model.Form;
import ql.model.Question;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import ql.model.expression.Expression;
import ql.model.expression.ExpressionIdentifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CycleDetector {

    private final Form form;

    public CycleDetector(Form form) {
        this.form = form;
    }

    public void detect() {
        Graph<String, DefaultEdge> referenceGraph = this.createVerticesGraph();
        this.addReferenceEdges(referenceGraph);

        org.jgrapht.alg.CycleDetector<String, DefaultEdge> jGraphTCycleDetector
                = new org.jgrapht.alg.CycleDetector<>(referenceGraph);

        Set<String> cycleVariables = jGraphTCycleDetector.findCycles();
        if(!cycleVariables.isEmpty()) {
            throw new IllegalArgumentException("Cycles detected in the following variables: " + cycleVariables);
        }
    }

    private Graph<String, DefaultEdge> createVerticesGraph() {
        Graph<String, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);

        // Visit all questions and add their identifier as a vertex to the graph
        this.form.accept(new QLBaseVisitor<Void>() {
            @Override
            public Void visit(Question question) {
                graph.addVertex(question.identifier);
                return super.visit(question);
            }
        });

        return graph;
    }

    private void addReferenceEdges(Graph<String, DefaultEdge> graph) {
        this.form.accept(new QLBaseVisitor<Void>() {
            @Override
            public Void visit(Question question) {
                // Only need to check expression when it is computed
                if (question.computedAnswer == null) {
                    return super.visit(question);
                }

                // Get all references made to other variables by the computed answer expression
                List<String> referencedIdentifiers =
                        IdentifiersCollector.collectReferencedIdentifiers(question.computedAnswer);

                // For each referenced question, add references to other questions to the graph
                for (String identifier : referencedIdentifiers) {
                    graph.addEdge(question.identifier, identifier);
                }
                return super.visit(question);
            }
        });
    }
}
