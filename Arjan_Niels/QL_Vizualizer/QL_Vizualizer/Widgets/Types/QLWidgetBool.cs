using QL_Vizualizer.Expression;

namespace QL_Vizualizer.Widgets.Types
{
    public class QLWidgetBool : QLQuestionWidget<bool>
    {
        public QLWidgetBool(string identifyer, string text, IExpression<bool> activationExpression = null, IExpression<bool> answerExpression = null) : base(identifyer, text, activationExpression, answerExpression)
        {
        }
    }
}
