using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers.LeafTypes;
using QLVisualizer.Widgets.Collection;
using QLVisualizer.Widgets.Leaf;
using QLVisualizer.Widgets.Windows.Leaf.InputCreators;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Leaf
{
    public class IntBuilderWindows : WidgetLeafBuilderWindows<IntQuestionManager>
    {
        public IntBuilderWindows(List<QLSValue> qlsElements, QLSWidgetSpecification widgetSpecification, IntQuestionManager elementManagerLeaf, IWidgetCollectionBuilder<Control> parent) : base(qlsElements, widgetSpecification, elementManagerLeaf, parent)
        {
        }

        public override Control Create()
        {
            IInputCreator<Control, int> inputCreator = null;
            switch (_widgetType)
            {
                default:
                    inputCreator = new TextBoxCreator<int>();
                    break;
            }

            return inputCreator.CreateInput(_styler, new string[] { _elementManagerLeaf.Text }, _elementManagerLeaf as IntQuestionManager);
        }
    }
}
