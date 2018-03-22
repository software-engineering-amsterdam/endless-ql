using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers.LeafTypes;
using QLVisualizer.Widgets.Collection;
using QLVisualizer.Widgets.Leaf;
using QLVisualizer.Widgets.Windows.Leaf.InputCreators;
using System.Collections.Generic;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Leaf
{
    public class MoneyBuilderWindows : WidgetLeafBuilderWindows<MoneyQuestionManager>
    {
        public MoneyBuilderWindows(List<QLSValue> qlsElements, QLSWidgetSpecification widgetSpecification, MoneyQuestionManager elementManagerLeaf, IWidgetCollectionBuilder<Control> parent) : base(qlsElements, widgetSpecification, elementManagerLeaf, parent)
        {
        }

        public override Control Create()
        {
            IInputCreator<Control, double> inputCreator = null;
            switch (_widgetType)
            {
                default:
                    inputCreator = new TextBoxCreator<double>();
                    break;
            }

            return inputCreator.CreateInput(_styler, new string[] { _elementManagerLeaf.Text }, _elementManagerLeaf as MoneyQuestionManager);
        }
    }
}
