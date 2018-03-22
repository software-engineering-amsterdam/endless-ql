using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers.LeafTypes;
using QLVisualizer.Widgets.Collection;
using QLVisualizer.Widgets.Leaf;
using QLVisualizer.Widgets.Windows.Leaf.InputCreators;
using System.Collections.Generic;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Leaf
{
    public class StringBuilderWindows : WidgetLeafBuilderWindows<StringQuestionManager>
    {
        public StringBuilderWindows(StringQuestionManager elementManagerLeaf) : base(elementManagerLeaf)
        {
        }

        public override Control Create()
        {
            IInputCreator<Control, string> inputCreator = null;
            switch(_widgetType)
            {
                default:
                    inputCreator = new TextBoxCreator<string>();
                    break;
            }

            return inputCreator.CreateInput(_styler, new string[] { _elementManagerLeaf.Text }, _elementManagerLeaf as StringQuestionManager);
        }
    }
}
