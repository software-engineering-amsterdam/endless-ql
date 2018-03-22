using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers.LeafTypes;
using QLVisualizer.Widgets.Collection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Leaf
{
    public class DoubleBuilderWindows : WidgetLeafBuilderWindows<DoubleQuestionManager>
    {
        public DoubleBuilderWindows(List<QLSValue> qlsElements, QLSWidgetSpecification widgetSpecification, DoubleQuestionManager elementManagerLeaf, IWidgetCollectionBuilder<Control> parent) : base(qlsElements, widgetSpecification, elementManagerLeaf, parent)
        {
        }

        public override Control Create()
        {
            throw new NotImplementedException();
        }
    }
}
