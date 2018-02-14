using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QL_Vizualizer.Widgets.Types
{
    public class QLWidgetInt : QLQuestionWidget<int>
    {
        public QLWidgetInt(string identifyer, string text, Expression<bool> activationExpression, Expression<int> answerExpression) : base(identifyer, text, activationExpression, answerExpression)
        {
        }
    }
}
