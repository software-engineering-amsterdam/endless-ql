using System;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QuestionnaireDomain.Entities.Domain
{
    internal class VariableService : IVariableService 
    {
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly ISymbolTable m_symbolTable;

        public VariableService(
            IDomainItemLocator domainItemLocator,
            ISymbolTable symbolTable)
        {
            m_domainItemLocator = domainItemLocator;
            m_symbolTable = symbolTable;
        }

        public IQuestionNode GetQuestionNode(string variableName)
        {
            return m_domainItemLocator
                .GetAll<IQuestionNode>()
                .FirstOrDefault(x => x.QuestionName == variableName);
        }

        public Type GetQuestionType(string variableName)
        {
            return GetQuestionNode(variableName)?.QuestionType;
        }

        public bool AreCompatible(string variableName1, string variableName2)
        {
            if (variableName1 == variableName2)
            {
                return true;
            }

            if (GetQuestionType(variableName1) == GetQuestionType(variableName2))
            {
                return true;
            }

            return IsNumeric(variableName1) && IsNumeric(variableName2);
        }

        public decimal GetNumberValue(string variableName)
        {
            var variableId = GetQuestionNode(variableName).Id;
            if (m_symbolTable.Exists<int>(variableId))
            {
                return m_symbolTable.Lookup<int>(variableId);
            }
            
            if (m_symbolTable.Exists<decimal>(variableId))
            {
                return m_symbolTable.Lookup<decimal>(variableId);
            }

            throw new ArgumentException(nameof(variableName), $"question {variableName} used as numeric but is not");
        }

        private bool IsNumeric(string variableName)
        {
            var type = GetQuestionType(variableName);
            return type == typeof(decimal) || type == typeof(int);
        }
    }
}
