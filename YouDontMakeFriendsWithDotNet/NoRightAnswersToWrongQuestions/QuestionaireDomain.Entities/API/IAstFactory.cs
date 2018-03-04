using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.API.AstNodes.Relational;
using QuestionaireDomain.Entities.DomainObjects;
using IVariableNode = QuestionaireDomain.Entities.API.AstNodes.Calculation.IVariableNode;

namespace QuestionaireDomain.Entities.API
{
    public interface IAstFactory
    {
        Reference<IRootNode> CreateQuestionnaire(
            string questionaireName,
            IEnumerable<Reference<IStatementNode>> statements);

        //Reference<ICalculationNode> CreateCalculation(
        //    string calculationDefinition);

        Reference<IConditionalStatementNode> CreateConditional(
            string questionDefinition,
            Reference<IBooleanLogicNode> predicate,
            IEnumerable<Reference<IStatementNode>> consequent,
            IEnumerable<Reference<IStatementNode>> alternative);

        Reference<IUserInputQuestionNode> CreateUserInputQuestion(string questionName, string questionText, Type questionType);

        Reference<ICalculatedQuestionNode> CreateCalculatedQuestion(
            string questionName,
            string questionText,
            Type questionType,
            Reference<ICalculationNode> calculation);

        Reference<INumberNode> CreateNumber(string numberText);
        Reference<AstNodes.Calculation.IVariableNode> CreateNumberVariableName(string variableName);

        Reference<AstNodes.Boolean.IVariableNode> CreateBooleanVariableName(
            string variableName);

        Reference<AstNodes.Boolean.ILiteralNode> CreateBooleanLiteral(
            string booleanString);

        Reference<AstNodes.Boolean.IAndNode> CreateAndOperation(
            Reference<IBooleanLogicNode> leftExpression,
            Reference<IBooleanLogicNode> rightExpression);

        Reference<AstNodes.Boolean.IOrNode> CreateOrOperation(
            Reference<IBooleanLogicNode> leftExpression,
            Reference<IBooleanLogicNode> rightExpression);

        Reference<INegateNode> CreateNegationOperation(
            Reference<IBooleanLogicNode> childExpression);

        Reference<IEqualityNode> CreateEqualityOperation(
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
    }
}
