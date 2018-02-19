using QL_Vizualizer.Controllers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QL_Vizualizer.Widgets.Types
{
    public class QLWidgetBool : QLQuestionWidget<bool>
    {
        public QLWidgetBool(string identifyer, string text, WidgetController widgetController, Expression<bool> activationExpression = null, Expression<bool> answerExpression = null) : base(identifyer, text, widgetController, activationExpression, answerExpression)
        {
        }
    }
}
