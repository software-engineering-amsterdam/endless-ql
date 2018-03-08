using QLVisualizer.Expression.Types;

namespace QLVisualizer.ElementManagers.LeafTypes
{
    public class StringElementManager : QuestionElementManager<string>
    {
        public StringElementManager(string identifyer, string text, ElementManager parent, ExpressionBool activationExpression = null, TypedExpressionValue<string> answerExpression = null) : base(identifyer, text, parent, activationExpression, answerExpression)
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
