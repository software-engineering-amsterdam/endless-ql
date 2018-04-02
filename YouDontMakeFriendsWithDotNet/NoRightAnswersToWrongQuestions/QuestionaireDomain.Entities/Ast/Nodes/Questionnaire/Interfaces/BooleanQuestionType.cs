using System;
using System.Windows;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces
{
    public class BooleanQuestionType : QuestionTypeBase, IQuestionType
    {
        public string GetValue(ISymbolTable symbolTable, IQuestionNode question)
        {
            return symbolTable.Lookup<bool>(question.Id).ToString();
        }

        public void InitializeVariable(ISymbolTable symbolTable, Guid questionId)
        {
            symbolTable.Add(questionId, default(bool));
        }
        
        public bool IsValidOperation(IRelationalLogicNode operation)
        {
            return operation is IEqualityNode || operation is IInequalityNode;
        }

        public string GetTypeDisplay()
        {
            return @"boolean";
        }

        public DataTemplate GetTemplate(DependencyObject container)
        {
            return GetTemplate(container, "BoolTemplate");
        }

        public bool IsNumeric()
        {
            return false;
        }
    }
}