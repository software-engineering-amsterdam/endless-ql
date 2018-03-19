using System;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QuestionnaireDomain.Entities.Domain
{
    internal class VariableService : IVariableService 
    {
        private readonly IDomainItemLocator m_domainItemLocator;

        public VariableService(IDomainItemLocator domainItemLocator)
        {
            m_domainItemLocator = domainItemLocator;
        }

        public Type GetType(string variableName)
        {
            return m_domainItemLocator
                .GetAll<IQuestionNode>()
                .FirstOrDefault(x => x.QuestionName == variableName)
                ?.QuestionType;
        }

        public bool AreCompatible(string variableName1, string variableName2)
        {
            if (variableName1 == variableName2)
            {
                return true;
            }

            if (GetType(variableName1) == GetType(variableName2))
            {
                return true;
            }

            return IsNumeric(variableName1) && IsNumeric(variableName2);
        }

        private bool IsNumeric(string variableName)
        {
            var type = GetType(variableName);
            return type == typeof(decimal) || type == typeof(int);
        }
    }
}
