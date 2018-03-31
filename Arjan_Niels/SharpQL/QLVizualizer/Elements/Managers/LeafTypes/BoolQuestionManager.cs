using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers.LeafTypes
{
    public class BoolQuestionManager : QuestionElementManager<bool>
    {
        public BoolQuestionManager(string identifier, string text, ElementManagerCollection parent, ElementManagerController controller, ExpressionBool activationExpression = null, TypedExpressionValue<bool> answerExpression = null) :
            base(identifier, text, parent, controller, activationExpression, answerExpression)
        {
        }

        protected override QuestionElementValue<bool> ParseInput(string input)
        {
            bool result = false;
            bool valid = bool.TryParse(input, out result);
            return new QuestionElementValue<bool>(result, valid);
        }
    }
}
