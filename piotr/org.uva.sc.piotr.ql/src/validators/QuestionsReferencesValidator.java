package validators;

import ast.model.statement.Question;
import ast.visitors.ASTNodeAbstractVisitor;

import java.util.HashMap;
import java.util.HashSet;

public class QuestionsReferencesValidator extends ASTNodeAbstractVisitor {

    private HashMap<String, Node> nodes = new HashMap<>();

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

    @Override
    public void visit(Question question) {

        Node node = new Node(question);

        this.nodes.put(
                question.getVariableName() + ":" + question.getVariableType().getIdentifier(),
                node
        );

        super.visit(question);
    }

}
