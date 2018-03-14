namespace Assignment1.Model
{
    public abstract class Expression
    {
        public abstract dynamic Evaluate();
    }

    public class ExpressionValue : Expression
    {
        private readonly object _value;
        public ExpressionValue(object value = null)
        {
            _value = value;
        }
        public override dynamic Evaluate() => _value;
    }

    public class ExpressionNot : Expression
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
        public override dynamic Evaluate() => Question != null ? Question.Value : null;
    }

    public abstract class ExpressionOperatorB : Expression
    {
        protected readonly Expression Left, Right;
        protected ExpressionOperatorB(Expression left, Expression right)
        {
            Left = left;
            Right = right;
        }
        public abstract override dynamic Evaluate();
    }

    public class ExpressionAdd : ExpressionOperatorB
    {
        public ExpressionAdd(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() + Right.Evaluate();
    }
    public class ExpressionSub : ExpressionOperatorB
    {
        public ExpressionSub(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() - Right.Evaluate();
    }
    public class ExpressionMult : ExpressionOperatorB
    {
        public ExpressionMult(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() * Right.Evaluate();
    }
    public class ExpressionDiv : ExpressionOperatorB
    {
        public ExpressionDiv(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() / Right.Evaluate();
    }
    public class ExpressionGreaterEqual : ExpressionOperatorB
    {
        public ExpressionGreaterEqual(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() >= Right.Evaluate();
    }
    public class ExpressionLessEqual : ExpressionOperatorB
    {
        public ExpressionLessEqual(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() <= Right.Evaluate();
    }
    public class ExpressionGreater : ExpressionOperatorB
    {
        public ExpressionGreater(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() > Right.Evaluate();
    }
    public class ExpressionLess : ExpressionOperatorB
    {
        public ExpressionLess(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() < Right.Evaluate();
    }
    public class ExpressionEqual : ExpressionOperatorB
    {
        public ExpressionEqual(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() == Right.Evaluate();
    }
    public class ExpressionNotEqual : ExpressionOperatorB
    {
        public ExpressionNotEqual(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() != Right.Evaluate();
    }
    public class ExpressionAnd : ExpressionOperatorB
    {
        public ExpressionAnd(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() && Right.Evaluate();
    }
    public class ExpressionOr : ExpressionOperatorB
    {
        public ExpressionOr(Expression left, Expression right) : base(left, right) { }
        public override dynamic Evaluate() => Left.Evaluate() || Right.Evaluate();
    }
}
