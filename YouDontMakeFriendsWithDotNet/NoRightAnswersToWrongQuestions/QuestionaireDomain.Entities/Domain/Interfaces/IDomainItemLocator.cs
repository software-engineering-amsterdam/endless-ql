using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;

namespace QuestionnaireDomain.Entities.Domain.Interfaces
{
    public interface IDomainItemLocator
    {
        TDomainItem Get<TDomainItem>(Guid id) where TDomainItem : IDomainItem;
        IEnumerable<TDomainItem> GetAll<TDomainItem>() where TDomainItem : IDomainItem;

        DomainId<TDomainItem> GetRef<TDomainItem>(Guid id) where TDomainItem : IDomainItem;
        IEnumerable<DomainId<TDomainItem>> GetAllRefs<TDomainItem>() where TDomainItem : IDomainItem;

        bool Exists<TDomainItem>(Guid id) where TDomainItem : IDomainItem;
        DomainId<IQuestionnaireRootNode> GetRoot(DomainId<IQuestionNode> node);
    }
}