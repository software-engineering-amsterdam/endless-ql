﻿using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.API.AstNodes.Relational;
using QuestionaireDomain.Entities.DomainObjects;
using ICalculationVariableNode = QuestionaireDomain.Entities.API.AstNodes.Calculation.IVariableNode;
using IBooleanVariableNode = QuestionaireDomain.Entities.API.AstNodes.Boolean.IVariableNode;
 
//ToDO split into questionairre, boolean logic and calcualtion ast nodes
namespace QuestionnaireDomain.Logic.Logic
{
    internal class AstFactory : IAstFactory
    {
        private readonly IIdMaker m_ids;
        private readonly IDomainItemRegistry m_registry;

        public AstFactory(IIdMaker ids, IDomainItemRegistry registry)
        {
            m_ids = ids;
            m_registry = registry;
        }

        public Reference<IRootNode> CreateQuestionnaire(
            string questionaireName,
            IEnumerable<Reference<IStatementNode>> statements)
        {
            var questionnaire = new AstNode(
                m_ids.Next,
                questionaireName,
                statements);
            return AstNodeRegistration<IRootNode>(questionnaire);
        }

        //public Reference<ICalculationNode> CreateCalculation(string calculationDefinition)
        //{
        //    var calculation = new CalculationNode(
        //        m_ids.Next, 
        //        calculationDefinition);
        //    return AstNodeRegistration<ICalculationNode>(calculation);
        //}

        public Reference<IConditionalStatementNode> CreateConditional(
            string questionDefinition, 
            Reference<IBooleanLogicNode> predicate, 
            IEnumerable<Reference<IStatementNode>> consequent,
            IEnumerable<Reference<IStatementNode>> alternative)
        {
            var condition = new ConditionalAst(
                m_ids.Next, 
                questionDefinition,
                predicate,
                consequent,
                alternative);

            return AstNodeRegistration<IConditionalStatementNode>(condition);
        }
        
        public Reference<IUserInputQuestionNode> CreateUserInputQuestion(
            string questionName, 
            string questionText, 
            Type questionType)
        {
            var question = new UserInputQuestion(m_ids.Next, questionName, questionText, questionType);
            return AstNodeRegistration<IUserInputQuestionNode>(question);
        }

        public Reference<ICalculatedQuestionNode> CreateCalculatedQuestion(
            string questionName, 
            string questionText, 
            Type questionType,
            Reference<ICalculationNode> calculation)
        {
            var question = new CalculatedQuestion(
                m_ids.Next, 
                questionName, 
                questionText, 
                questionType,
                calculation);

            return AstNodeRegistration<ICalculatedQuestionNode>(question);
        }

        private Reference<T> AstNodeRegistration<T>(T node) where T : IAstNode
        {
            m_registry.Add(node);
            return new Reference<T>(node.Id);
        }

        public Reference<INumberNode> CreateNumber(string numberText)
        {
            var number = new NumberNode(m_ids.Next, numberText);
            return AstNodeRegistration<INumberNode>(number);
        }

        public Reference<ICalculationVariableNode> CreateNumberVariableName(string variableName)
        {
            var variable = new CalculationVariableNode(m_ids.Next, variableName);
            return AstNodeRegistration<ICalculationVariableNode>(variable);
        }

        public Reference<IBooleanVariableNode> CreateBooleanVariableName(string variableName)
        {
            var variable = new BooleanVariableNode(m_ids.Next, variableName);
            return AstNodeRegistration<IBooleanVariableNode>(variable);
        }

        public Reference<ILiteralNode> CreateBooleanLiteral(string booleanString)
        {
            var literal = new BooleanLiteralNode(m_ids.Next, booleanString);
            return AstNodeRegistration<ILiteralNode>(literal);
        }

        public Reference<IAndNode> CreateAndOperation(
            Reference<IBooleanLogicNode> leftExpression,
            Reference<IBooleanLogicNode> rightExpression)
        {
            var andNode = new AndNode(m_ids.Next, leftExpression, rightExpression);
            return AstNodeRegistration<IAndNode>(andNode);
        }

        public Reference<IOrNode> CreateOrOperation(
            Reference<IBooleanLogicNode> leftExpression,
            Reference<IBooleanLogicNode> rightExpression)
        {
            var orNode = new OrNode(m_ids.Next, leftExpression, rightExpression);
            return AstNodeRegistration<IOrNode>(orNode);
        }

        public Reference<INegateNode> CreateNegationOperation(
            Reference<IBooleanLogicNode> childExpression)
        {
            var negateNode = new NegateNode(m_ids.Next, childExpression);
            return AstNodeRegistration<INegateNode>(negateNode);
        }

        public Reference<IEqualityNode> CreateEqualityOperation(
            Reference<IAstNode> leftExpression, 
            Reference<IAstNode> rightExpression)
        {
            var equalityNode = new EqualityNode(
                m_ids.Next, 
                leftExpression, 
                rightExpression);

            return AstNodeRegistration<IEqualityNode>(equalityNode);
        }

        public Reference<IAstNode> CreateMultiplicationOperation(
            string defintion,
            Reference<ICalculationNode> leftExpression, 
            Reference<ICalculationNode> rightExpression)
        {
            var multiplicationNode = new MultiplyNode(
                m_ids.Next,
                defintion,
                leftExpression,
                rightExpression);

            return AstNodeRegistration<IMultiplyNode>(multiplicationNode);
        }

        public Reference<IAstNode> CreateDivision(
            string defintion,
            Reference<ICalculationNode> leftExpression, 
            Reference<ICalculationNode> rightExpression)
        {
            var divisionNode = new DivideNode(
                m_ids.Next,
                defintion,
                leftExpression,
                rightExpression);

            return AstNodeRegistration<IDivideNode>(divisionNode);
        }
    }
}
