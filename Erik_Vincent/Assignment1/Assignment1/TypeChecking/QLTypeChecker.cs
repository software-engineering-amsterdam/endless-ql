using System.Collections.Generic;
using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.AST.Value;

namespace Assignment1.TypeChecking
{
    internal class QLTypeChecker : QLASTBaseVisitor
    {
        private readonly MessageContainer _messages = new MessageContainer();
        private Type _currentType = Type.Undefined;

        public static (IEnumerable<string> errors, IEnumerable<string> warnings) CheckTypes(QuestionForm questionForm)
        {
            var checker = new QLTypeChecker();
            questionForm.Accept(checker);
            return checker._messages.ToTuple();
        }

        private void TypeCheckQuestion(Question question)
        {
            if (_currentType != question.Type)
                _messages.AddError("Line:" + question.LineNumber + " | Cannot assign value of type " + _currentType + " to question of type " + question.Type + ".");
            _currentType = question.Type;
        }

        public override void Visit(NormalQuestion question)
        {
            base.Visit(question);
            TypeCheckQuestion(question);
        }

        public override void Visit(ComputedQuestion question)
        {
            base.Visit(question);
            TypeCheckQuestion(question);
        }

        public override void Visit(IfStatement ifStatement)
        {
            ifStatement.Condition.Accept(this);
            if (_currentType != Type.Boolean)
                _messages.AddError("Line:" + ifStatement.LineNumber + " | Can not use value of type " + _currentType + " as condition for if statement.");
            VisitStatements(ifStatement.ThenStatements);
            VisitStatements(ifStatement.ElseStatements);
        }

        public override void Visit(Not expression)
        {
            base.Visit(expression);
            if (_currentType != Type.Boolean)
                _messages.AddError("Line: " + expression.LineNumber + " | Operator ! cannot be applied to expression of type " + _currentType);
            _currentType = Type.Boolean;
        }

        public override void Visit(Reference expression) => FollowReference(expression);

        private void TypeCheckBinary(Binary expression, IReadOnlyDictionary<(Type, Type), Type> supported, string operatorToken)
        {
            expression.Left.Accept(this);
            var left = _currentType;
            expression.Right.Accept(this);
            var right = _currentType;
            if (supported.ContainsKey((left, right)))
            {
                _currentType = supported[(left, right)];
                return;
            }
            _messages.AddError("Line: " + expression.LineNumber + " | Operator " + operatorToken + " cannot be applied to expressions of types " + left + " and " + right + ".");
            _currentType = Type.Undefined;
        }

        private readonly Dictionary<(Type, Type), Type> _logical = new Dictionary<(Type, Type), Type>
        {
            {(Type.Boolean, Type.Boolean), Type.Boolean}
        };
        public override void Visit(And expression) => TypeCheckBinary(expression, _logical, "&&");
        public override void Visit(Or expression) => TypeCheckBinary(expression, _logical, "||");

        private readonly Dictionary<(Type, Type), Type> _comparison = new Dictionary<(Type, Type), Type>
        {
            {(Type.Integer, Type.Integer), Type.Boolean},
            {(Type.Decimal, Type.Decimal), Type.Boolean},
            {(Type.Date, Type.Date), Type.Boolean},
            {(Type.Money, Type.Money), Type.Boolean}
        };
        public override void Visit(LessThan expression) => TypeCheckBinary(expression, _comparison, "<");
        public override void Visit(GreaterThan expression) => TypeCheckBinary(expression, _comparison, ">");
        public override void Visit(GreaterThanOrEqual expression) => TypeCheckBinary(expression, _comparison, ">=");
        public override void Visit(LessThanOrEqual expression) => TypeCheckBinary(expression, _comparison, "<=");

        public override void Visit(NotEqual expression) => _currentType = Type.Boolean;

        private readonly Dictionary<(Type, Type), Type> _equal = new Dictionary<(Type, Type), Type>
        {
            {(Type.Boolean, Type.Boolean), Type.Boolean},
            {(Type.Integer, Type.Integer), Type.Boolean},
            {(Type.Decimal, Type.Decimal), Type.Boolean},
            {(Type.Date, Type.Date), Type.Boolean},
            {(Type.Money, Type.Money), Type.Boolean},
            {(Type.String, Type.String), Type.Boolean}
        };
        public override void Visit(Equal expression) => TypeCheckBinary(expression, _equal, "==");

        private readonly Dictionary<(Type, Type), Type> _addSubtract = new Dictionary<(Type, Type), Type>
        {
            {(Type.Integer, Type.Integer), Type.Integer},
            {(Type.Decimal, Type.Decimal), Type.Decimal},
            {(Type.Money, Type.Money), Type.Money},
            {(Type.String, Type.String), Type.String}
        };
        public override void Visit(Add expression) => TypeCheckBinary(expression, _addSubtract, "+");
        public override void Visit(Subtract expression) => TypeCheckBinary(expression, _addSubtract, "-");

        private readonly Dictionary<(Type, Type), Type> _multiply = new Dictionary<(Type, Type), Type>
        {
            {(Type.Integer, Type.Integer), Type.Integer},
            {(Type.Decimal, Type.Decimal), Type.Decimal},
            {(Type.Integer, Type.Money), Type.Money},
            {(Type.Money, Type.Integer), Type.Money},
            {(Type.Decimal, Type.Money), Type.Money},
            {(Type.Money, Type.Decimal), Type.Money},
            {(Type.Integer, Type.Decimal), Type.Decimal},
            {(Type.Decimal, Type.Integer), Type.Decimal},
            {(Type.Integer, Type.String), Type.String},
            {(Type.String, Type.Integer), Type.String}
        };
        public override void Visit(Multiply expression) => TypeCheckBinary(expression, _multiply, "*");

        private readonly Dictionary<(Type, Type), Type> _divide = new Dictionary<(Type, Type), Type>
        {
            {(Type.Integer, Type.Integer), Type.Integer},
            {(Type.Decimal, Type.Decimal), Type.Decimal},
            {(Type.Money, Type.Integer), Type.Money},
            {(Type.Money, Type.Money), Type.Decimal},
            {(Type.Integer, Type.Decimal), Type.Decimal},
            {(Type.Decimal, Type.Integer), Type.Decimal}
        };
        public override void Visit(Divide expression) => TypeCheckBinary(expression, _divide, "/");

        public override void Visit(QLBoolean value) => _currentType = Type.Boolean;
        public override void Visit(QLInteger value) => _currentType = Type.Integer;
        public override void Visit(QLString value) => _currentType = Type.String;
        public override void Visit(QLDate value) => _currentType = Type.Date;
        public override void Visit(QLDecimal value) => _currentType = Type.Decimal;
        public override void Visit(QLMoney value) => _currentType = Type.Money;
    }
}
