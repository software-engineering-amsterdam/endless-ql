using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers.LeafTypes
{
    public class IntQuestionManager : QuestionElementManager<int>
    {
        public IntQuestionManager(string identifier, string text, ElementManagerCollection parent, ElementManagerController controller, ExpressionBool activationExpression = null, TypedExpressionValue<int> answerExpression = null) : 
            base(identifier, text, parent, controller, activationExpression, answerExpression)
        {
        }

        protected override QuestionElementValue<int> ParseInput(string input)
        {
            int result = 0;
            bool valid = int.TryParse(input, out result);
            return new QuestionElementValue<int>(result, valid);
        }
    }
}
