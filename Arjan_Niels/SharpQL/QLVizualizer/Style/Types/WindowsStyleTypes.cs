using QLParser.AST.Nodes;
using QLParser.AST.QLS;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QLVisualizer.Style.Types
{
    public static class WindowsStyleTypes
    {
        public static Dictionary<string, QValueType> DefaultStyleElements = new Dictionary<string, QValueType>()
        {
            {"width", QValueType.INTEGER },
            {"height", QValueType.INTEGER },
            {"color", QValueType.HEX },
            {"font", QValueType.TEXT },
            {"fontsize", QValueType.INTEGER },
        };
    }
}
