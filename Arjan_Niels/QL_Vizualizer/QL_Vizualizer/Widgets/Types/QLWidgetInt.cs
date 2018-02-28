﻿using QL_Vizualizer.Expression;

namespace QL_Vizualizer.Widgets.Types
{
    public class QLWidgetInt : QLQuestionWidget<int>
    {
        public QLWidgetInt(string identifyer, string text, IExpression<bool> activationExpression = null, IExpression<int> answerExpression = null) : base(identifyer, text, activationExpression, answerExpression)
        {
        }

        public override ParsedWidgetValue<int> ParseInput(string input)
        {
            int result = 0;
            bool valid = int.TryParse(input, out result);
            return new ParsedWidgetValue<int>(Validate(result), valid);
        }
    }
}
