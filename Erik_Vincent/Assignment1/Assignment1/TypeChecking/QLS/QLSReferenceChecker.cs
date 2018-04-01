using System.Collections.Generic;
using Assignment1.Model.QL.AST;
using Assignment1.Model.QLS.AST;

namespace Assignment1.TypeChecking.QLS
{
    public class QLSReferenceChecker : IQLSASTVisitor, IQLASTVisitor
    {
        private readonly MessageContainer _messages = new MessageContainer();
        private readonly HashSet<string> _qlQuestions = new HashSet<string>();
        private readonly HashSet<string> _qlsQuestions = new HashSet<string>();

        public static (IEnumerable<string> errors, IEnumerable<string> warnings)
            CheckQuestionReferences(StyleSheet styleSheet, QuestionForm questionForm)
        {
            var checker = new QLSReferenceChecker();
            questionForm.Accept(checker);
            styleSheet.Accept(checker);
            return checker._messages.ToTuple();
        }

        private QLSReferenceChecker() { }

        public void Visit(StyleSheet styleSheet)
        {
            foreach (var page in styleSheet.Pages)
                page.Accept(this);
            _qlQuestions.SymmetricExceptWith(_qlsQuestions);
            foreach (var question in _qlQuestions)
            {
                if (_qlsQuestions.Contains(question))
                    _messages.AddError("Question '" + question + "' is not in the QL program.");
                else
                    _messages.AddError("Question '" + question + "' is in the QL program but not placed by the QLS program.");
            }
        }

        public void Visit(Page page)
        {
            foreach (var statement in page.Statements)
                statement.Accept(this);
        }

        public void Visit(Section section)
        {
            foreach (var statement in section.Contents)
                statement.Accept(this);
        }

        public void Visit(QuestionStyle questionStyle)
        {
            if (!_qlsQuestions.Add(questionStyle.Id))
                _messages.AddError("Line: " + questionStyle.LineNumber + " | Question '" + questionStyle.Id + "' was placed already.");
        }

        public void Visit(DefaultStyle defaultStyle) { }

        public void Visit(QuestionForm questionForm)
        {
            foreach (var statement in questionForm.Statements)
                statement.Accept(this);
        }

        public void Visit(NormalQuestion question) => _qlQuestions.Add(question.Id);

        public void Visit(ComputedQuestion question) => _qlQuestions.Add(question.Id);

        public void Visit(IfStatement ifStatement)
        {
            foreach (var statement in ifStatement.ThenStatements)
                statement.Accept(this);
            foreach (var statement in ifStatement.ElseStatements)
                statement.Accept(this);
        }
    }
}
