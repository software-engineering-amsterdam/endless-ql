using QLParser.AST.QLS.Enums;
using System.Collections.Generic;

namespace QLParser.AST.QLS
{
    public class QLSStructuralNode : QLSNode
    {
        public QLSStructuralNode(QLSNodeType type, string id) : base(type, id)
        {
        }

        public QLSStructuralNode(QLSNodeType type, string id, IList<QLSStyle> styles) : base(type, id, styles)
        {
        }

        public override void Accept(IQLSVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
