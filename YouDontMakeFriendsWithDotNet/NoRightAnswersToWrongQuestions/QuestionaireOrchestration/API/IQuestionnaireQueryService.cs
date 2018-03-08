using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.Output;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireOrchestration.API
{
    public interface IQuestionnaireQueryService
    {
        IEnumerable<IModelReference<IQuestionnaire>> GetAll();
    }

    internal class QuestionnaireQueryService : IQuestionnaireQueryService
    {
        public IEnumerable<IModelReference<IQuestionnaire>> GetAll()
        {
            throw new NotImplementedException();
        }
    }

    public interface IModelReference<T> where T : IDomainItem
    {
        Guid Id { get; }
        Guid DisplayValue { get; }
    }
}