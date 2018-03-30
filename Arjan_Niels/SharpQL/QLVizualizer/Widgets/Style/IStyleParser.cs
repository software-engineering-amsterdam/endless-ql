using QLParser.AST.QLS;
using System.Collections.Generic;

namespace QLVisualizer.Widgets
{
    public interface IStyleParser
    {
        void ParseStyle(QLSStyle qlsValues, out string[] errors);
    }
}
