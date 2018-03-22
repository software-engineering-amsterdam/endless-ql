using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers.CollectionTypes;
using QLVisualizer.Widgets.Collection;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Windows.Collection
{
    public class FormBuilderWindows : WidgetCollectionBuilderWindows<FormManager>
    {
        public FormBuilderWindows(List<QLSValue> qlsElements, FormManager elementManagerCollection) : base(qlsElements, elementManagerCollection, null)
        {
        }
    }
}
