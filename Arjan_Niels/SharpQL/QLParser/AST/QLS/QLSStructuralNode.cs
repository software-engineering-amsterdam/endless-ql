using QLParser.AST.QLS.Enums;
using System.Collections.Generic;

namespace QLParser.AST.QLS
{
    public class QLSStructuralNode : QLSNode
    {
        public QLSStructuralNode(Location location, QLSNodeType type, string id) : base(location, type, id)
        {
        }

        public QLSStructuralNode(Location location, QLSNodeType type, string id, IList<QLSStyle> styles) : base(location, type, id, styles)
        {
        }

        public override void Accept(IQLSVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
