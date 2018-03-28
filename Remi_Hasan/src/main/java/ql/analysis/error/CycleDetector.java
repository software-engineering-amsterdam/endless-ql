package ql.analysis.error;

import ql.QLBaseVisitor;
import ql.analysis.IdentifiersCollector;
import ql.evaluation.SymbolTable;
import ql.model.Form;
import ql.model.statement.Question;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.Set;

public class CycleDetector implements IQLErrorAnalysis {

    @Override
    public void analyze(Form form, SymbolTable symbolTable) {
        // Add all identifiers to a graph
        Graph<String, DefaultEdge> referenceGraph = this.createVerticesGraph(form);

        // Add all edges between questions referring to each other to the graph
        this.addReferenceEdges(form, referenceGraph);

        org.jgrapht.alg.CycleDetector<String, DefaultEdge> jGraphTCycleDetector
                = new org.jgrapht.alg.CycleDetector<>(referenceGraph);

        Set<String> cycleVariables = jGraphTCycleDetector.findCycles();
        if(!cycleVariables.isEmpty()) {
            throw new IllegalArgumentException("Cycles detected in the following variables: " + cycleVariables);
        }
    }

    private Graph<String, DefaultEdge> createVerticesGraph(Form form) {
        Graph<String, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);

        // Visit all questions and add their identifier as a vertex to the graph
        form.accept(new QLBaseVisitor<Void>() {
            @Override
            public Void visit(Question question) {
                graph.addVertex(question.getIdentifier());
                return super.visit(question);
            }
        });

        return graph;
    }

    private void addReferenceEdges(Form form, Graph<String, DefaultEdge> graph) {
        form.accept(new QLBaseVisitor<Void>() {
            @Override
            public Void visit(Question question) {
                // Only need to check expression when it is computed
                if (!question.isComputed()) {
                    return super.visit(question);
                }

                // Get all references made to other variables by the computed answer expression
                Set<String> referencedIdentifiers =
                        IdentifiersCollector.collectReferencedIdentifiers(question.getComputedAnswer());

                // For each referenced question, add references to other questions to the graph
                for (String identifier : referencedIdentifiers) {
                    graph.addEdge(question.getIdentifier(), identifier);
                }
                return super.visit(question);
            }
        });
    }
}
