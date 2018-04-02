using System;
using System.Globalization;
using System.Windows;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces
{
    public class DecimalQuestionType : QuestionTypeBase, IQuestionType
    {
        public string GetValue(ISymbolTable symbolTable, IQuestionNode question)
        {
            return symbolTable
                .Lookup<decimal>(question.Id)
                .ToString(CultureInfo.InvariantCulture);
        }

        public void InitializeVariable(ISymbolTable symbolTable, Guid questionId)
        {
            symbolTable.Add(questionId, default(decimal));
        }

        public bool IsValidOperation(IRelationalLogicNode operation)
        {
            return true;
        }

        public string GetTypeDisplay()
        {
            return @"decimal";
        }

        public DataTemplate GetTemplate(DependencyObject container)
        {
            return GetTemplate(container, "DecimalTemplate");
        }

        public bool IsNumeric()
        {
            return true;
        }
    }
}