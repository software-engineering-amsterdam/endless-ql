package org.uva.sea.ql;

import org.uva.sea.ql.evaluate.Value;
import org.uva.sea.ql.parser.elements.*;
import org.uva.sea.ql.parser.elements.expressions.*;
import org.uva.sea.ql.parser.elements.types.*;
import org.uva.sea.ql.parser.nodeTypes.BinaryOperator;
import org.uva.sea.ql.parser.nodeTypes.SingleNode;
import org.uva.sea.ql.traverse.BaseVisitor;
import org.uva.sea.ql.traverse.Visitor;

public class QLExprEvaluate implements Visitor<Value> {

    private Error errors = new Error();

    /**
     * Constructor
     * @param evaluator The supported operations
     */
    public QLExprEvaluate() {

    }

    /**
     * Evaluate the AST and get all questions
     * @param node The base AST node
     */
    public Value getValue(ASTNode node) {
        return node.accept(this);


        return this.error || this.notComplete ? null : this.stack.pop();
    }

    public boolean isError() {
        return error;
    }

    public boolean isNotComplete() {
        return notComplete;
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
        if(node instanceof Int && with instanceof Decimal) {
            int intVal = ((Int)node).getValue();
            return new Decimal(node.getToken(), intVal);
        }

        return node;
    }

    /**
     *
     * @param minimalStackSize
     * @return
     */
    private boolean stateIsValid(int minimalStackSize) {
        if(this.notComplete || this.error)
            return false;

        if(this.stack.size() < minimalStackSize) {
            this.error = true;
            return false;
        }

        return true;
    }

    /**
     * Pop two elements from the stack, make them compatible. Get the evaluator. Execute the
     * operation that is passed
     * @param doubleOperator Operation that has to be done
     */
    private void doDoubleOperator(ApplyDualNode<Evaluator, ASTNode, ASTNode> doubleOperator) {
        if(!this.stateIsValid(2))
            return;

        ASTNode second = stack.pop();
        ASTNode first = stack.pop();
        if(second == null || first == null) {
            this.notComplete = true;
            return;
        }

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
        if(!this.stateIsValid(1))
            return;

        ASTNode node = stack.pop();
        if(node == null) {
            this.notComplete = true;
            return;
        }

        Type typeName = node.getType();
        Evaluator eval = getEvaluator(typeName);
        singleOperator.apply(eval, node);
    }

    /**
     * Do the addition operation
     * @param node The node
     */
    public void doAdd(Addition node)  {
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

    public void doDiv(Division node)  {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.div(lhs, rhs));
            }
        });
    }

    public void doEq(Equal node)  {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.eq(lhs, rhs));
            }
        });
    }

    public void doGEq(GreaterOrEqual node)  {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.gEq(lhs, rhs));
            }
        });
    }

    public void doGThan(GreaterThan node)  {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.gThan(lhs, rhs));
            }
        });
    }

    public void doLEq(LessOrEqual node)  {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.lEq(lhs, rhs));
            }
        });
    }

    public void doLThan(LessThan node)  {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.lThan(lhs, rhs));
            }
        });
    }

    public void doMul(Multiplication node)  {
        this.doDoubleOperator((eval, lhs, rhs) -> {
            if(eval != null) {
                this.stack.add(eval.mul(lhs, rhs));
            }
        });
    }

    public void doNeg(Negative node)  {
        this.doSingleOperator((eval, value) -> {
            if(eval != null) {
                this.stack.add(eval.neg(value));
            }
        });
    }

    public void doNEq(NotEqual node)  {
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

    public void doPos(Positive node) {
        this.doSingleOperator((eval, value) -> {
            if(eval != null) {
                this.stack.add(eval.pos(value));
            }
        });
    }

    public void doSub(Subtraction node) {
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

    public void doDec(Decimal node) {
        this.stack.add(node);
    }

    public void doMoney(Money node) {
        this.stack.add(node);
    }

    public Int doInt(Int node) {
        this.stack.add(node);
    }

    public Str doStr(Str node) {
        this.stack.add(node);
    }

    @Override
    public Value visit(Addition node) {
        return null;
    }

    @Override
    public Value visit(And node) {
        Value left = node.getLhs().accept(this);

        return left.and(right);
    }
    /*
    public class IntValue extends Value{

    public Value and(Value value){
        return value.and(this);
    }

    public Value and(IntValue value){
        return new IntValue(this.intValue, value.intValue);
    }
    }*/

    @Override
    public Value visit(Division node) {
        return null;
    }

    @Override
    public Value visit(Equal node) {
        return null;
    }

    @Override
    public Value visit(GreaterOrEqual node) {
        return null;
    }

    @Override
    public Value visit(GreaterThan node) {
        return null;
    }

    @Override
    public Value visit(LessOrEqual node) {
        return null;
    }

    @Override
    public Value visit(LessThan node) {
        return null;
    }

    @Override
    public Value visit(Multiplication node) {
        return null;
    }

    @Override
    public Value visit(Negative node) {
        return null;
    }

    @Override
    public Value visit(NotEqual node) {
        return null;
    }

    @Override
    public Value visit(Not node) {
        return null;
    }

    @Override
    public Value visit(Or node) {
        return null;
    }

    @Override
    public Value visit(Positive node) {
        return null;
    }

    @Override
    public Value visit(Subtraction node) {
        return null;
    }

    @Override
    public Value visit(Bool node) {
        return null;
    }

    @Override
    public Value visit(DateExpr node) {
        return null;
    }

    @Override
    public Value visit(Decimal node) {
        return null;
    }

    @Override
    public Value visit(Money node) {
        return null;
    }

    @Override
    public Value visit(Int node) {
        return new IntValue(node.getValue());
    }

    @Override
    public Value visit(Str node) {
        return null;
    }

    @Override
    public Value visit(Type node) {
        return null;
    }

    public T visit(Variable node) {
        this.stack.add(node.getLinkedQuestion().getValue());
    }

    @Override
    public Value visit(Condition node) {
        return null;
    }

    @Override
    public Value visit(Form node) {
        return null;
    }

    @Override
    public Value visit(Question node) {
        return null;
    }

    @Override
    public Value visit(Statement node) {
        return null;
    }

    @Override
    public Value visit(Statements node) {
        return null;
    }

    @Override
    public Value visit(BinaryOperator node) {
        return null;
    }

    @Override
    public Value visit(SingleNode node) {
        return null;
    }
}
