using System;
using System.Collections.Generic;
using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.AST.Value;
using Assignment1.Model.QL.RenderTree;
using Assignment1.Model.QL.RenderTree.QLExpression;

namespace Assignment1.Converters
{
    public class QLASTExpressionToRenderExpression : IExpressionVisitor
    {
        private Expression _result;
        private Dictionary<string, RenderableQuestion> _questions;

        private QLASTExpressionToRenderExpression(Dictionary<string, RenderableQuestion> questions)
        {
            _questions = questions;
        }

        public static Expression Convert(IExpression expression, Dictionary<string, RenderableQuestion> questions)
        {
            var converter = new QLASTExpressionToRenderExpression(questions);
            expression.Accept(converter);
            return converter._result;
        }

        private Expression Convert(IExpression expression) => Convert(expression, _questions);

        public void Visit(QLBoolean value) => _result = new ExpressionValue(value.Value);

        public void Visit(QLInteger value) => _result = new ExpressionValue(value.Value);

        //public void Visit(Undefined undefined) => _result = null; //TODO: Implement

        public void Visit(QLString value) => _result = new ExpressionValue(value.Value);

        public void Visit(QLDate value) => _result = new ExpressionValue(value.Value);

        public void Visit(QLDecimal value) => _result = new ExpressionValue(value.Value);

        public void Visit(QLMoney value) => _result = new ExpressionValue(value.Value);

        public void Visit(Not expression) => _result = new ExpressionNot(Convert(expression));

        public void Visit(Reference expression) => _result = new ExpressionId(_questions[expression.QuestionId]);

        public void Visit(And expression) =>
            _result = new ExpressionAnd(Convert(expression.Left), Convert(expression.Right));

        public void Visit(Or expression) =>
            _result = new ExpressionOr(Convert(expression.Left), Convert(expression.Right));

        public void Visit(LessThan expression) =>
            _result = new ExpressionLess(Convert(expression.Left), Convert(expression.Right));

        public void Visit(GreaterThan expression) =>
            _result = new ExpressionGreater(Convert(expression.Left), Convert(expression.Right));

        public void Visit(GreaterThanOrEqual expression) =>
            _result = new ExpressionGreaterEqual(Convert(expression.Left), Convert(expression.Right));

        public void Visit(LessThanOrEqual expression) =>
            _result = new ExpressionLessEqual(Convert(expression.Left), Convert(expression.Right));

        public void Visit(NotEqual expression) =>
            _result = new ExpressionNotEqual(Convert(expression.Left), Convert(expression.Right));

        public void Visit(Equal expression) =>
            _result = new ExpressionEqual(Convert(expression.Left), Convert(expression.Right));

        public void Visit(Add expression) =>
            _result = new ExpressionAdd(Convert(expression.Left), Convert(expression.Right));

        public void Visit(Subtract expression) =>
            _result = new ExpressionSub(Convert(expression.Left), Convert(expression.Right));

        public void Visit(Multiply expression) =>
            _result = new ExpressionMult(Convert(expression.Left), Convert(expression.Right));

        public void Visit(Divide expression) =>
            _result = new ExpressionDiv(Convert(expression.Left), Convert(expression.Right));
    }
}
