using System.Collections.Generic;
using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.AST.Value;

namespace Assignment1.Execution
{
    public class QLExecutor : IQLASTVisitor
    {
        private readonly QuestionForm _questionForm;
        private readonly Dictionary<string, IValue> _answers = new Dictionary<string, IValue>();
        private readonly Dictionary<string, Question> _questions = new Dictionary<string, Question>();

        public QLExecutor(QuestionForm questionForm)
        {
            _questionForm = questionForm;
            _questionForm.Accept(this);
        }

        public Question GetQuestion(string questionId) => _questions[questionId];

        public void SetAnswer(string questionId, IValue answer)
        {
            _answers[questionId] = answer;
            _questionForm.Accept(this);
        }

        private void VisitStatements(IEnumerable<Statement> statements)
        {
            foreach (var statement in statements)
            {
                statement.Accept(this);
            }
        }

        public void Visit(QuestionForm questionForm)
        {
            _questions.Clear();
            VisitStatements(questionForm.Statements);
        }

        public void Visit(NormalQuestion question)
        {
            if (!_answers.ContainsKey(question.Id))
            {
                _answers.Add(question.Id, question.Answer);
            }
            _questions.Add(question.Id, question);
        }

        public void Visit(ComputedQuestion question)
        {
            _questions.Add(question.Id, question);
        }

        public void Visit(IfStatement ifStatement)
        {
            VisitStatements(EvaluateCondition(ifStatement.Condition) ? ifStatement.ThenStatements : ifStatement.ElseStatements);
        }

        private bool EvaluateCondition(IExpression expression) => QLExpressionEvaluator.AsBool(QLExpressionEvaluator.Evaluate(expression, _answers));
    }
}
