using System;
using System.Collections.Generic;
using System.Linq;
using QuestionaireOrchestration.Models;
using QuestionaireOrchestration.QueryServices.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QuestionaireOrchestration.QueryServices
{
    internal sealed class QuestionnaireDefintionQueryService : IModelQueryService<QuestionnaireDefinitionModel>
    {
        private readonly IDomainItemLocator m_domainItemLocator;

        public QuestionnaireDefintionQueryService(IDomainItemLocator domainItemLocator)
        {
            m_domainItemLocator = domainItemLocator;
        }
        public QuestionnaireDefinitionModel FindByName(string name)
        {
            return GetAll().FirstOrDefault(x => x.Name == name);
        }

        public QuestionnaireDefinitionModel FindByReference(Guid reference)
        {
            return GetAll().FirstOrDefault(x => x.Id == reference);
        }

        public IEnumerable<QuestionnaireDefinitionModel> GetAll()
        {
            return m_domainItemLocator
                .GetAll<IQuestionnaireRootNode>()
                .Select(x => new QuestionnaireDefinitionModel(x.Id, x.DisplayName));
        }
    }
}


//public IEnumerable<ExampleModel> GetAll()
//=> m_models.GetAll();

//public ExampleModel FindByReference(Reference<ExampleModel> reference)
//{
//return reference != null
//? GetAll().FirstOrDefault(x => x.Id == reference.Id)
//: null;
//}

//public ExampleModel FindByName(string name)
//{
//return GetAll().FirstOrDefault(x => x.Name == name);
//}