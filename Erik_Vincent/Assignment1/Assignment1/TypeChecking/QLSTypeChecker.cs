using Assignment1.Model.QL;
using Assignment1.Model.QL.AST;
using Assignment1.Model.QLS.AST;
using Assignment1.Model.QLS.AST.Style;
using Assignment1.Model.QLS.AST.Style.Widget;
using Assignment1.Parser;
using System.Collections.Generic;
using System.Linq;

namespace Assignment1.TypeChecking
{
    // TODO: add line numbers to QLS nodes
    public class QLSTypeChecker : IQLSASTVisitor, IQLASTVisitor, IStyleVisitor
    {
        private ParseErrorHandler _errorHandler = new ParseErrorHandler();
        private Dictionary<string, Question> _qlQuestions = new Dictionary<string, Question>();
        private Dictionary<string, QuestionStyle> _qlsQuestions = new Dictionary<string, QuestionStyle>();
        private string _currentQuestion = "";

        public void TypeCheckStylesheet(StyleSheet styleSheet, QuestionForm questionForm)
        {
            questionForm.Accept(this);
            styleSheet.Accept(this);
            _currentQuestion = "";
            if (_errorHandler.HasErrors)
            {
                _errorHandler.ThrowParseException();
            }
        }

        private void TypeCheckQLQuestionsInQLSStylesheet(int lineNumber)
        {
            var qlIDs = _qlQuestions.Keys.ToList();
            var qlsIDs = _qlsQuestions.Keys.ToList();
            IEnumerable<string> disjunction = qlIDs.Except(qlsIDs);
            if (disjunction.Count() > 0)
            {
                string missingQuestions = "";
                foreach (string question in disjunction)
                {
                    if (disjunction.Last().Equals(question))
                        missingQuestions += question;
                    else
                        missingQuestions += question + ", ";
                }
                _errorHandler.AddError(lineNumber, "The questions " + missingQuestions + " are defined in questionaire form but not used in stylesheet.");
            }
        }

        public void Visit(StyleSheet styleSheet)
        {
            foreach (Page page in styleSheet.Pages)
            {
                page.Accept(this);
            }
            TypeCheckQLQuestionsInQLSStylesheet(styleSheet.LineNumber);
        }

        public void Visit(Page page)
        {
            foreach (Model.QLS.AST.Statement statement in page.Statements)
            {
                statement.Accept(this);
            }
        }

        public void Visit(Section section)
        {
            foreach (Model.QLS.AST.Statement statement in section.Contents)
            {
                statement.Accept(this);
            }
        }

        public void Visit(QuestionStyle questionStyle)
        {
            _currentQuestion = questionStyle.Id;
            if (!_qlQuestions.ContainsKey(_currentQuestion))
            {
                // Collect error that question ID is used but doesn't exist in QL form
                _errorHandler.AddError(questionStyle.LineNumber, "Reference to undefined question with id '" + _currentQuestion + "'.");
            }
            if (_qlsQuestions.ContainsKey(_currentQuestion))
            {
                // Collect error that question ID is already used before
                _errorHandler.AddError(questionStyle.LineNumber, "Question with id '" + _currentQuestion + "' already used in stylesheet.");
            }
            foreach (IStyle style in questionStyle.Styles)
            {
                style.Accept(this);
            }
            _qlsQuestions.Add(questionStyle.Id, questionStyle);
        }

        public void Visit(DefaultStyle defaultStyle)
        {
            // TODO
        }

        public void Visit(QuestionForm questionForm)
        {
            foreach (Model.QL.AST.Statement statement in questionForm.Statements)
            {
                statement.Accept(this);
            }
        }

        public void Visit(NormalQuestion question) => _qlQuestions.Add(question.Id, question);

        public void Visit(ComputedQuestion question) => _qlQuestions.Add(question.Id, question);

        public void Visit(IfStatement ifStatement)
        {
            foreach (Model.QL.AST.Statement statement in ifStatement.ThenStatements)
            {
                statement.Accept(this);
            }
            foreach (Model.QL.AST.Statement statement in ifStatement.ElseStatements)
            {
                statement.Accept(this);
            }
        }

        public void Visit(Width style) { }

        public void Visit(Font style) { }

        public void Visit(FontSize style) { }

        public void Visit(Color style) { }

        public void Visit(Slider style)
        {
            // TODO: What are the constraints?
        }

        public void Visit(SpinBox style)
        {
            Type questionType = _qlQuestions.ContainsKey(_currentQuestion) ? _qlQuestions[_currentQuestion].Type : Type.Undefined;
            if (questionType == Type.Undefined)
            {
                // Collect error that question could not be found
                _errorHandler.AddError(1, "Reference to undefined question with id '" + _currentQuestion + "'.");
            }
            if (!questionType.IsNumeric())
            {
                // Collect error that radio cannot be applied to non-numeric question types
                _errorHandler.AddError(1, "Spinbox widget cannot be applied to question of type " + questionType.ToString());
            }
        }

        public void Visit(TextBox style)
        {
            Type questionType = _qlQuestions.ContainsKey(_currentQuestion) ? _qlQuestions[_currentQuestion].Type : Type.Undefined;
            if (questionType == Type.Undefined)
            {
                // Collect error that question could not be found
                _errorHandler.AddError(1, "Reference to undefined question with id '" + _currentQuestion + "'.");
            }
            if (questionType == Type.Boolean)
            {
                // Collect error that radio cannot be applied to boolean question types
                _errorHandler.AddError(1, "Textbox widget cannot be applied to question of type " + questionType.ToString());
            }
        }

        public void Visit(Radio style)
        {
            Type questionType = _qlQuestions.ContainsKey(_currentQuestion) ? _qlQuestions[_currentQuestion].Type : Type.Undefined;
            if (questionType == Type.Undefined)
            {
                // Collect error that question could not be found
                _errorHandler.AddError(1, "Reference to undefined question with id '" + _currentQuestion + "'.");
            }
            if (questionType.IsNumeric())
            {
                // Collect error that radio cannot be applied to numeric question types
                _errorHandler.AddError(1, "Radio widget cannot be applied to question of type " + questionType.ToString());
            }
        }

        public void Visit(CheckBox style)
        {
            Type questionType = _qlQuestions.ContainsKey(_currentQuestion) ? _qlQuestions[_currentQuestion].Type : Type.Undefined;
            if (questionType == Type.Undefined)
            {
                // Collect error that question could not be found
                _errorHandler.AddError(1, "Reference to undefined question with id '" + _currentQuestion + "'.");
            }
            if (questionType.IsNumeric())
            {
                // Collect error that radio cannot be applied to numeric question types
                _errorHandler.AddError(1, "Checkbox widget cannot be applied to question of type " + questionType.ToString());
            }
        }

        public void Visit(DropDown style)
        {
            Type questionType = _qlQuestions.ContainsKey(_currentQuestion) ? _qlQuestions[_currentQuestion].Type : Type.Undefined;
            if (questionType == Type.Undefined)
            {
                // Collect error that question could not be found
                _errorHandler.AddError(1, "Reference to undefined question with id '" + _currentQuestion + "'.");
            }
            if (questionType != Type.Boolean)
            {
                // Collect error that radio cannot be applied to non-boolean question types
                _errorHandler.AddError(1, "Dropdown widget cannot be applied to question of type " + questionType.ToString());
            }
        }
    }
}
