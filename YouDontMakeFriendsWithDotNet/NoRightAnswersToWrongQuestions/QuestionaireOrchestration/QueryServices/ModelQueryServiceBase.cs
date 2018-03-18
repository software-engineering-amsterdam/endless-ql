using System;
using System.Collections.Generic;
using System.Linq;
using QuestionaireOrchestration.Models;
using QuestionaireOrchestration.QueryServices.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QuestionaireOrchestration.QueryServices
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