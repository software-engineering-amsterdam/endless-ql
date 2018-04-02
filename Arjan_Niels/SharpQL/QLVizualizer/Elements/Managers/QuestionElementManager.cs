using QLParser.AST.QL;
using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;
using System.Collections.Generic;

namespace QLVisualizer.Elements.Managers
{
    public abstract class QuestionElementManager<T> : ElementManagerLeaf
    {
        public QuestionElementValue<T> Answer { get; private set; }

        public bool IsAnswered { get; private set; }

        private TypedExpressionValue<T> _answerExpression;

        public QuestionElementManager(string identifier, string text, ElementManagerCollection parent, ElementManagerController controller, ExpressionBool activationExpression = null, TypedExpressionValue<T> answerExpression = null) :
            base(identifier, text, "question", parent, controller, activationExpression)
        {
            Answer = new QuestionElementValue<T>(default(T), false);
            IsAnswered = false;
            _answerExpression = answerExpression;
            Editable = _answerExpression == null;
        }

        protected virtual QuestionElementValue<T> Validate(T input)
        {
            // Default accepts all input
            return new QuestionElementValue<T>(input, true);
        }

        protected abstract QuestionElementValue<T> ParseInput(string input);

        public void SetAnswer(T answer, bool fromNotify = false)
        {
            SetAnswer(Validate(answer));
            TriggerAnwerUpdate(fromNotify);
        }

        public void SetAnswer(string answer)
        {
            QuestionElementValue<T> parsedAnswer = ParseInput(answer);
            if (parsedAnswer.IsValid)
                SetAnswer(parsedAnswer);
            TriggerAnwerUpdate(!parsedAnswer.IsValid);
        }

        public void SetAnswer(QuestionElementValue<T> answer)
        {
            Answer = answer;
            IsAnswered = Answer.IsValid;
        }

        public override void RegisterListeners()
        {
            base.RegisterListeners();

            if (_answerExpression != null)
            {
                Dictionary<string, ElementManagerLeaf> managers = _elementManagerController.Form.FindLeafsByID(_answerExpression.UsedIdentifiers);
                foreach (ElementManagerLeaf manager in managers.Values)
                    manager.OnAnswerValueUpdate += DependendValueUpdate;
            }
        }

        private void DependendValueUpdate(ElementManagerLeaf elementManagerLeaf, bool calculated)
        {
            SetAnswer(_answerExpression.Result, true);
        }

        public override string ToXML()
        {
            return string.Format("<{0} identifier=\"{1}\" type=\"{2}\" valid=\"{3}\">{4}</{0}>", XMLElementName, Identifier, GetQValueType(), Answer.IsValid, Answer.Value);
        }

        public override string AnswerToString()
        {
            if (Answer.Value != null)
                return Answer.Value.ToString();
            return string.Empty;
        }

        protected abstract QValueType GetQValueType();
    }
}
