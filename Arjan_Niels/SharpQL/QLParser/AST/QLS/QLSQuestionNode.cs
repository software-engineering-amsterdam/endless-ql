using QLParser.AST.QLS.Enums;
using System.Collections.Generic;

namespace QLParser.AST.QLS
{
    public class QLSQuestionNode : QLSNode
    {
        public QLSQuestionNode(string id) : base(QLSNodeType.Question, id)
        {
        }

        public QLSQuestionNode(string id, IList<QLSStyle> styles) : base(QLSNodeType.Question, id, styles)
        {
        }

        public override void Accept(IQLSVisitor visitor)
        {
            visitor.Visit(this);
        }
    }
}
