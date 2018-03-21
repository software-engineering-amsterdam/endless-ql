using QLParser.AST.QLS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QLVisualizer.Style
{
    public interface IStyleCheckable
    {
        ICollection<QLSValue> ParseStyle(ICollection<QLSValue> qlsValues);
    }
}
