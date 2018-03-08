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
    public List<String> visit(Expression expression) {
        return expression.accept(this);
    }

    @Override
    public List<String> visit(ExpressionArithmeticDivide expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionArithmeticMultiply expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionArithmeticSubtract expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionArithmeticSum expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonEq expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonGE expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonGT expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonLE expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionComparisonLT expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionLogicalAnd expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionLogicalOr expression) {
        return visitLeftRight(expression.left, expression.right);
    }

    @Override
    public List<String> visit(ExpressionUnaryNot expression) {
        return expression.value.accept(this);
    }

    @Override
    public List<String> visit(ExpressionUnaryNeg expression) {
        return expression.value.accept(this);
    }

    @Override
    public List<String> visit(ExpressionVariableBoolean expression) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableDate expression) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableInteger expression) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableDecimal expression) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableMoney expression) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableString expression) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionVariableUndefined expression) {
        return new ArrayList<>();
    }

    @Override
    public List<String> visit(ExpressionIdentifier expression) {
        List<String> list = new ArrayList<>();
        list.add(expression.identifier);
        return list;
    }
}
