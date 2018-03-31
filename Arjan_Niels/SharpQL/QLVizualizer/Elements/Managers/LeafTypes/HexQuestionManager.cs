using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers.LeafTypes
{
    public class HexQuestionManager : QuestionElementManager<Hexadecimal>
    {
        public HexQuestionManager(string identifier, string text, ElementManagerCollection parent, ElementManagerController controller, ExpressionBool activationExpression = null, TypedExpressionValue<Hexadecimal> answerExpression = null)
            : base(identifier, text, parent, controller, activationExpression, answerExpression)
        {
        }

        protected override QuestionElementValue<Hexadecimal> ParseInput(string input)
        {
            Hexadecimal value = new Hexadecimal(0);
            bool isValid = true;
            try
            {
                value = Hexadecimal.FromString(input);
            }
            catch
            {
                isValid = false;
            }

            return new QuestionElementValue<Hexadecimal>(value, isValid);
        }
    }
}
