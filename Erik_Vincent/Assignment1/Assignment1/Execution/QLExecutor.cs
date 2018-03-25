using System.Collections.Generic;
using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.AST.Value;

namespace Assignment1.Execution
{
    public class QLExecutor : IQLASTVisitor
    {
        public IReadOnlyList<Question> VisibleQuestions => _visibleQuestions.AsReadOnly();

        private readonly QuestionForm _questionForm;
        private readonly Dictionary<string, IValue> _answers = new Dictionary<string, IValue>();
        private readonly Dictionary<string, Question> _questions = new Dictionary<string, Question>();
        private readonly List<Question> _visibleQuestions = new List<Question>();
        private readonly HashSet<string> _readOnlyQuestions = new HashSet<string>();

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

        public bool IsReadOnly(string questionId) => _readOnlyQuestions.Contains(questionId);

        public IValue GetAnswer(string questionId) => _answers.ContainsKey(questionId) ? _answers[questionId] : new Undefined();

        private void AddQuestion(Question question, bool readOnly)
        {
            _questions.Add(question.Id, question);
            _visibleQuestions.Add(question);
            if (readOnly) _readOnlyQuestions.Add(question.Id);
        }

        private void ClearQuestions()
        {
            _questions.Clear();
            _visibleQuestions.Clear();
            _readOnlyQuestions.Clear();
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
            ClearQuestions();
            VisitStatements(questionForm.Statements);
        }

        public void Visit(NormalQuestion question)
        {
            if (!_answers.ContainsKey(question.Id))
            {
                _answers.Add(question.Id, question.Answer);
            }
            AddQuestion(question, false);
        }

        public void Visit(ComputedQuestion question)
        {
            _answers.Add(question.Id, QLExpressionEvaluator.Evaluate(question.Computation, GetAnswer));
            AddQuestion(question, true);
        }

        public void Visit(IfStatement ifStatement)
        {
            VisitStatements(EvaluateCondition(ifStatement.Condition) ? ifStatement.ThenStatements : ifStatement.ElseStatements);
        }

        private bool EvaluateCondition(IExpression expression) => QLExpressionEvaluator.AsBool(QLExpressionEvaluator.Evaluate(expression, GetAnswer));
    }
}
