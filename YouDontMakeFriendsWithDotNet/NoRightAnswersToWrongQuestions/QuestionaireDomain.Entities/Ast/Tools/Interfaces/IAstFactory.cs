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
        Reference<IQuestionnaireRootNode> CreateQuestionnaire(
            string definition,
            string questionaireName,
            IEnumerable<Reference<IStatementNode>> statements);

        Reference<IConditionalStatementNode> CreateConditional(
            string definition,
            Reference<IBooleanLogicNode> predicate,
            IEnumerable<Reference<IStatementNode>> consequent,
            IEnumerable<Reference<IStatementNode>> alternative);

        Reference<IUserInputQuestionNode> CreateUserInputQuestion(
            string definition,
            string questionName,
            string questionText, 
            Type questionType);

        Reference<ICalculatedQuestionNode> CreateCalculatedQuestion(
            string definition, 
            string questionName,
            string questionText,
            Type questionType,
            Reference<ICalculationNode> calculation);

        Reference<INumberNode> CreateNumber(string numberText);

        Reference<ICalculationVariableNode> CreateNumberVariableName(string variableName);

        Reference<IBooleanVariableNode> CreateBooleanVariableName(
            string variableName);

        Reference<IBooleanLiteralNode> CreateBooleanLiteral(
            string booleanString);

        Reference<IAndNode> CreateAndOperation(
            string definition,
            Reference<IBooleanLogicNode> leftExpression,
            Reference<IBooleanLogicNode> rightExpression);

        Reference<IOrNode> CreateOrOperation(
            string definition,
            Reference<IBooleanLogicNode> leftExpression,
            Reference<IBooleanLogicNode> rightExpression);

        Reference<INegateNode> CreateNegationOperation(
            string definition,
            Reference<IBooleanLogicNode> childExpression);

        Reference<IEqualityNode> CreateEqualityOperation(
            string definition,
            Reference<IAstNode> leftExpression, 
            Reference<IAstNode> rightExpression);

        Reference<IAstNode> CreateMultiplicationOperation(
            string definition,
            Reference<ICalculationNode> leftExpression, 
            Reference<ICalculationNode> rightExpression);

        Reference<IAstNode> CreateDivisionOperation(
            string definition,
            Reference<ICalculationNode> leftExpression, 
            Reference<ICalculationNode> rightExpression);

        Reference<IAstNode> CreateAdditionOperation(
            string definition, 
            Reference<ICalculationNode> leftExpression, 
            Reference<ICalculationNode> rightExpression);

        Reference<IAstNode> CreateSubtractionOperation(
            string definition, 
            Reference<ICalculationNode> leftExpression, 
            Reference<ICalculationNode> rightExpression);

        Reference<IAstNode> CreateInequalityOperation(
            string definition, 
            Reference<IAstNode> leftExpression, 
            Reference<IAstNode> rightExpression);

        Reference<IAstNode> CreateDate(string dateText);

        Reference<IAstNode> CreateDateVariableName(string dateText);

        Reference<IAstNode> CreateText(string text);

        Reference<IAstNode> CreateTextVariableName(string text);

        Reference<IAstNode> CreateGreaterThanOperation(
            string definition, 
            Reference<IAstNode> leftExpression, 
            Reference<IAstNode> rightExpression);

        Reference<IAstNode> CreateGreaterOrEqualOperation(
            string definition, 
            Reference<IAstNode> leftExpression, 
            Reference<IAstNode> rightExpression);

        Reference<IAstNode> CreateLessThanOperation(
            string definition, 
            Reference<IAstNode> leftExpression, 
            Reference<IAstNode> rightExpression);

        Reference<IAstNode> CreateLessOrEqualOperation(
            string definition, 
            Reference<IAstNode> leftExpression, 
            Reference<IAstNode> rightExpression);
    }
}
