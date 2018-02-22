using System;
using QL.Core.Ast.Visitors;

namespace QL.Core.Ast
{
    public sealed class NullNode : Node
    {
        public NullNode() : base(null)
        {
        }

        protected override void VisitNode(IVisitor visitor)
        {
            // Visiting a null node yields no result
        }
    }
}
