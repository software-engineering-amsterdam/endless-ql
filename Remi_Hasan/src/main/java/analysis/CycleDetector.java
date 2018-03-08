package analysis;

import evaluation.IASTVisitor;
import model.Form;
import model.Question;
import model.expression.Expression;
import model.expression.ExpressionIdentifier;
import model.expression.binary.*;
import model.expression.unary.ExpressionUnaryNeg;
import model.expression.unary.ExpressionUnaryNot;
import model.expression.variable.*;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CycleDetector implements IASTVisitor<List<String>> {

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
        for (Question question : form.questions) {
            // Only check expression when it is a predefined expression
            if (!question.isEditable()) {
                // For each question, add references to other questions to the graph
                List<String> referencedIdentifiers = this.visit(question.defaultAnswer);
                for (String identifier : referencedIdentifiers) {
                    graph.addEdge(question.name, identifier);
                }
            }
        }
    }

    public Set<String> detectCycles() {
        Graph<String, DefaultEdge> referenceGraph = createVerticesGraph();
        addReferenceEdges(referenceGraph);

        org.jgrapht.alg.CycleDetector<String, DefaultEdge> jGraphTCycleDetector
                = new org.jgrapht.alg.CycleDetector<>(referenceGraph);
        return jGraphTCycleDetector.findCycles();
    }

    private List<String> visitLeftRight(Expression left, Expression right) {
        List<String> identifiers = left.accept(this);
        identifiers.addAll(right.accept(this));
        return identifiers;
    }

    @Override
    public List<String> visit(Expression e) {
        return e.accept(this);
    }

    @Override
    public List<String> visit(ExpressionArithmeticDivide e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionArithmeticMultiply e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionArithmeticSubtract e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionArithmeticSum e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonEq e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonGE e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonGT e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonLE e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonLT e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionLogicalAnd e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionLogicalOr e) {
        return visitLeftRight(e.left, e.right);
    }

    @Override
    public List<String> visit(ExpressionUnaryNot e) {
        return e.value.accept(this);
    }

    @Override
    public List<String> visit(ExpressionUnaryNeg e) {
        return e.value.accept(this);
    }

    @Override
    public List<String> visit(ExpressionVariableBoolean e) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableDate e) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableInteger e) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableDecimal e) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableMoney e) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableString e) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableUndefined e) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionIdentifier e) {
        List<String> list = new ArrayList<>();
        list.add(e.identifier);
        return list;
    }
}
