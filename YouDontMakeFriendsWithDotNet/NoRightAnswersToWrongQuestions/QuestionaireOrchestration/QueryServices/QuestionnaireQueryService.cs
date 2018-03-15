//using System.Collections.Generic;
//using System.Linq;
//using QuestionaireOrchestration.Models;
//using QuestionaireOrchestration.QueryServices.Interfaces;
//using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
//using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
//using QuestionnaireDomain.Entities.Domain.Interfaces;
//using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;
//using QuestionnaireDomain.Entities.Output.Tools.Interfaces;
//using QuestionnaireInfrastructure.API;

//namespace QuestionaireOrchestration.QueryServices
//{
//    internal class QuestionnaireQueryService : IQuestionnaireQueryService
//    {
//        private readonly IQuestionnaireDefinitionLoader m_definitionLoader;
//        private readonly IQuestionnaireAstCreator m_astCreator;
//        private readonly IDomainItemLocator m_domainItemLocator;
//        private readonly IQuestionnaireOutputCreator m_outputCreator;

//        internal QuestionnaireQueryService(
//            IQuestionnaireDefinitionLoader definitionLoader,
//            IQuestionnaireAstCreator astCreator,
//            IDomainItemLocator domainItemLocator,
//            IQuestionnaireOutputCreator outputCreator)
//        {
//            m_definitionLoader = definitionLoader;
//            m_astCreator = astCreator;
//            m_domainItemLocator = domainItemLocator;
//            m_outputCreator = outputCreator;
//        }

//        public IEnumerable<ModelReference<IQuestionnaireOutputItem>> GetAll()
//        {
//            var existingQuestionnaires = m_domainItemLocator
//                .GetAll<IQuestionnaireOutputItem>();

//            if (!existingQuestionnaires.Any())
//            {
//                //var examples = m_definitionLoader.Load();
//                //foreach (var example in examples)
//                //{
//                //    m_astCreator.Create(example);
//                //}
//                const string example = @"form PaulTest { q1: ""hello Q"" integer }";
//                m_astCreator.Create(example);

//                var questionnaireRefs = m_domainItemLocator
//                    .GetAllRefs<IQuestionnaireRootNode>();

//                foreach (var questionnaireRootNode in questionnaireRefs)
//                {
//                    if (m_outputCreator.Validate(questionnaireRootNode))
//                    {
//                        m_outputCreator.Create(questionnaireRootNode);
//                    }
//                }

//                existingQuestionnaires = m_domainItemLocator
//                    .GetAll<IQuestionnaireOutputItem>();
//            }

//            return existingQuestionnaires
//                .Select(x => x.ToModel<IQuestionnaireOutputItem>());
//        }
//    }
//}