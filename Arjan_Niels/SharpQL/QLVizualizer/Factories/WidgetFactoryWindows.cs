using QLVisualizer.Elements.Managers;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Elements.Managers.LeafTypes;
using QLVisualizer.Widgets;
using QLVisualizer.Widgets.Collection;
using QLVisualizer.Widgets.Windows.Collection;
using QLVisualizer.Widgets.Windows.Leaf;
using System;
using System.Windows.Forms;

namespace QLVisualizer.Factories
{
    public class WidgetFactoryWindows
    {
        public static WidgetBuilder<Control> GetBuilder(ElementManager elementManager, IWidgetCollectionBuilder parent)
        {
            switch (elementManager)
            {
                case ElementManagerLeaf elementManagerLeaf:
                    return GetLeafBuilder(elementManagerLeaf);
                case ElementManagerCollection elementManagerCollection:
                    return GetCollectionBuilder(elementManagerCollection);
            }

            throw new NotImplementedException();
        }

        private static WidgetBuilder<Control> GetCollectionBuilder(ElementManagerCollection elementManagerCollection)
        {
            WidgetCollectionBuilder<Control> builder = null;
            switch (elementManagerCollection)
            {
                case FormManager formManager:
                    builder = new FormBuilderWindows(formManager);
                    break;
                case PageManager pageManager:
                    builder = new PageBuilderWindows(pageManager);
                    break;
                case SectionManager sectionManager:
                    builder = new SectionBuilderWindows(sectionManager);
                    break;
                default:
                    throw new NotImplementedException();
            }

            foreach (ElementManager child in elementManagerCollection.Children)
                builder.AddChild(GetBuilder(child, builder));

            return builder;
        }

        private static WidgetBuilder<Control> GetLeafBuilder(ElementManagerLeaf elementManagerLeaf)
        {
            switch (elementManagerLeaf)
            {
                case BoolQuestionManager boolQuestion:
                    return new BoolBuilderWindows(boolQuestion);
                case DoubleQuestionManager doubleQuestion:
                    return new DoubleBuilderWindows(doubleQuestion);
                case IntQuestionManager intQuestion:
                    return new IntBuilderWindows(intQuestion);
                case MoneyQuestionManager moneyQuestion:
                    return new MoneyBuilderWindows(moneyQuestion);
                case StringQuestionManager stringQuestion:
                    return new StringBuilderWindows(stringQuestion);
                case HexQuestionManager hexQuestion:
                    return new HexBuilderWindows(hexQuestion);
            }
            throw new NotImplementedException();
        }
    }
}
