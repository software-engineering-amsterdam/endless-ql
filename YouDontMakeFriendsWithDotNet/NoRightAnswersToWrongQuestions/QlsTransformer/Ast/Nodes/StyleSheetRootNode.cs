using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QlsTransformer.Ast.Nodes
{
    internal class StyleSheetRootNode : AstNodeBase, IStyleSheetRootNode
    {
        public StyleSheetRootNode(
            Guid id, 
            string definition,
            string styleSheetName) 
            : base(id, definition)
        {
            StyleSheetName = styleSheetName;
        }

        public string StyleSheetName { get; }
    }

    public interface IStyleSheetRootNode : IAstNode
    {
        string StyleSheetName { get; }
    }
}
