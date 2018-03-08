﻿using QLVisualizer.Expression.Types;

namespace QLVisualizer.ElementManagers.LeafTypes
{
    public class BoolQuestionManager : QuestionElementManager<bool>
    {
        public BoolQuestionManager(string identifyer, string text, ElementManager parent, ExpressionBool activationExpression = null, TypedExpressionValue<bool> answerExpression = null) : base(identifyer, text, parent, activationExpression, answerExpression)
        {
        }

        public override QuestionElementValue<bool> ParseInput(string input)
        {
            bool result = false;
            bool valid = bool.TryParse(input, out result);
            if (valid)
                return Validate(result);
            else
                return new QuestionElementValue<bool>(false, false);
        }
    }
}