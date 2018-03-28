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
            string styleSheetName,
            IEnumerable<Reference<IPageNode>> pages) 
            : base(id, definition)
        {
            StyleSheetName = styleSheetName;
            Pages = pages;
        }

        public string StyleSheetName { get; }
        public IEnumerable<Reference<IPageNode>> Pages { get; }
    }

    public interface IStyleSheetRootNode : IAstNode
    {
        string StyleSheetName { get; }
    }
}
    