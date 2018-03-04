using System;
using System.Configuration;
using System.Linq;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class BooleanLiteralNode : AstNodeBase, ILiteralNode
    {
        private static readonly string[] TruthValues = { "true", "True", "TRUE" };
        private static readonly string[] FalseValues = { "false", "False", "FALSE" };

        public BooleanLiteralNode(
            Guid id,
            string booleanString) : base(id)
        {
            if (TruthValues.Contains(booleanString))
            {
                Value = true;
            }
            else if (FalseValues.Contains(booleanString))
            {
                Value = false;
            }
            else
            {
                throw new QlParserException($"The value '{booleanString}' could not be resolved to True or False ",null);
            }
        }

        public override void Accept(IAstVisitor visitor)
        {
            (visitor as IAstVisitor<ILiteralNode>)?.Visit(this);
        }

        public bool Value { get; }
    }
}
