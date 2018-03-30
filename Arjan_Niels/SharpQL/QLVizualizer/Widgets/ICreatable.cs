using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QLVisualizer.Widgets
{
    public interface ICreatable<T>
    {
        T Create();
    }
}
