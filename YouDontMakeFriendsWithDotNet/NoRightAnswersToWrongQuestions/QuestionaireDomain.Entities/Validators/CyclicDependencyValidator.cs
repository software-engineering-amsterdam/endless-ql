//using System.Collections.Generic;
//using System.Linq;
//using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
//using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
//using QuestionnaireDomain.Entities.Domain;
//using QuestionnaireDomain.Entities.Domain.Interfaces;
//using QuestionnaireDomain.Entities.Validators.Interfaces;
//using QuestionnaireDomain.Entities.Validators.MetaData;

//namespace QuestionnaireDomain.Entities.Validators
//{
//    internal class CyclicDependencyValidator : ICyclicDependencyValidator
//    {
//        private readonly IDomainItemLocator m_domainItemLocator;
//        private readonly IVariableService m_variableService;

//        public CyclicDependencyValidator(
//            IDomainItemLocator domainItemLocator,
//            IVariableService variableService)
//        {
//            m_domainItemLocator = domainItemLocator;
//            m_variableService = variableService;
//        }

//        public IEnumerable<ValidationMetaData> Validate(
//            Reference<IQuestionnaireRootNode> questionnaireRootNode)
//        {
//            var questionNodes = m_domainItemLocator
//                .GetAll<ICalculatedQuestionNode>()
//                .ToList();

//            foreach (var questionNode in questionNodes)
//            {

//                questionNode.



//            }
//        }
//    }
//}