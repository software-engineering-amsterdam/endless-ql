using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers.CollectionTypes;
using System.Collections.Generic;

namespace QLVisualizer.Widgets.Windows.Collection
{
    public class FormBuilderWindows : WidgetCollectionBuilderWindows<FormManager>
    {
        public FormBuilderWindows(List<QLSValue> qlsElements, FormManager elementManagerCollection) : base(qlsElements, elementManagerCollection, null)
        {
        }

        protected override string GetTitleText()
        {
            return string.Format("Form: {0}", _elementManagerCollection.Text);
        }
    }
}
