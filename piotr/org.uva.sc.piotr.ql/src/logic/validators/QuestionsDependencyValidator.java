package logic.validators;

import ast.model.expressions.values.VariableReference;
import ast.model.statements.Question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class QuestionsDependencyValidator {

    public HashMap<String, Node> nodes = new HashMap<>();

    static class Node {
        private final Question question;
        private final HashSet<Edge> inEdges;
        private final HashSet<Edge> outEdges;

        Node(Question question) {
            this.question = question;
            this.inEdges = new HashSet<>();
            this.outEdges = new HashSet<>();
        }

        public Node addEdge(Node node) {
            Edge e = new Edge(this, node);
            this.outEdges.add(e);
            node.inEdges.add(e);
            return this;
        }

        @Override
        public String toString() {
            return question.getVariableName();
        }
    }

    static class Edge {
        private final Node from;
        private final Node to;

        Edge(Node from, Node to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object obj) {
            Edge e = (Edge) obj;
            return e.from == from && e.to == to;
        }
    }

    public QuestionsDependencyValidator(HashMap<Question, List<VariableReference>> questionsMap) {
        this.CreateGraph(questionsMap);
    }

    private void CreateGraph(HashMap<Question, List<VariableReference>> questionsMap) {

        // create nodes
        for (Question question : questionsMap.keySet()) {
            this.nodes.put(question.getVariableName(), new Node(question));
        }

        // create edges
        for (Map.Entry<Question, List<VariableReference>> entry : questionsMap.entrySet()) {

            // for each given question (entry)
            Node referringNode = this.nodes.get(entry.getKey().getVariableName());

            // find variables that it refers to
            for (VariableReference reference : entry.getValue()) {

                // find node by name
                Node referredNode = nodes.get(reference.getName());

                // add edge
                if (referredNode != null) {
                    referringNode.addEdge(referredNode);
                }
            }
        }
    }


    // TODO: write code
    private void ConstructTransitiveClosure() {
        
    }
}
