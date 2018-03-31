using QLVisualizer.Controllers;
using QLVisualizer.Expression.Types;
using System;

namespace QLVisualizer.Elements.Managers.LeafTypes
{
    public class MoneyQuestionManager : QuestionElementManager<double>
    {
        private const int _numberOfDecimals = 2;

        public MoneyQuestionManager(string identifier, string text, ElementManagerCollection parent, ElementManagerController controller, ExpressionBool activationExpression = null, TypedExpressionValue<double> answerExpression = null) :
            base(identifier, text, parent, controller, activationExpression, answerExpression)
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
            return new QuestionElementValue<double>(Math.Round(input, _numberOfDecimals), true);
        }
    }
}
