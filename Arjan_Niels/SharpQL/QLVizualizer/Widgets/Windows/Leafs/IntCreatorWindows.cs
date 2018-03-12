using QLVisualizer.Elements.Managers.LeafTypes;
using System;

namespace QLVisualizer.Widgets.Windows.Leafs
{
    public class IntCreatorWindows : WidgetLeafCreator<IntQuestionManager>
    {
        public IntCreatorWindows(WidgetCreator parent, IntQuestionManager elementManagerLeaf) : base(parent, elementManagerLeaf)
        {
        }
    }
}
