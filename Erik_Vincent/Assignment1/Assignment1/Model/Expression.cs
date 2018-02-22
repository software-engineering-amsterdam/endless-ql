using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1
{
    public class Expression
    {
        private readonly object _value;
        public Expression(object value = null)
        {
            _value = value;
        }
        public virtual dynamic Evaluate() => _value;
    }

    internal class ExpressionNot : Expression
    {
        private readonly Expression _expression;
        public ExpressionNot(Expression expression)
        {
            _expression = expression;
        }
        public override dynamic Evaluate() => !_expression.Evaluate();
    }

    public class ExpressionId : Expression
    {
        public readonly string Id;
        public Question Question;
        public ExpressionId(string id)
        {
            Id = id;
        }
        public override dynamic Evaluate() => Question.Value;
    }

    internal abstract class ExpressionOperatorB : Expression
    {
        protected readonly Expression Left, Right;
        protected ExpressionOperatorB(Expression left, Expression right)
        {
            Left = left;
            Right = right;
        }
        public abstract override dynamic Evaluate();
    }

    internal class ExpressionAdd : ExpressionOperatorB
    {
        public ExpressionAdd(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() + Right.Evaluate();
    }
    internal class ExpressionSub : ExpressionOperatorB
    {
        public ExpressionSub(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() - Right.Evaluate();
    }
    internal class ExpressionMult : ExpressionOperatorB
    {
        public ExpressionMult(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() * Right.Evaluate();
    }
    internal class ExpressionDiv : ExpressionOperatorB
    {
        public ExpressionDiv(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() / Right.Evaluate();
    }
    internal class ExpressionGreaterEqual : ExpressionOperatorB
    {
        public ExpressionGreaterEqual(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() >= Right.Evaluate();
    }
    internal class ExpressionLessEqual : ExpressionOperatorB
    {
        public ExpressionLessEqual(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() <= Right.Evaluate();
    }
    internal class ExpressionGreater : ExpressionOperatorB
    {
        public ExpressionGreater(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() > Right.Evaluate();
    }
    internal class ExpressionLess : ExpressionOperatorB
    {
        public ExpressionLess(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() < Right.Evaluate();
    }
    internal class ExpressionEqual : ExpressionOperatorB
    {
        public ExpressionEqual(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() == Right.Evaluate();
    }
    internal class ExpressionNotEqual : ExpressionOperatorB
    {
        public ExpressionNotEqual(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() != Right.Evaluate();
    }
    internal class ExpressionAnd : ExpressionOperatorB
    {
        public ExpressionAnd(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() && Right.Evaluate();
    }
    internal class ExpressionOr : ExpressionOperatorB
    {
        public ExpressionOr(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() || Right.Evaluate();
    }
}
