using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers.LeafTypes;
using QLVisualizer.Widgets.Collection;
using QLVisualizer.Widgets.Leaf;
using QLVisualizer.Widgets.Windows.Leaf.InputCreators;
using System.Collections.Generic;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Leaf
{
    public class BoolBuilderWindows : WidgetLeafBuilderWindows<BoolQuestionManager>
    {
        public BoolBuilderWindows(List<QLSValue> qlsElements, QLSWidgetSpecification widgetSpecification, BoolQuestionManager elementManagerLeaf, IWidgetCollectionBuilder<Control> parent) : base(qlsElements, widgetSpecification, elementManagerLeaf, parent)
        {
        }

        public override Control Create()
        {
            IInputCreator<Control, bool> inputCreator = null;
            switch (_widgetType)
            {
                default:
                    inputCreator = new CheckBoxCreator<bool>();
                    break;
            }

            return inputCreator.CreateInput(_styler, new string[] { _elementManagerLeaf.Text }, _elementManagerLeaf as BoolQuestionManager);
        }
    }
}
