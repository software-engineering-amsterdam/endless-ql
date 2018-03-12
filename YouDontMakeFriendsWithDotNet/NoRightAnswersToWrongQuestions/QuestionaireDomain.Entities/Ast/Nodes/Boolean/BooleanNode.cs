using System;
using System.Linq;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionaireDomain.Entities.Ast.Nodes.Common;

namespace QuestionaireDomain.Entities.Ast.Nodes.Boolean
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
