using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers.LeafTypes
{
    public class BoolQuestionManager : QuestionElementManager<bool>
    {
        public BoolQuestionManager(string identifyer, string text, ElementManager parent, ElementManagerController controller, ExpressionBool activationExpression = null, TypedExpressionValue<bool> answerExpression = null) : 
            base(identifyer, text, parent, controller, activationExpression, answerExpression)
        {
        }

        public override QuestionElementValue<bool> ParseInput(string input)
        {
            bool result = false;
            bool valid = bool.TryParse(input, out result);
            if (valid)
                return Validate(result);
            else
                return new QuestionElementValue<bool>(false, false);
        }
    }
}
