using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.ElementManagers.LeafTypes
{
    public class IntQuestionManager : QuestionElementManager<int>
    {
        public IntQuestionManager(string identifyer, string text, ElementManager parent, ElementManagerController controller, ExpressionBool activationExpression = null, TypedExpressionValue<int> answerExpression = null) : 
            base(identifyer, text, parent, controller, activationExpression, answerExpression)
        {
        }

        public override QuestionElementValue<int> ParseInput(string input)
        {
            int result = 0;
            bool valid = int.TryParse(input, out result);
            return new QuestionElementValue<int>(result, valid);
        }
    }
}
