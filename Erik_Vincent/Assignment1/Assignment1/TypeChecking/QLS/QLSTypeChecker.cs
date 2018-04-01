using System.Collections.Generic;
using Assignment1.Model.QL.AST;
using Assignment1.Model.QLS.AST;
using Assignment1.Model.QLS.AST.Style;
using Assignment1.Model.QLS.AST.Style.Widget;

namespace Assignment1.TypeChecking.QLS
{
    public class QLSTypeChecker : IQLSASTVisitor, IQLASTVisitor, IStyleVisitor
    {
        private readonly MessageContainer _messages = new MessageContainer();
        private readonly Dictionary<string, Type> _questionTypes = new Dictionary<string, Type>();
        private Type _current;

        public static (IEnumerable<string> errors, IEnumerable<string> warnings)
            CheckTypes(StyleSheet styleSheet, QuestionForm questionForm)
        {
            var checker = new QLSTypeChecker();
            questionForm.Accept(checker);
            styleSheet.Accept(checker);
            return checker._messages.ToTuple();
        }

        private QLSTypeChecker() { }

        public void Visit(StyleSheet styleSheet)
        {
            foreach (var page in styleSheet.Pages)
                page.Accept(this);
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
            _current = _questionTypes[questionStyle.Id];
            foreach (var style in questionStyle.Styles)
                style.Accept(this);
        }

        public void Visit(DefaultStyle defaultStyle)
        {
            _current = defaultStyle.Type;
            foreach (var style in defaultStyle.Styles)
                style.Accept(this);
        }

        public void Visit(QuestionForm questionForm)
        {
            foreach (var statement in questionForm.Statements)
                statement.Accept(this);
        }

        public void Visit(NormalQuestion question) => _questionTypes.Add(question.Id, question.Type);

        public void Visit(ComputedQuestion question) => _questionTypes.Add(question.Id, question.Type);

        public void Visit(IfStatement ifStatement)
        {
            foreach (var statement in ifStatement.ThenStatements)
                statement.Accept(this);
            foreach (var statement in ifStatement.ElseStatements)
                statement.Accept(this);
        }

        public void Visit(Width style) { }

        public void Visit(Font style) { }

        public void Visit(FontSize style) { }

        public void Visit(Color style) { }

        public void Visit(Slider style)
        {
            if (!_current.IsNumeric())
                _messages.AddError("Line: " + style.LineNumber + " | Slider widget cannot be applied to question of type " + _current + ".");
        }

        public void Visit(SpinBox style)
        {
            if (!_current.IsNumeric())
                _messages.AddError("Line: " + style.LineNumber + " | Spinbox widget cannot be applied to question of type " + _current + ".");
        }

        public void Visit(TextBox style)
        {
            if (_current == Type.Boolean)
                _messages.AddError("Line: " + style.LineNumber + "| Textbox widget cannot be applied to question of type " + _current + ".");
        }

        public void Visit(Radio style)
        {
            if (_current != Type.Boolean)
                _messages.AddError("Line: " + style.LineNumber + " | Radio widget cannot be applied to question of type " + _current + ".");
        }

        public void Visit(CheckBox style)
        {
            if (_current != Type.Boolean)
                _messages.AddError("Line: " + style.LineNumber + " | Checkbox widget cannot be applied to question of type " + _current + ".");
        }

        public void Visit(DropDown style)
        {
            if (_current != Type.Boolean)
                _messages.AddError("Line: " + style.LineNumber + " | Dropdown widget cannot be applied to question of type " + _current + ".");
        }
    }
}
