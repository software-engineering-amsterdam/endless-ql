using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Tools.Interfaces
{
    public interface IAstFactory
    {
        DomainId<IQuestionnaireRootNode> CreateQuestionnaire(
            string definition,
            string questionnaireName,
            IEnumerable<DomainId<IStatementNode>> statements);

        DomainId<IConditionalStatementNode> CreateConditional(
            string definition,
            DomainId<IBooleanLogicNode> predicate,
            IEnumerable<DomainId<IStatementNode>> consequent,
            IEnumerable<DomainId<IStatementNode>> alternative);

        DomainId<IUserInputQuestionNode> CreateUserInputQuestion(
            string definition,
            string questionName,
            string questionText, 
            IQuestionType questionType);

        DomainId<ICalculatedQuestionNode> CreateCalculatedQuestion(
            string definition, 
            string questionName,
            string questionText,
            IQuestionType questionType,
            DomainId<ICalculationNode> calculation);

        DomainId<INumberNode> CreateNumber(string numberText);

        DomainId<ICalculationVariableNode> CreateNumberVariableName(string variableName);

        DomainId<IBooleanVariableNode> CreateBooleanVariableName(
            string variableName);

        DomainId<IBooleanLiteralNode> CreateBooleanLiteral(
            string booleanString);

        DomainId<IAndNode> CreateAndOperation(
            string definition,
            DomainId<IBooleanLogicNode> leftExpression,
            DomainId<IBooleanLogicNode> rightExpression);

        DomainId<IOrNode> CreateOrOperation(
            string definition,
            DomainId<IBooleanLogicNode> leftExpression,
            DomainId<IBooleanLogicNode> rightExpression);

        DomainId<INegateNode> CreateNegationOperation(
            string definition,
            DomainId<IBooleanLogicNode> childExpression);

        DomainId<IEqualityNode> CreateEqualityOperation(
            string definition,
            DomainId<IAstNode> leftExpression, 
            DomainId<IAstNode> rightExpression);

        DomainId<IAstNode> CreateMultiplicationOperation(
            string definition,
            DomainId<ICalculationNode> leftExpression, 
            DomainId<ICalculationNode> rightExpression);

        DomainId<IAstNode> CreateDivisionOperation(
            string definition,
            DomainId<ICalculationNode> leftExpression, 
            DomainId<ICalculationNode> rightExpression);

        DomainId<IAstNode> CreateAdditionOperation(
            string definition, 
            DomainId<ICalculationNode> leftExpression, 
            DomainId<ICalculationNode> rightExpression);

        DomainId<IAstNode> CreateSubtractionOperation(
            string definition, 
            DomainId<ICalculationNode> leftExpression, 
            DomainId<ICalculationNode> rightExpression);

        DomainId<IAstNode> CreateInequalityOperation(
            string definition, 
            DomainId<IAstNode> leftExpression, 
            DomainId<IAstNode> rightExpression);

        DomainId<IAstNode> CreateDate(string dateText);

        DomainId<IAstNode> CreateDateVariableName(string dateVariableName);

        DomainId<IAstNode> CreateText(string text);

        DomainId<IAstNode> CreateTextVariableName(string textVariableName);

        DomainId<IAstNode> CreateGreaterThanOperation(
            string definition, 
            DomainId<IAstNode> leftExpression, 
            DomainId<IAstNode> rightExpression);

        DomainId<IAstNode> CreateGreaterOrEqualOperation(
            string definition, 
            DomainId<IAstNode> leftExpression, 
            DomainId<IAstNode> rightExpression);

        DomainId<IAstNode> CreateLessThanOperation(
            string definition, 
            DomainId<IAstNode> leftExpression, 
            DomainId<IAstNode> rightExpression);

        DomainId<IAstNode> CreateLessOrEqualOperation(
            string definition, 
            DomainId<IAstNode> leftExpression, 
            DomainId<IAstNode> rightExpression);

        DomainId<IUntypedVariableNode> CreateUntypedVariableName(
            string variableName);
    }
}
