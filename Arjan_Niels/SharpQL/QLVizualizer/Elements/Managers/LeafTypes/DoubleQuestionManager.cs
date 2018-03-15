using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers.LeafTypes
{
    public class DoubleQuestionManager : QuestionElementManager<double>
    {
        public DoubleQuestionManager(string identifyer, string text, ElementManager parent, ElementManagerController controller, ExpressionBool activationExpression = null, TypedExpressionValue<double> answerExpression = null) 
            : base(identifyer, text, parent, controller, activationExpression, answerExpression)
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
