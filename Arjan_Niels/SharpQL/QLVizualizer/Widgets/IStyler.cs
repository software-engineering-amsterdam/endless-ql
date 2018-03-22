using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QLVisualizer.Widgets
{
    public interface IStyler<T>
    {
        T StyleElement(T element);
    }
}
