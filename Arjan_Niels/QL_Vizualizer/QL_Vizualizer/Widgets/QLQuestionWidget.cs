using QL_Vizualizer.Controllers;
using System.Linq;

namespace QL_Vizualizer.Widgets
{
    public abstract class QLQuestionWidget<T> : QLWidget
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
        private Expression<T> _answerExpression;

        /// <summary>
        /// Indication if the answer is editable
        /// </summary>
        public bool Editable { get { return _answerExpression == null; } }

        public QLQuestionWidget(string identifyer, string text, WidgetController widgetController, Expression<bool> activationExpression = null, Expression<T> answerExpression = null) : base(identifyer, text, widgetController, activationExpression)
        {
            AnswerValue = default(T);
            IsAnswered = false;
            _answerExpression = answerExpression;

            if (answerExpression != null)
                foreach (string s in answerExpression.WidgetIDs)
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
        }

        /// <summary>
        /// Handles incoming updates for Answer values
        /// </summary>
        /// <param name="updatedIdentifyer">Updated widgetID</param>
        public override void ReceiveUpdate(string updatedIdentifyer)
        {
            base.ReceiveUpdate(updatedIdentifyer);
            if (_answerExpression.WidgetIDs.Contains(updatedIdentifyer))
            {
                SetAnswer(_answerExpression.Run());
                _widgetController.UpdateView(this);
            }
        }
    }
}
