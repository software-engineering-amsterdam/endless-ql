using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers.LeafTypes
{
    public class HexQuestionManager : QuestionElementManager<Hexadecimal>
    {
        public HexQuestionManager(string identifyer, string text, ElementManagerCollection parent, ElementManagerController controller, ExpressionBool activationExpression = null, TypedExpressionValue<Hexadecimal> answerExpression = null) 
            : base(identifyer, text, parent, controller, activationExpression, answerExpression)
        {
        }

        protected override QuestionElementValue<Hexadecimal> ParseInput(string input)
        {
            Hexadecimal value = new Hexadecimal(0);
            bool isValid = true;
            try
            {
                value = new Hexadecimal(input);
            }
            catch
            {
                // TODO: WHAT TO DO? throw error or return value with 'valid = false'
                isValid = false;
            }
            return new QuestionElementValue<Hexadecimal>(value, isValid);
        }
    }
}
