using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Relational
{
    internal class TextVariableNode : 
        VariableNodeBase, 
        ITextVariableNode
    {
        public TextVariableNode(
            Guid id,
            string variableName)
            : base(id, variableName)
        {
        }
    }
}