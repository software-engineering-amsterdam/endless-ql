using QLParser.AST.QLS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QLVisualizer.Elements.Managers
{
    public interface IStylable
    {
        void SetStyle(QLSStyle style);
        QLSStyle GetStyle();
    }
}
