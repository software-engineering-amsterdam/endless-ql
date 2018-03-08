using QL_Vizualizer.Expression;
using QL_Vizualizer.Expression.Types;

namespace QL_Vizualizer.Widgets.Types
{
    public class QLWidgetString : QLQuestionWidget<string>
    {
        public QLWidgetString(string identifyer, string text, ExpressionBool activationExpression = null, TypedExpressionValue<string> answerExpression = null) : base(identifyer, text, activationExpression, answerExpression)
        {
        }

        public override ParsedWidgetValue<string> ParseInput(string input)
        {
            return new ParsedWidgetValue<string>(input, true);
        }

        public override string ToXML()
        {
            return string.Format("<stringValue>{0}</stringValue>", AnswerValue);
        }
    }
}
