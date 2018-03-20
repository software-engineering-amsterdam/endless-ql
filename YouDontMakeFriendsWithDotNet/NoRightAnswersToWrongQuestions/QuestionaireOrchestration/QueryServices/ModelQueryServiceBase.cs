using System;
using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireOrchestration.Models;
using QuestionnaireOrchestration.QueryServices.Interfaces;

namespace QuestionnaireOrchestration.QueryServices
{
    internal abstract class ModelQueryServiceBase<T> : IModelQueryService<T> where T : DomainItemModel
    {
        protected readonly IDomainItemLocator DomainItemLocator;

        protected ModelQueryServiceBase(IDomainItemLocator domainItemLocator)
        {
            DomainItemLocator = domainItemLocator;
        }

        public T FindByName(string name)
        {
            return GetAll().FirstOrDefault(x => x.Name == name);
        }

        public T FindByReference(Guid reference)
        {
            return GetAll().FirstOrDefault(x => x.Id == reference);
        }

        public abstract IEnumerable<T> GetAll();
    }
}