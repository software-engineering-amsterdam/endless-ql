package validators;

import ast.model.expressions.unary.values.VariableReference;
import ast.model.statements.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class QuestionsDependencyValidator {

    private HashMap<Question, Node> nodes = new HashMap<>();

    static class Node {
        private final Question question;
        private final HashSet<Edge> inEdges;
        private final HashSet<Edge> outEdges;

        public Node(Question question) {
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

    public QuestionsDependencyValidator(HashMap<Question, ArrayList<VariableReference>> questionsMap) {

        // create nodes
        for (Question question : questionsMap.keySet()) {
            this.nodes.put(question, new Node(question));
        }

        // create edges
        for (Map.Entry<Question, ArrayList<VariableReference>> entry : questionsMap.entrySet()) {

            // 

            for (VariableReference reference : entry.getValue()) {
                //this.nodes.get();
            }
        }

    }



}
