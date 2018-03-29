using System.Collections.Generic;
using System.Linq;
using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.AST.Value;
using Assignment1.Parser;

namespace Assignment1.TypeChecking
{
    internal class QLTypeChecker : IQLASTVisitor, IExpressionVisitor
    {
        private readonly Dictionary<string, Question> _questions = new Dictionary<string, Question>();
        private readonly List<string> _warnings = new List<string>();
        private ParseErrorHandler _errorHandler = new ParseErrorHandler();
        private Type _currentType = Type.Undefined;
        public List<string> Warnings => _warnings;

        public void TypeCheckQuestionForm(QuestionForm questionForm) => questionForm.Accept(this);

        private void TypeCheckQuestionAnswer(int lineNumber, Type questionType, IValue questionValue)
        {
            questionValue.Accept(this);
            string errorMessage = "Cannot assign value of type " + _currentType.ToString() + " to question of type " + questionType.ToString() + ".";
            if (_currentType != Type.Undefined)
            {
                if (questionType != _currentType)
                    _errorHandler.AddError(lineNumber, errorMessage);
            }
        }

        private void TypeCheckQuestionAnswer(int lineNumber, Type questionType, IExpression questionExpression)
        {
            questionExpression.Accept(this);
            string errorMessage = "Cannot assign expression of type " + _currentType.ToString() + " to question of type " + questionType.ToString() + ".";
            if (questionType != _currentType)
                _errorHandler.AddError(lineNumber, errorMessage);
        }

        private Type TypeCheckBinaryLogical(Binary expression, string logicalOperator)
        {
            expression.Left.Accept(this);
            Type leftType = _currentType;
            expression.Right.Accept(this);
            Type rightType = _currentType;

            if (leftType != Type.Boolean && rightType != Type.Boolean)
            {
                _errorHandler.AddError(expression.LineNumber, "Operator " + logicalOperator + 
                    " cannot be applied to expressions of types " + leftType.ToString() + " and " + rightType.ToString() + ".");
            }

            return Type.Boolean;
        }

        private Type TypeCheckBinaryComparison(Binary expression, string comparisonOperator)
        {
            expression.Left.Accept(this);
            Type leftType = _currentType;
            expression.Right.Accept(this);
            Type rightType = _currentType;

            bool comparisonCondition = comparisonOperator.Equals("==") || comparisonOperator.Equals("!=") ?
                (leftType != Type.Boolean && rightType != Type.Boolean) || (!leftType.IsNumeric() && !rightType.IsNumeric()) :
                (!leftType.IsNumeric() && !rightType.IsNumeric());
            if (comparisonCondition)
            {
                _errorHandler.AddError(expression.LineNumber, "Operator " + comparisonOperator +
                    " cannot be applied to expressions of types " + leftType.ToString() + " and " + rightType.ToString() + ".");
            }

            return Type.Boolean;
        }

        private Type TypeCheckBinaryArithmetic(Binary expression, string arithmeticOperator)
        {
            expression.Left.Accept(this);
            Type leftType = _currentType;
            expression.Right.Accept(this);
            Type rightType = _currentType;

            if (!leftType.IsNumeric() && !rightType.IsNumeric())
            {
                _errorHandler.AddError(expression.LineNumber, "Operator " + arithmeticOperator +
                    " cannot be applied to expressions of types " + leftType.ToString() + " and " + rightType.ToString() + ".");
            }

            return TypeMethods.InferArithmeticType(leftType, rightType);
        }

        private bool QuestionIdExists(string questionId) => _questions.ContainsKey(questionId);

        public void Visit(QuestionForm questionForm)
        {
            foreach (Statement statement in questionForm.Statements)
            {
                statement.Accept(this);
            }
            if (_errorHandler.HasErrors)
                _errorHandler.ThrowParseException();
        }

        public void Visit(NormalQuestion question)
        {
            TypeCheckQuestionAnswer(question.LineNumber, question.Type, question.Answer);
            _questions.Add(question.Id, question);
        }

        public void Visit(ComputedQuestion question)
        {
            TypeCheckQuestionAnswer(question.LineNumber, question.Type, question.Computation);
            _questions.Add(question.Id, question);
        }

        public void Visit(IfStatement ifStatement)
        {
            ifStatement.Condition.Accept(this);
            if (_currentType != Type.Boolean)
                _errorHandler.AddError(ifStatement.LineNumber, "The condition in if statement is not of type boolean.");
            foreach (Statement statement in ifStatement.ThenStatements)
            {
                statement.Accept(this);
            }
            foreach (Statement statement in ifStatement.ElseStatements)
            {
                statement.Accept(this);
            }
        }

        public void Visit(QLBoolean value)
        {
            _currentType = Type.Boolean;
        }

        public void Visit(QLInteger value)
        {
            _currentType = Type.Integer;
        }

        public void Visit(QLString value)
        {
            _currentType = Type.String;
        }

        public void Visit(QLDate value)
        {
            _currentType = Type.Date;
        }

        public void Visit(QLDecimal value)
        {
            _currentType = Type.Decimal;
        }

        public void Visit(QLMoney value)
        {
            _currentType = Type.Money;
        }

        public void Visit(Not expression)
        {
            expression.Accept(this);

            if (_currentType != Type.Boolean)
            {
                _errorHandler.AddError(expression.LineNumber, "Operator ! cannot be applied to expression of type " + _currentType.ToString());
            }

            _currentType = Type.Boolean;
        }

        public void Visit(Reference expression)
        {
            Question referenced = QuestionIdExists(expression.QuestionId) ? _questions[expression.QuestionId] : null;
            if (referenced == null)
            {
                _errorHandler.AddError(expression.LineNumber, "Reference to " + expression.QuestionId + " in expression does not exist.");
                _currentType = Type.Undefined;
            } else
            {
                _currentType = referenced.Type;
            }
        }

        public void Visit(And expression) => _currentType = TypeCheckBinaryLogical(expression, "&&");

        public void Visit(Or expression) => _currentType = TypeCheckBinaryLogical(expression, "||");

        public void Visit(LessThan expression) => _currentType = TypeCheckBinaryComparison(expression, "<");

        public void Visit(GreaterThan expression) => _currentType = TypeCheckBinaryComparison(expression, ">");

        public void Visit(GreaterThanOrEqual expression) => _currentType = TypeCheckBinaryComparison(expression, ">=");

        public void Visit(LessThanOrEqual expression) => _currentType = TypeCheckBinaryComparison(expression, "<=");

        public void Visit(NotEqual expression) => _currentType = TypeCheckBinaryComparison(expression, "!=");

        public void Visit(Equal expression) => _currentType = TypeCheckBinaryComparison(expression, "==");

        public void Visit(Add expression) => _currentType = TypeCheckBinaryArithmetic(expression, "+");

        public void Visit(Subtract expression) => _currentType = TypeCheckBinaryArithmetic(expression, "-");

        public void Visit(Multiply expression) => _currentType = TypeCheckBinaryArithmetic(expression, "*");

        public void Visit(Divide expression) => _currentType = TypeCheckBinaryArithmetic(expression, "/");
    }
}
