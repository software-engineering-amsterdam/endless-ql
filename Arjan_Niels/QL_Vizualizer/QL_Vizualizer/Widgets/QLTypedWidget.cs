using System;

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

        public QLQuestionWidget(string identifyer, string question) : base(identifyer)
        {
            AnswerValue = default(T);
            IsAnswered = false;
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
    }
}
