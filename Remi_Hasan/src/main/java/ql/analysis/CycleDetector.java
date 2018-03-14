package ql.analysis;

import ql.model.Form;
import ql.model.Question;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;
import java.util.Set;

public class CycleDetector {

    private final Form form;

    public CycleDetector(Form form) {
        this.form = form;
    }

    private Graph<String, DefaultEdge> createVerticesGraph() {
        Graph<String, DefaultEdge> graph = new DefaultDirectedGraph<>(DefaultEdge.class);
        for (Question question : form.questions) {
            graph.addVertex(question.name);
        }

        return graph;
    }

    private void addReferenceEdges(Graph<String, DefaultEdge> graph) {
        ReferencedIdentifiersVisitor referencedIdentifiersVisitor = new ReferencedIdentifiersVisitor();

        for (Question question : form.questions) {
            // Only check expression when it is a predefined expression
            if (question.isComputed()) {
                // For each question, add references to other questions to the graph
                List<String> referencedIdentifiers = referencedIdentifiersVisitor.visit(question.computedAnswer);
                for (String identifier : referencedIdentifiers) {
                    graph.addEdge(question.name, identifier);
                }
            }
        }
    }

    public void detectCycles() {
        Graph<String, DefaultEdge> referenceGraph = createVerticesGraph();
        addReferenceEdges(referenceGraph);

        org.jgrapht.alg.CycleDetector<String, DefaultEdge> jGraphTCycleDetector
                = new org.jgrapht.alg.CycleDetector<>(referenceGraph);

        Set<String> cycleVariables = jGraphTCycleDetector.findCycles();
        if(!cycleVariables.isEmpty()) {
            throw new IllegalArgumentException("Cycles detected in the following variables: " + cycleVariables);
        }
    }
}
