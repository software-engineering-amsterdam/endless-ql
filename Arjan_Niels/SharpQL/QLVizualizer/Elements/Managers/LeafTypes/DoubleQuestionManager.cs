using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers.LeafTypes
{
    public class DoubleQuestionManager : QuestionElementManager<double>
    {
        public DoubleQuestionManager(string identifier, string text, ElementManagerCollection parent, ElementManagerController controller, ExpressionBool activationExpression = null, TypedExpressionValue<double> answerExpression = null) 
            : base(identifier, text, parent, controller, activationExpression, answerExpression)
        {
        }

        protected override QuestionElementValue<double> ParseInput(string input)
        {
            double result = 0;
            bool valid = double.TryParse(input, out result);
            return new QuestionElementValue<double>(result, valid);
        }
    }
}
