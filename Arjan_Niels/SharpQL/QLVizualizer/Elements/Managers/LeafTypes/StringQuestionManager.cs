using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers.LeafTypes
{
    public class StringQuestionManager : QuestionElementManager<string>
    {
        public StringQuestionManager(string identifier, string text, ElementManagerCollection parent, ElementManagerController controller, ExpressionBool activationExpression = null, TypedExpressionValue<string> answerExpression = null) : 
            base(identifier, text, parent, controller, activationExpression, answerExpression)
        {
        }

        protected override QuestionElementValue<string> ParseInput(string input)
        {
            return new QuestionElementValue<string>(input, true);
        }
    }
}
