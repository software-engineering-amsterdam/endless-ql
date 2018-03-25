using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.AST.Value;

namespace Assignment1.Rendering
{
    public class ExpressionEvaluator : IExpressionVisitor
    {
        private IValue _currentValue;
        private string _valueAsString;
        private SymbolTable _table;

        public ExpressionEvaluator(SymbolTable table)
        {
            _table = table;
        }

        public string GetValueAsString(IExpression expression)
        {
            IValue evaluated = EvaluateExpression(expression);
            switch (evaluated.Type)
            {
                case Type.Boolean:
                    Visit((QLBoolean)evaluated);
                    break;
                case Type.Integer:
                    Visit((QLInteger)evaluated);
                    break;
                case Type.Decimal:
                    Visit((QLDecimal)evaluated);
                    break;
                case Type.Money:
                    Visit((QLMoney)evaluated);
                    break;
                case Type.String:
                    Visit((QLString)evaluated);
                    break;
                case Type.Date:
                    Visit((QLDate)evaluated);
                    break;
            }
            return _valueAsString;
        }

        public IValue EvaluateExpression(IExpression expression)
        {
            expression.Accept(this);
            return _currentValue;
        }

        public void Visit(QLBoolean value)
        {
            _currentValue = value;
            _valueAsString = value.Value.ToString();
        }

        public void Visit(QLInteger value)
        {
            _currentValue = value;
            _valueAsString = value.Value.ToString();
        }

        public void Visit(Undefined undefined)
        {
            _currentValue = undefined;
            _valueAsString = "false";
        }

        public void Visit(QLString value)
        {
            _currentValue = value;
            _valueAsString = value.Value.ToString();
        }

        public void Visit(QLDate value)
        {
            _currentValue = value;
            _valueAsString = value.Value.ToString();
        }

        public void Visit(QLDecimal value)
        {
            _currentValue = value;
            _valueAsString = value.Value.ToString();
        }

        public void Visit(QLMoney value)
        {
            _currentValue = value;
            _valueAsString = value.Value.ToString();
        }

        public void Visit(Not expression)
        {
            expression.Accept(this);
            IValue value = (IValue)_currentValue;
            _currentValue = value.Not();
        }

        public void Visit(Reference expression)
        {
            _table.GetExpression(expression.QuestionId).Accept(this);
        }

        public void Visit(And expression)
        {
            expression.Right.Accept(this);
            IValue leftValue = (IValue)_currentValue;
            expression.Left.Accept(this);
            IValue rightValue = (IValue)_currentValue;
            _currentValue = leftValue.And(rightValue);
        }

        public void Visit(Or expression)
        {
            expression.Right.Accept(this);
            IValue leftValue = (IValue)_currentValue;
            expression.Left.Accept(this);
            IValue rightValue = (IValue)_currentValue;
            _currentValue = leftValue.Or(rightValue);
        }

        public void Visit(LessThan expression)
        {
            expression.Right.Accept(this);
            IValue leftValue = (IValue)_currentValue;
            expression.Left.Accept(this);
            IValue rightValue = (IValue)_currentValue;
            _currentValue = leftValue.LessThan(rightValue);
        }

        public void Visit(GreaterThan expression)
        {
            expression.Right.Accept(this);
            IValue leftValue = (IValue)_currentValue;
            expression.Left.Accept(this);
            IValue rightValue = (IValue)_currentValue;
            _currentValue = leftValue.GreaterThan(rightValue);
        }

        public void Visit(GreaterThanOrEqual expression)
        {
            expression.Right.Accept(this);
            IValue leftValue = (IValue)_currentValue;
            expression.Left.Accept(this);
            IValue rightValue = (IValue)_currentValue;
            _currentValue = leftValue.GreaterThanOrEqual(rightValue);
        }

        public void Visit(LessThanOrEqual expression)
        {
            expression.Right.Accept(this);
            IValue leftValue = (IValue)_currentValue;
            expression.Left.Accept(this);
            IValue rightValue = (IValue)_currentValue;
            _currentValue = leftValue.LessThanOrEqual(rightValue);
        }

        public void Visit(NotEqual expression)
        {
            expression.Right.Accept(this);
            IValue leftValue = (IValue)_currentValue;
            expression.Left.Accept(this);
            IValue rightValue = (IValue)_currentValue;
            _currentValue = leftValue.NotEqual(rightValue);
        }

        public void Visit(Equal expression)
        {
            expression.Right.Accept(this);
            IValue leftValue = (IValue)_currentValue;
            expression.Left.Accept(this);
            IValue rightValue = (IValue)_currentValue;
            _currentValue = leftValue.Equal(rightValue);
        }

        public void Visit(Add expression)
        {
            expression.Right.Accept(this);
            IValue leftValue = (IValue)_currentValue;
            expression.Left.Accept(this);
            IValue rightValue = (IValue)_currentValue;
            _currentValue = leftValue.Add(rightValue);
        }

        public void Visit(Subtract expression)
        {
            expression.Right.Accept(this);
            IValue leftValue = (IValue)_currentValue;
            expression.Left.Accept(this);
            IValue rightValue = (IValue)_currentValue;
            _currentValue = leftValue.Subtract(rightValue);
        }

        public void Visit(Multiply expression)
        {
            expression.Right.Accept(this);
            IValue leftValue = (IValue)_currentValue;
            expression.Left.Accept(this);
            IValue rightValue = (IValue)_currentValue;
            _currentValue = leftValue.Multiply(rightValue);
        }

        public void Visit(Divide expression)
        {
            expression.Right.Accept(this);
            IValue leftValue = (IValue)_currentValue;
            expression.Left.Accept(this);
            IValue rightValue = (IValue)_currentValue;
            _currentValue = leftValue.Divide(rightValue);
        }
    }
}
