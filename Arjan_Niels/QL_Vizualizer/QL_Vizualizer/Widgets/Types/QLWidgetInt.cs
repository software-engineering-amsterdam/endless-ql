using QL_Vizualizer.Controllers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QL_Vizualizer.Widgets.Types
{
    public class QLWidgetInt : QLQuestionWidget<int>
    {
        public QLWidgetInt(string identifyer, string text, Expression<bool> activationExpression = null, Expression<int> answerExpression = null) : base(identifyer, text, activationExpression, answerExpression)
        {
        }
    }
}
