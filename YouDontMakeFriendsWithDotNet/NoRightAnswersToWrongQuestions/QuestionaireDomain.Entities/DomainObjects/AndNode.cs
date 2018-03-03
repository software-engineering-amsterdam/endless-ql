using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class AndNode : AstNodeBase, IAndNode
    {
        public AndNode(
            Guid id,
            Reference<IBooleanLogicNode> leftExpression,
            Reference<IBooleanLogicNode> rightExpression) : base(id)
        {
            LeftExpression = leftExpression;
            RightExpression = rightExpression;
        }

        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }

        public Reference<IBooleanLogicNode> LeftExpression { get; }
        public Reference<IBooleanLogicNode> RightExpression { get; }
    }
}
