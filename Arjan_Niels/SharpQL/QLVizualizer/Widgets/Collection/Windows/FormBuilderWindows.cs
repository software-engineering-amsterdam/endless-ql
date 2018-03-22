using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers.CollectionTypes;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Collection.Windows
{
    public class FormBuilderWindows : WidgetCollectionBuilder<Control, FormManager>
    {
        public FormBuilderWindows(List<QLSValue> qlsElements, FormManager elementManagerCollection) : base(qlsElements, elementManagerCollection, null)
        {
        }

        protected override Control Create(IEnumerable<Control> children)
        {
            throw new NotImplementedException();
        }

        protected override bool IsCompatible(string styleElement)
        {
            throw new NotImplementedException();
        }
    }
}
