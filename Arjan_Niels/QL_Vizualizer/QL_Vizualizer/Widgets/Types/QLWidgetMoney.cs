using System;
using QL_Vizualizer.Expression;

namespace QL_Vizualizer.Widgets.Types
{
    public class QLWidgetMoney : QLQuestionWidget<double>
    {
        public QLWidgetMoney(string identifyer, string text, IExpression<bool> activationExpression = null, IExpression<double> answerExpression = null) : base(identifyer, text, activationExpression, answerExpression)
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
    }
}
