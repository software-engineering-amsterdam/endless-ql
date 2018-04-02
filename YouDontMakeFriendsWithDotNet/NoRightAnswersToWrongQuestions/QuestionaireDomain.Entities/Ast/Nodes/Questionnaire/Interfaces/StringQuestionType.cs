using System;
using System.Windows;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces
{
    public class StringQuestionType : QuestionTypeBase, IQuestionType
    {
        public string GetValue(ISymbolTable symbolTable, IQuestionNode question)
        {
            return symbolTable.Lookup<string>(question.Id) ?? "";
        }

        public void InitializeVariable(ISymbolTable symbolTable, Guid questionId)
        {
            symbolTable.Add(questionId, default(string));
        }

        public bool IsValidOperation(IRelationalLogicNode operation)
        {
            return operation is IEqualityNode || operation is IInequalityNode;
        }

        public string GetTypeDisplay()
        {
            return @"string";
        }

        public DataTemplate GetTemplate(DependencyObject container)
        {
            return GetTemplate(container, "StringTemplate");
        }

        public bool IsNumeric()
        {
            return false;
        }
    }
}