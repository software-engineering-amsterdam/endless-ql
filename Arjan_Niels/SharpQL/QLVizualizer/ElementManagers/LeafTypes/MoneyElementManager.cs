using System;
using QLVisualizer.Expression.Types;

namespace QLVisualizer.ElementManagers.LeafTypes
{
    public class MoneyElementManager : QuestionElementManager<double>
    {
        public MoneyElementManager(string identifyer, string text, ElementManager parent, ExpressionBool activationExpression = null, TypedExpressionValue<double> answerExpression = null) : base(identifyer, text, parent, activationExpression, answerExpression)
        {
        }

        public override QuestionElementValue<double> ParseInput(string input)
        {
            double inputValue = 0;
            bool valid = double.TryParse(input, out inputValue);

            return new QuestionElementValue<double>(Validate(inputValue), valid);
        }

        public override double Validate(double input)
        {
            return Math.Round(input, 2);
        }

        public override string ToXML()
        {
            return string.Format("<moneyValue>{0}</moneyValue>", AnswerValue);
        }
    }
}
