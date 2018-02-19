using QL_Vizualizer.Controllers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QL_Vizualizer.Widgets.Types
{
    public class QLWidgetString : QLQuestionWidget<string>
    {
        public QLWidgetString(string identifyer, string text, Expression<bool> activationExpression = null, Expression<string> answerExpression = null) : base(identifyer, text, activationExpression, answerExpression)
        {
        }
    }
}
