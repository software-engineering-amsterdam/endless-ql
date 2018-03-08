using QLVizualizer.Expression;
using QLVizualizer.Expression.Types;

namespace QLVizualizer.ElementManagers.Types
{
    public class StringElementManager : QuestionElementManager<string>
    {
        public StringElementManager(string identifyer, string text, ExpressionBool activationExpression = null, TypedExpressionValue<string> answerExpression = null) : base(identifyer, text, activationExpression, answerExpression)
        {
        }

        public override QuestionElementValue<string> ParseInput(string input)
        {
            return new QuestionElementValue<string>(input, true);
        }

        public override string ToXML()
        {
            return string.Format("<stringValue>{0}</stringValue>", AnswerValue);
        }
    }
}
