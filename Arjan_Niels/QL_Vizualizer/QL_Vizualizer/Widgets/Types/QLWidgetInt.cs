using QL_Vizualizer.Expression;

namespace QL_Vizualizer.Widgets.Types
{
    public class QLWidgetInt : QLQuestionWidget<int>
    {
        public QLWidgetInt(string identifyer, string text, IExpression<bool> activationExpression = null, IExpression<int> answerExpression = null) : base(identifyer, text, activationExpression, answerExpression)
        {
        }
    }
}
