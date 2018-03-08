using QLVisualizer.Expression;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.ElementManagers.Types
{
    public class BoolElementManager : QuestionElementManager<bool>
    {
        public BoolElementManager(string identifyer, string text, ElementManager parent, ExpressionBool activationExpression = null, TypedExpressionValue<bool> answerExpression = null) : base(identifyer, text, parent, activationExpression, answerExpression)
        {
        }

        public override QuestionElementValue<bool> ParseInput(string input)
        {
            bool result = false;
            bool valid = bool.TryParse(input, out result);
            return new QuestionElementValue<bool>(Validate(result), valid);
        }

        public override string ToXML()
        {
            return string.Format("<boolValue>{0}</boolValue>", AnswerValue);
        }
    }
}
