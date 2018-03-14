using System;
using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.Elements.Managers.LeafTypes
{
    public class MoneyQuestionManager : QuestionElementManager<double>
    {
        public MoneyQuestionManager(string identifyer, string text, ElementManager parent, ElementManagerController controller, ExpressionBool activationExpression = null, TypedExpressionValue<double> answerExpression = null) : 
            base(identifyer, text, parent, controller, activationExpression, answerExpression)
        {
        }

        protected override QuestionElementValue<double> ParseInput(string input)
        {
            double inputValue = 0;
            bool valid = double.TryParse(input, out inputValue);
            if (valid)
                return Validate(inputValue);
            else
                return new QuestionElementValue<double>(0, false);
        }

        protected override QuestionElementValue<double> Validate(double input)
        {
            return new QuestionElementValue<double>(Math.Round(input, 2), true);
        }
    }
}
