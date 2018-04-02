using QLParser.AST.QLS.Enums;
using System.Collections.Generic;

namespace QLParser.AST.QLS
{
    public class QLSQuestionNode : QLSNode
    {
        public QLSQuestionNode(Location location, string id) : base(location, QLSNodeType.Question, id)
        {
        }

        public QLSQuestionNode(Location location, string id, IList<QLSStyle> styles) : base(location, QLSNodeType.Question, id, styles)
        {
        }

        public override void Accept(IQLSVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
