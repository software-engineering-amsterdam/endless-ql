using QL_Vizualizer.Controllers;
using QL_Vizualizer.Expression.Types;
using System.Linq;

namespace QL_Vizualizer.ElementManagers
{
    public abstract class QLQuestionWidget<T> : ElementManager
    {
        /// <summary>
        /// Contains given answer, if not answered contains default value for T
        /// </summary>
        public T AnswerValue { get; private set; }

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

        public QLQuestionWidget(string identifyer, string text, ExpressionBool activationExpression = null, TypedExpressionValue<T> answerExpression = null) : base(identifyer, text, activationExpression)
        {
            AnswerValue = default(T);
            IsAnswered = false;
            _answerExpression = answerExpression;
        }

        /// <summary>
        /// Validates the input value
        /// </summary>
        /// <param name="input">Input value</param>
        /// <returns>Correct value obtained from input</returns>
        public virtual T Validate(T input)
        {
            return input;
        }

        public abstract ParsedWidgetValue<T> ParseInput(string input);

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
            AnswerValue = answer;
            IsAnswered = true;

            // Send update to the controller
            if (_widgetController != null)
                _widgetController.ValueUpdate(Identifyer);
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
    }
}
