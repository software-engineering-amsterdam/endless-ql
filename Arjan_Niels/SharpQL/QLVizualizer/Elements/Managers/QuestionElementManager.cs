using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;
using System.Collections.Generic;
using System.Linq;

namespace QLVisualizer.Elements.Managers
{
    public abstract class QuestionElementManager<T> : ElementManagerLeaf
    {
        /// <summary>
        /// Contains given answer, if not answered contains default value for T
        /// </summary>
        public QuestionElementValue<T> Answer { get; private set; }

        /// <summary>
        /// Indication if user gave an answer for this QLWidget
        /// </summary>
        public bool IsAnswered { get; private set; }

        /// <summary>
        /// Expression that creates the answer
        /// </summary>
        private TypedExpressionValue<T> _answerExpression;

        /// <summary>
        /// Indication if the answer is editable
        /// </summary>
        //public bool Editable { get { return _answerExpression == null; } }

        public delegate void TypedAnswerValueUpdate(QuestionElementValue<T> answer, bool calculated);
        public event TypedAnswerValueUpdate OnTypedAnswerValueUpdate;

        public QuestionElementManager(string identifyer, string text, ElementManager parent, ElementManagerController controller, ExpressionBool activationExpression = null, TypedExpressionValue<T> answerExpression = null) : 
            base(identifyer, text, "question", parent, controller, activationExpression)
        {
            Answer = new QuestionElementValue<T>(default(T), false);
            IsAnswered = false;
            _answerExpression = answerExpression;
            Editable = _answerExpression == null;
        }

        /// <summary>
        /// Validates the input value
        /// </summary>
        /// <param name="input">Input value</param>
        /// <returns>Correct value obtained from input</returns>
        protected virtual QuestionElementValue<T> Validate(T input)
        {
            // Default accepts all
            return new QuestionElementValue<T>(input, true);
        }

        protected abstract QuestionElementValue<T> ParseInput(string input);

        /// <summary>
        /// Set the value of the AnswerValue
        /// </summary>
        /// <param name="answer"></param>
        public void SetAnswer(T answer, bool fromNotify = false)
        {
            SetAnswer(Validate(answer));
            TriggerAnwerUpdate(fromNotify);
        }

        public void SetAnswer(string answer)
        {
            QuestionElementValue<T> parsedAnswer = ParseInput(answer);
            if(parsedAnswer.IsValid)
                SetAnswer(parsedAnswer);
            TriggerAnwerUpdate(!parsedAnswer.IsValid);
        }

        public void SetAnswer(QuestionElementValue<T> answer)
        {
            Answer = answer;
            IsAnswered = Answer.IsValid;
        }

        /// <summary>
        /// Handles incoming updates for Answer values
        /// </summary>
        /// <param name="updatedIdentifyer">Updated widgetID</param>
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
            return string.Format("<{0} identifier=\"{1}\" type=\"{2}\" valid=\"{3}\">{4}</{0}>", XMLElementName, Identifier, typeof(T), Answer.IsValid, Answer.Value);
        }

        public override string AnswerToString()
        {
            return Answer.Value.ToString();
        }
    }
}
