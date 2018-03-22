using QLParser.AST.QLS;
using System.Collections.Generic;

namespace QLVisualizer.Widgets
{
    public interface IStyleParser
    {
        void ParseStyle(List<QLSValue> qlsValues, out string[] errors);
    }
}
