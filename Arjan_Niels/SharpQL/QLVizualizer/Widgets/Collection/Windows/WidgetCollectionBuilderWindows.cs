using QLParser.AST.QLS;
using QLVisualizer.Elements.Managers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace QLVisualizer.Widgets.Collection.Windows
{
    public class WidgetCollectionBuilderWindows<T> : WidgetCollectionBuilder<Control, T> where T : ElementManagerCollection
    {
        public WidgetCollectionBuilderWindows(List<QLSValue> qlsElements, T elementManagerCollection, IWidgetCollectionBuilder<Control> parent) : base(qlsElements, elementManagerCollection, parent)
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
