using QLParser.AST.Nodes;
using QLParser.AST.QLS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QLVisualizer.Widgets
{
    public interface IStyleParser
    {
        Dictionary<string, string> ParseStyle();


    }
}
