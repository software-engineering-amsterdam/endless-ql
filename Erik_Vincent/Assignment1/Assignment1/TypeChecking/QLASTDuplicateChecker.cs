using System;
using System.Collections.Generic;
using System.Linq;
using Assignment1.Model.QL.AST;

namespace Assignment1.TypeChecking
{
    public class QLASTDuplicateChecker : QLASTBaseVisitor
    {
        private readonly Dictionary<string, List<Question>> _labelQuestions = new Dictionary<string, List<Question>>();

        public static void CheckDuplicates(QuestionForm questionForm)
        {
            var checker = new QLASTDuplicateChecker();
            checker.Visit(questionForm);
        }

        private void CheckDuplicateLabels()
        {
            var duplicateLabelQuestions = _labelQuestions.Where(pair => pair.Value.Count > 1).ToArray();
            foreach (var labelQuestion in duplicateLabelQuestions)
            {
                var lineNumbers = labelQuestion.Value.Select(question => question.LineNumber.ToString());
                Console.WriteLine("Warning label:\"" + labelQuestion.Key + "\" is defined on lines: " + string.Join(", ", lineNumbers) + ".");
            }
        }

        public override void Visit(QuestionForm questionForm)
        {
            base.Visit(questionForm);
            CheckDuplicateLabels();
        }

        private void AddLabelQuestion(Question question)
        {
            if (!_labelQuestions.ContainsKey(question.Label))
                _labelQuestions.Add(question.Label, new List<Question>());
            _labelQuestions[question.Label].Add(question);
        }

        private void CheckDuplicateQuestion(Question question)
        {
            if (QuestionsInScope[question.Id].Count() > 1)
            {
                Console.WriteLine("Error found on line " + question.LineNumber + ": " + question.Id + " was already declared in this context.");
            }
        }

        public override void Visit(NormalQuestion question)
        {
            CheckDuplicateQuestion(question);
            AddLabelQuestion(question);
        }

        public override void Visit(ComputedQuestion question)
        {
            CheckDuplicateQuestion(question);
            AddLabelQuestion(question);
        }

        private QLASTDuplicateChecker() { }
    }
}
