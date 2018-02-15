package org.uva.sea.ql;

import org.uva.sea.ql.evaluate.Evaluator;
import org.uva.sea.ql.parser.elements.*;
import org.uva.sea.ql.parser.elements.expressions.*;
import org.uva.sea.ql.parser.elements.types.*;
import org.uva.sea.ql.traverse.Traverse;

import java.util.*;

@FunctionalInterface
interface ApplyDualNode<One, Two, Three> {
    public void apply(One one, Two two, Three three);
}

@FunctionalInterface
interface ApplySingleNode<One, Two> {
    public void apply(One one, Two two);
}

public class QLExprEvaluate extends Traverse {

    private Stack<ASTNode> stack = new Stack<>();

    private Map<String, Evaluator> evaluator;

    private boolean error = false;

    /**
     * Constructor
     * @param evaluator The supported operations
     */
    public QLExprEvaluate(Map<String, Evaluator> evaluator) {
        this.evaluator = evaluator;
    }

    /**
     * Evaluate the AST and get all questions
     * @param node The base AST node
     */
    public ASTNode getValue(ASTNode node) {
        node.doTraversal(this, TraverseType.BOTTOM_UP);
        return this.error ? null : this.stack.pop();
    }


    /**
     * Get evaluator for a type
     * @param type The operator type
     * @return the Evaluator for the given type
     */
    private Evaluator getEvaluator(Type type) {
        Evaluator eval = evaluator.get(type.getNodeType());
        if(eval == null) {
            System.err.println("Evaluator " + type + " is not found");
            this.error = true;
            return null;
        }

        return eval;
    }

    /**
     * Makes type compatible. Return a new node, that is compatible with the 2nth parameter
     * @param node Convert this node
     * @param with To the type of this node when possible
     */
    private ASTNode makeTypeCompatible(ASTNode node, ASTNode with) {
        if(node instanceof Int && with instanceof Dec) {
            int intVal = ((Int)node).getValue();
            return new Dec((double)intVal);
        }

        return node;
    }

    /**
     * Pop two elements from the stack, make them compatible. Get the evaluator. Execute the
     * operation that is passed
     * @param doubleOperator Operation that has to be done
     */
    private void doDoubleOperator(ApplyDualNode<Evaluator, ASTNode, ASTNode> doubleOperator) {
        ASTNode second = stack.pop();
        ASTNode first = stack.pop();
        first = makeTypeCompatible(first, second);
        second = makeTypeCompatible(second, first);
        Type typeName = first.getType();
        Evaluator eval = getEvaluator(typeName);
        doubleOperator.apply(eval, first, second);
    }

    /**
     * Pop one element from the stack. Get the evaluator. Execute the
     * operation that is passed
     * @param singleOperator Operation that has to be done
     */
    private void doSingleOperator(ApplySingleNode<Evaluator, ASTNode> singleOperator) {
        ASTNode node = stack.pop();
        Type typeName = node.getType();
        Evaluator eval = getEvaluator(typeName);
        singleOperator.apply(eval, node);
    }

    /**
     * Do the addition operation
     * @param node The node
     */
    public void doAdd(Add node)  {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.add(lhs, rhs));
            }
        });
    }

    public void doAnd(And node)  {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.and(lhs, rhs));
            }
        });
    }

    public void doDiv(Div node)  {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.div(lhs, rhs));
            }
        });
    }

    public void doEq(Eq node)  {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.eq(lhs, rhs));
            }
        });
    }

    public void doGEq(GEq node)  {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.gEq(lhs, rhs));
            }
        });
    }

    public void doGThan(GThan node)  {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.gThan(lhs, rhs));
            }
        });
    }

    public void doLEq(LEq node)  {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.lEq(lhs, rhs));
            }
        });
    }

    public void doLThan(LThan node)  {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.lThan(lhs, rhs));
            }
        });
    }

    public void doMul(Mul node)  {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.mul(lhs, rhs));
            }
        });
    }

    public void doNeg(Neg node)  {
        this.doSingleOperator((eval, value) -> {
            if(eval != null) {
                this.stack.add(eval.neg(value));
            }
        });
    }

    public void doNEq(NEq node)  {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.nEq(lhs, rhs));
            }
        });
    }

    public void doNot(Not node)  {
        this.doSingleOperator((eval, value) -> {
            if(eval != null) {
                this.stack.add(eval.not(value));
            }
        });
    }

    public void doOr(Or node) {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.or(lhs, rhs));
            }
        });
    }

    public void doPos(Pos node) {
        this.doSingleOperator((eval, value) -> {
            if(eval != null) {
                this.stack.add(eval.pos(value));
            }
        });
    }

    public void doSub(Sub node) {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.sub(lhs, rhs));
            }
        });
    }

    public void doBool(Bool node) {
        this.stack.add(node);
    }

    public void doDateExpr(DateExpr node) {
        this.stack.add(node);
    }

    public void doDec(Dec node) {
        this.stack.add(node);
    }

    public void doMoney(Money node) {
        this.stack.add(node);
    }

    public void doInt(Int node) {
        this.stack.add(node);
    }

    public void doStr(Str node) {
        this.stack.add(node);
    }

    public void doVar(Var node) {
        this.stack.add(node);
    }
}
