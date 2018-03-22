using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;

namespace QuestionnaireDomain.Entities.Output.Tools
{
    internal class CalculationService : ICalculationService
    {
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly ICalculationVisitor m_calculationVisitor;
        private readonly ISymbolTable m_symbolTable;

        public CalculationService(
            IDomainItemLocator domainItemLocator,
            ICalculationVisitor calculationVisitor,
            ISymbolTable symbolTable)
        {
            m_domainItemLocator = domainItemLocator;
            m_calculationVisitor = calculationVisitor;
            m_symbolTable = symbolTable;
        }

        public void UpdateCalculations()
        {
            var calculationQuestions = m_domainItemLocator
                .GetAll<ICalculatedQuestionNode>()
                .ToList();

            foreach (var calculatedQuestion in calculationQuestions)
            {
                var calculation = calculatedQuestion.CalculatedValue;
                var result = m_calculationVisitor.Calculate(calculation);
                m_symbolTable.Update(calculatedQuestion.Id, result);
            }
        }
    }
}
