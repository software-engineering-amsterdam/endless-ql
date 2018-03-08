using System;
using QL_Vizualizer.Expression;
using QL_Vizualizer.Expression.Types;

namespace QL_Vizualizer.ElementManagers.Types
{
    public class MoneyElementManager : QLQuestionWidget<double>
    {
        public MoneyElementManager(string identifyer, string text, ExpressionBool activationExpression = null, TypedExpressionValue<double> answerExpression = null) : base(identifyer, text, activationExpression, answerExpression)
        {
        }

        public override ParsedWidgetValue<double> ParseInput(string input)
        {
            double inputValue = 0;
            bool valid = double.TryParse(input, out inputValue);

            return new ParsedWidgetValue<double>(Validate(inputValue), valid);
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
