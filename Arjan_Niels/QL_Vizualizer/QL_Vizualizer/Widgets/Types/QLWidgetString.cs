using QL_Vizualizer.Expression;

namespace QL_Vizualizer.Widgets.Types
{
    public class QLWidgetString : QLQuestionWidget<string>
    {
        public QLWidgetString(string identifyer, string text, IExpression<bool> activationExpression = null, IExpression<string> answerExpression = null) : base(identifyer, text, activationExpression, answerExpression)
        {
        }

        public override ParsedWidgetValue<string> ParseInput(string input)
        {
            return new ParsedWidgetValue<string>(input, true);
        }
    }
}
