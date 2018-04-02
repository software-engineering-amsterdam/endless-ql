using System;
using System.Windows;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces
{
    public interface IQuestionType
    {
        string GetValue(ISymbolTable symbolTable, IQuestionNode question);
        void InitializeVariable(ISymbolTable symbolTable, Guid questionId);
        bool IsValidOperation(IRelationalLogicNode operation);
        string GetTypeDisplay();
        DataTemplate GetTemplate(DependencyObject container);
        bool IsNumeric();
    }
}