package QL.Analysis;

import QL.AST.Expressions.Expression;
import QL.AST.Expressions.IdentifierExpression;
import QL.AST.Form;
import QL.AST.Question;
import org.jgrapht.Graph;
import org.jgrapht.alg.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashSet;
import java.util.Set;

public class CyclicDependencyDetector {

    private final Form form;

    public CyclicDependencyDetector(Form form){
        this.form = form;
    }

    public void detectCycles(){
        Graph<String, DefaultEdge> dependencyGraph = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);

        for(Question question : form.getQuestions()){
            dependencyGraph.addVertex(question.getIdentifier());

            if(!question.isPredefined()){
                break;
            }

            Set<String> referencedQuestions = detectReferences(question.getAnswer());

            for(String identifier : referencedQuestions){
                dependencyGraph.addEdge(question.getIdentifier(), identifier);
            }

            CycleDetector<String, DefaultEdge> cycleDetector = new CycleDetector<>(dependencyGraph);

            Set<String> cyclicDependencies = cycleDetector.findCycles();
            if(!cyclicDependencies.isEmpty()){
                throw new IllegalArgumentException("Cyclic dependencies detected: " + cyclicDependencies);
            }
        }
    }

    private Set<String> detectReferences(Expression answer){
        Set<String> referencedQuestions = new HashSet<>();

        GenericExpressionVisitor referenceVisitor = new GenericExpressionVisitor(){
            @Override
            public Object visit(IdentifierExpression expression){
                referencedQuestions.add(expression.getIdentifier());
                return super.visit(expression);
            }
        };

        answer.accept(referenceVisitor);

        return referencedQuestions;
    }
}
