using System;
using System.Globalization;
using System.Windows;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces
{
    public class IntegerQuestionType : QuestionTypeBase, IQuestionType
    {
        public string GetValue(ISymbolTable symbolTable, IQuestionNode question)
        {
            return symbolTable
                .Lookup<decimal>(question.Id)
                .ToString(CultureInfo.InvariantCulture);
        }

        public void InitializeVariable(ISymbolTable symbolTable, Guid questionId)
        {
            symbolTable.Add(questionId, default(int));
        }

        public bool IsValidOperation(IRelationalLogicNode operation)
        {
            return true;
        }

        public string GetTypeDisplay()
        {
            return @"integer";
        }

        public DataTemplate GetTemplate(DependencyObject container)
        {
            return GetTemplate(container, "IntTemplate");
        }

        public bool IsNumeric()
        {
            return true;
        }
    }
}