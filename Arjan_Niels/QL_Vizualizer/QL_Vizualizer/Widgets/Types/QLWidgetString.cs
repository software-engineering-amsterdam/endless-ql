using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QL_Vizualizer.Widgets.Types
{
    public class QLWidgetString : QLQuestionWidget<string>
    {
        public QLWidgetString(string identifyer, string text, Expression<bool> activationExpression, Expression<string> answerExpression) : base(identifyer, text, activationExpression, answerExpression)
        {
        }
    }
}
