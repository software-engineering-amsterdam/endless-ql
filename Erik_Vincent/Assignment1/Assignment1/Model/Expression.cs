using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Assignment1
{
    internal class Expression
    {
        private readonly object _value;

        public Expression(object value = null)
        {
            _value = value;
        }

        public virtual dynamic Evaluate() => _value;
    }

    internal class ExpressionOperatorB : Expression
    {
        private readonly Expression _left, _right;
        private readonly Func<Expression, Expression, dynamic> _opFunc;

        public ExpressionOperatorB(Expression left, Expression right, Func<Expression, Expression, dynamic> opFunc)
        {
            _left = left;
            _right = right;
            _opFunc = opFunc;
        }

        public override dynamic Evaluate() => _opFunc(_left, _right);
    }

    internal class ExpressionId : Expression
    {
        private readonly Dictionary<string, Question> _questions;
        private readonly string _id;

        public ExpressionId(string id, Dictionary<string, Question> questions)
        {
            _id = id;
            _questions = questions;
        }
        
        public override dynamic Evaluate() => _questions[_id].Expression.Evaluate();
    }
}
