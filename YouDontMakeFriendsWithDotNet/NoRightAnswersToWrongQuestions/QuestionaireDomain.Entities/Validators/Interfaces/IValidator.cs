using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Validators.MetaData;

namespace QuestionnaireDomain.Entities.Validators.Interfaces
{
    public interface IValidator<T> where T : IAstNode
    {
        IEnumerable<ValidationMetaData> Validate(
            DomainId<T> rootNode);
    }
}