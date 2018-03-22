using System;
using System.Collections.Generic;
using System.Linq;
using Assignment1.Model.QL;
using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.RenderTree;
using Assignment1.Model.QL.RenderTree.QLExpression;
using Question = Assignment1.Model.QL.AST.Question;
using QuestionForm = Assignment1.Model.QL.AST.QuestionForm;
using Type = Assignment1.Model.QL.AST.Type;

namespace Assignment1.Converters
{
    public class QLASTToRenderTree : IQLASTVisitor
    {
        private Model.QL.RenderTree.QuestionForm _result;
        private readonly Stack<Expression> _conditions = new Stack<Expression>(new[] { new ExpressionValue(true) });
        private readonly Dictionary<string, Model.QL.RenderTree.Question> _questions = new Dictionary<string, Model.QL.RenderTree.Question>();

        private QLASTToRenderTree() { }

        public static Model.QL.RenderTree.QuestionForm Convert(QuestionForm form)
        {
            var converter = new QLASTToRenderTree();
            form.Accept(converter);
            return converter._result;
        }

        public void Visit(QuestionForm questionForm)
        {
            _questions.Clear();
            foreach (var statement in questionForm.Statements)
            {
                statement.Accept(this);
            }
            _result = new Model.QL.RenderTree.QuestionForm(questionForm.Id, _questions.Values.ToList());
        }

        public void Visit(IfStatement ifStatement)
        {
            var condition = QLASTExpressionToRenderExpression.Convert(ifStatement.Condition, _questions);
            _conditions.Push(new ExpressionAnd(_conditions.Peek(), condition));
            foreach (var statement in ifStatement.ThenStatements)
            {
                statement.Accept(this);
            }
            _conditions.Push(new ExpressionNot(_conditions.Pop()));
            foreach (var statement in ifStatement.ElseStatements)
            {
                statement.Accept(this);
            }
            _conditions.Pop();
        }

        public void Visit(NormalQuestion question)
        {
            var result = QLASTQuestionToQuestion(question);
            result.Value = QLASTExpressionToRenderExpression.Convert(question.Answer, _questions)?.Evaluate() ?? result.Value; //TODO: Cleanup when Convert method is implemented.
            _questions.Add(question.Id, result);
        }

        public void Visit(ComputedQuestion question)
        {
            var result = QLASTQuestionToQuestion(question);
            result.Computation = QLASTExpressionToRenderExpression.Convert(question.Computation, _questions);
            _questions.Add(question.Id, result);
        }

        private Model.QL.RenderTree.Question QLASTQuestionToQuestion(Question question)
        {
            Model.QL.RenderTree.Question result;
            switch (question.Type)
            {
                case Type.Boolean:
                    result = new QuestionBool(question.Id, question.Label);
                    break;
                case Type.Integer:
                    result = new QuestionInt(question.Id, question.Label);
                    break;
                case Type.String:
                    result = new QuestionString(question.Id, question.Label);
                    break;
                case Type.Date:
                    result = new QuestionDate(question.Id, question.Label);
                    break;
                case Type.Decimal:
                    result = new QuestionDecimal(question.Id, question.Label);
                    break;
                case Type.Money:
                    result = new QuestionMoney(question.Id, question.Label);
                    break;
                default:
                    throw new ArgumentOutOfRangeException();
            }
            result.Condition = _conditions.Peek();
            return result;
        }
    }
}
