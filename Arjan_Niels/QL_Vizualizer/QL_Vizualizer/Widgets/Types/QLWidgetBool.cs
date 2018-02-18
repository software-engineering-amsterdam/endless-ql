using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QL_Vizualizer.Widgets.Types
{
    public class QLWidgetBool : QLQuestionWidget<bool>
    {
        public QLWidgetBool(string identifyer, string text, Expression<bool> activationExpression, Expression<bool> answerExpression) : base(identifyer, text, activationExpression, answerExpression)
        {
        }
    }
}
