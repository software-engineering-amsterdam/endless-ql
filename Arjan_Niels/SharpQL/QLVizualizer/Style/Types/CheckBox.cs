using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using QLParser.AST.Nodes;

namespace QLVisualizer.Style.Types
{
    public class CheckBox : StyleChecker
    {
        public CheckBox() : base(WindowsStyleTypes.DefaultStyleElements)
        {
        }
    }
}
