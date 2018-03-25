using System;
using System.Collections.Generic;
using System.Linq;
using Assignment1.Model.QL;
using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.AST.Value;
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
        private readonly Stack<IExpression> _conditions = new Stack<IExpression>(new[] { new QLBoolean(true) });
        private readonly Dictionary<string, Model.QL.RenderTree.RenderableQuestion> _questions = new Dictionary<string, Model.QL.RenderTree.RenderableQuestion>();
        private bool _isComputed = false;

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
            var condition = ifStatement.Condition;
            _conditions.Push(new And(_conditions.Peek(), condition));
            foreach (var statement in ifStatement.ThenStatements)
            {
                statement.Accept(this);
            }
            _conditions.Push(new Not(_conditions.Pop()));
            foreach (var statement in ifStatement.ElseStatements)
            {
                statement.Accept(this);
            }
            _conditions.Pop();
        }

        public void Visit(NormalQuestion question)
        {
            _isComputed = false;
            var result = QLASTQuestionToQuestion(question);
            result.Computation = question.Answer;
            _questions.Add(question.Id, result);
        }

        public void Visit(ComputedQuestion question)
        {
            _isComputed = true;
            var result = QLASTQuestionToQuestion(question);
            result.Computation = question.Computation;
            _questions.Add(question.Id, result);
        }

        private Model.QL.RenderTree.RenderableQuestion QLASTQuestionToQuestion(Question question)
        {
            Model.QL.RenderTree.RenderableQuestion result;
            switch (question.Type)
            {
                case Type.Boolean:
                    result = new RenderableQuestionBool(question.Id, question.Label, _isComputed);
                    break;
                case Type.Integer:
                    result = new RenderableQuestionInt(question.Id, question.Label, _isComputed);
                    break;
                case Type.String:
                    result = new RenderableQuestionString(question.Id, question.Label, _isComputed);
                    break;
                case Type.Date:
                    result = new RenderableQuestionDate(question.Id, question.Label, _isComputed);
                    break;
                case Type.Decimal:
                    result = new RenderableQuestionDecimal(question.Id, question.Label, _isComputed);
                    break;
                case Type.Money:
                    result = new RenderableQuestionMoney(question.Id, question.Label, _isComputed);
                    break;
                default:
                    throw new ArgumentOutOfRangeException();
            }
            result.Condition = _conditions.Peek();
            return result;
        }
    }
}
