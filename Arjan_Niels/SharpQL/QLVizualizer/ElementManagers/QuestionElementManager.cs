using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;
using System.Linq;

namespace QLVisualizer.ElementManagers
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

        public QuestionElementManager(string identifyer, string text, ElementManager parent,ExpressionBool activationExpression = null, TypedExpressionValue<T> answerExpression = null) : base(identifyer, text, "question", parent, activationExpression)
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
        /// Sets widgetcontroller and subscribes to value changes
        /// </summary>
        /// <param name="controller">Controller to use</param>
        public override void SetController(ElementManagerController controller)
        {
            base.SetController(controller);

            // Subscribe for answer expressions
            if (_answerExpression != null)
                foreach (string s in _answerExpression.UsedWidgetIDs)
                    _widgetController.ReceiveUpdates(s, this);
        }

        /// <summary>
        /// Set the value of the AnswerValue
        /// </summary>
        /// <param name="answer"></param>
        public void SetAnswer(T answer)
        {
            Answer = Validate(answer);
            IsAnswered = true;

            // Send update to the controller
            if (_widgetController != null)
                _widgetController.ValueUpdate(Identifier);
        }

        /// <summary>
        /// Handles incoming updates for Answer values
        /// </summary>
        /// <param name="updatedIdentifyer">Updated widgetID</param>
        public override void ReceiveUpdate(string updatedIdentifyer)
        {
            base.ReceiveUpdate(updatedIdentifyer);
            if (_answerExpression != null && _answerExpression.UsedWidgetIDs.Contains(updatedIdentifyer))
            {
                SetAnswer(_answerExpression.Result);

                // Update view of this widget since the value is calculated
                _widgetController.UpdateView(this);
            }
        }

        public override string ToXML()
        {
            return string.Format("<{0} identifier=\"{1}\" type=\"{2}\" valid=\"{3}\">{4}</{0}>", XMLElementName, Identifier, typeof(T), Answer.IsValid, Answer.Value);
        }
    }
}
