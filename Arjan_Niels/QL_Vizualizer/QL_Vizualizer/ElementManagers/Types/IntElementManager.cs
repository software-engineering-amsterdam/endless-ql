using QLVizualizer.Expression;
using QLVizualizer.Expression.Types;

namespace QLVizualizer.ElementManagers.Types
{
    public class IntElementManager : QuestionElementManager<int>
    {
        public IntElementManager(string identifyer, string text, ExpressionBool activationExpression = null, TypedExpressionValue<int> answerExpression = null) : base(identifyer, text, activationExpression, answerExpression)
        {
        }

        public override QuestionElementValue<int> ParseInput(string input)
        {
            int result = 0;
            bool valid = int.TryParse(input, out result);
            return new QuestionElementValue<int>(Validate(result), valid);
        }

        public override string ToXML()
        {
            return string.Format("<intValue>{0}</intValue>", AnswerValue);
        }
    }
}
