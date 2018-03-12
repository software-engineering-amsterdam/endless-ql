using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Elements.Managers.LeafTypes;

namespace QLVisualizer.Widgets
{
    // TODO: implement
    public class WidgetCreatorWindows : WidgetCreator<Control>
    {
        protected override Control CreateWidget(FormManager form)
        {
            throw new NotImplementedException();
        }

        protected override Control CreateWidget(PageManager page)
        {
            throw new NotImplementedException();
        }

        protected override Control CreateWidget(SectionManager section)
        {
            throw new NotImplementedException();
        }

        protected override Control CreateWidget(BoolQuestionManager boolQuestion)
        {
            throw new NotImplementedException();
        }

        protected override Control CreateWidget(IntQuestionManager intQuestion)
        {
            throw new NotImplementedException();
        }

        protected override Control CreateWidget(MoneyQuestionManager moneyQuestion)
        {
            throw new NotImplementedException();
        }

        protected override Control CreateWidget(StringQuestionManager stringQuestion)
        {
            throw new NotImplementedException();
        }
    }
}
