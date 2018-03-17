using System;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Boolean
{
    internal class BooleanLiteralNode : 
        AstNodeBase, 
        IBooleanLiteralNode
    {
        private static readonly string[] TruthValues = { "true", "True", "TRUE" };
        private static readonly string[] FalseValues = { "false", "False", "FALSE" };

        public bool Value { get; }

        public BooleanLiteralNode(
            Guid id,
            string booleanString) : base(id, booleanString)
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
    }
}
