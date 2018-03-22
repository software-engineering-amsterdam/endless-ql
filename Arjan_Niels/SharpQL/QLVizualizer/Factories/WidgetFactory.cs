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
        public static IWidgetBuilder<Control> GetBuilder(ElementManager elementManager, IWidgetCollectionBuilder<Control> parent)
        {
            switch (elementManager)
            {
                case ElementManagerLeaf elementManagerLeaf:
                    return GetLeafBuilder(elementManagerLeaf, parent);
                case ElementManagerCollection elementManagerCollection:
                    return GetCollectionBuilder(elementManagerCollection, parent);
            }

            throw new NotImplementedException();
        }

        private static IWidgetCollectionBuilder<Control> GetCollectionBuilder(ElementManagerCollection elementManagerCollection, IWidgetCollectionBuilder<Control> parent)
        {
            IWidgetCollectionBuilder<Control> builder = null;
            switch (elementManagerCollection)
            {
                case FormManager formManager:
                    builder = new FormBuilderWindows(null, formManager);
                    break;
                case PageManager pageManager:
                    builder = new PageBuilderWindows(null, pageManager, parent);
                    break;
                case SectionManager sectionManager:
                    builder = new SectionBuilderWindows(null, sectionManager, parent);
                    break;
                default:
                    throw new NotImplementedException();
            }

            foreach (ElementManager child in elementManagerCollection.Children)
                builder.AddChild(GetBuilder(child, builder));

            return builder;
        }

        private static IWidgetBuilder<Control> GetLeafBuilder(ElementManagerLeaf elementManagerLeaf, IWidgetCollectionBuilder<Control> parent)
        {
            switch (elementManagerLeaf)
            {
                case BoolQuestionManager boolQuestion:
                    return new BoolBuilderWindows(null, null, boolQuestion, parent);
                case DoubleQuestionManager doubleQuestion:
                    return new DoubleBuilderWindows(null, null, doubleQuestion, parent);
                case IntQuestionManager intQuestion:
                    return new IntBuilderWindows(null, null, intQuestion, parent);
                case MoneyQuestionManager moneyQuestion:
                    return new MoneyBuilderWindows(null, null, moneyQuestion, parent);
                case StringQuestionManager stringQuestion:
                    return new StringBuilderWindows(null, null, stringQuestion, parent);
            }
            throw new NotImplementedException();
        }
    }
}
