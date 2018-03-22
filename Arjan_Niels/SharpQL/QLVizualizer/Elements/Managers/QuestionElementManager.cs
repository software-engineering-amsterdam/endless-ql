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
        public bool Editable { get { return _answerExpression == null; } }


        public delegate void AnswerValueUpdate(QuestionElementValue<T> answer, bool calculated);
        public event AnswerValueUpdate OnAnswerValueUpdate;

        public QuestionElementManager(string identifyer, string text, ElementManager parent, ElementManagerController controller, ExpressionBool activationExpression = null, TypedExpressionValue<T> answerExpression = null) : 
            base(identifyer, text, "question", parent, controller, activationExpression)
        {
            Answer = new QuestionElementValue<T>(default(T), false);
            IsAnswered = false;
            _answerExpression = answerExpression;
        }

        /// <summary>
        /// Validates the input value
        /// </summary>
        /// <param name="input">Input value</param>
        /// <returns>Correct value obtained from input</returns>
        public virtual QuestionElementValue<T> Validate(T input)
        {
            // Default accepts all
            return new QuestionElementValue<T>(input, true);
        }

        public abstract QuestionElementValue<T> ParseInput(string input);

        /// <summary>
        /// Set the value of the AnswerValue
        /// </summary>
        /// <param name="answer"></param>
        public void SetAnswer(T answer, bool fromNotify = false)
        {
            Answer = Validate(answer);
            IsAnswered = Answer.IsValid;

            OnAnswerValueUpdate?.Invoke(Answer, fromNotify);
        }

        /// <summary>
        /// Handles incoming updates for Answer values
        /// </summary>
        /// <param name="updatedIdentifyer">Updated widgetID</param>
        public override void RegisterListeners()
        {
            base.RegisterListeners();
            Dictionary<string, ElementManager> managers = _elementManagerController.Form.FindByID(_answerExpression.UsedIdentifiers);
            foreach (ElementManager manager in managers.Values) ;
            // TODO: cast to questionElementManager to listen to event

                //if(manager.GetType().IsSubclassOf(QuestionElementManager))
        }

        public override string ToXML()
        {
            return string.Format("<{0} identifier=\"{1}\" type=\"{2}\" valid=\"{3}\">{4}</{0}>", XMLElementName, Identifier, typeof(T), Answer.IsValid, Answer.Value);
        }
    }
}
