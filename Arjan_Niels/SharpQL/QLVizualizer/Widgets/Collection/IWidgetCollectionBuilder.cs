using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QLVisualizer.Widgets.Collection
{
    public interface IWidgetCollectionBuilder<T> : IWidgetBuilder<T>
    {
        void AddChild(WidgetBuilder<T> builder);
    }
}
