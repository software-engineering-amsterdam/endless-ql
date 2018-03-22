using System;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;

namespace QuestionnaireDomain.Entities.Domain
{
    internal class VariableService : IVariableService 
    {
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly ISymbolTable m_symbolTable;
        private readonly ICalculationVisitor m_calculationVisitor;

        public VariableService(
            IDomainItemLocator domainItemLocator,
            ISymbolTable symbolTable,
            ICalculationVisitor calculationVisitor)
        {
            m_domainItemLocator = domainItemLocator;
            m_symbolTable = symbolTable;
            m_calculationVisitor = calculationVisitor;
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

        public void UpdateCalculations()
        {
            var calculationQuestions = m_domainItemLocator
                .GetAll<ICalculatedQuestionNode>()
                .ToList();

            //ToDo: (maybe) deal with dependencies / variable calculation order

            foreach (var calculatedQuestion in calculationQuestions)
            {
                var calculation = calculatedQuestion.CalculatedValue;
                var result = m_calculationVisitor.Calculate(calculation);
                m_symbolTable.Update(calculatedQuestion.Id, result);
            }
        }

        private bool IsNumeric(string variableName)
        {
            var type = GetType(variableName);
            return type == typeof(decimal) || type == typeof(int);
        }

        //public bool IsVariableInCalculation(string variableName, Reference<ICalculationNode> calculation)
        //{
        //    var calculationVariables = m_domainItemLocator
        //        .GetAll<ICalculationVariableNode>()
        //        .ToList();

        //    var usedInAnyCalculation = calculationVariables
        //        .Any(x => x.VariableName == variableName);
        //    if (!usedInAnyCalculation)
        //    {
        //        return false;
        //    }

        //    var variablesIcCalculation = calculation
        //}
    }
}
