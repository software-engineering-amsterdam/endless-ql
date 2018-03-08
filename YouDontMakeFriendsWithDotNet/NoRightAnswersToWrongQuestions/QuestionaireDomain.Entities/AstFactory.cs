using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.API.AstNodes.Relational;
using QuestionaireDomain.Entities.DomainObjects;
using QuestionaireDomain.Entities.DomainObjects.Ast;
using QuestionnaireInfrastructure.API;

namespace QuestionaireDomain.Entities
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
            string definition,
            string questionaireName,
            IEnumerable<Reference<IStatementNode>> statements)
        {
            var questionnaire = new QuestionnaireRootNode(
                m_ids.Next,
                definition,
                questionaireName,
                statements);

            return AstNodeRegistration<IRootNode>(questionnaire);
        }
        
        public Reference<IConditionalStatementNode> CreateConditional(
            string definition, 
            Reference<IBooleanLogicNode> predicate, 
            IEnumerable<Reference<IStatementNode>> consequent,
            IEnumerable<Reference<IStatementNode>> alternative)
        {
            var condition = new ConditionalAst(
                m_ids.Next, 
                definition,
                predicate,
                consequent,
                alternative);

            return AstNodeRegistration<IConditionalStatementNode>(condition);
        }
        
        public Reference<IUserInputQuestionNode> CreateUserInputQuestion(
            string definition,
            string questionName, 
            string questionText, 
            Type questionType)
        {
            var question = new UserInputQuestionNode(
                m_ids.Next, 
                definition,
                questionName, 
                questionText, 
                questionType);

            return AstNodeRegistration<IUserInputQuestionNode>(question);
        }

        public Reference<ICalculatedQuestionNode> CreateCalculatedQuestion(
            string definition,
            string questionName, 
            string questionText, 
            Type questionType,
            Reference<ICalculationNode> calculation)
        {
            var question = new CalculatedQuestionNode(
                m_ids.Next, 
                definition,
                questionName, 
                questionText, 
                questionType,
                calculation);

            return AstNodeRegistration<ICalculatedQuestionNode>(question);
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
            var literal = new BooleanLiteralNode(m_ids.Next,booleanString);
            return AstNodeRegistration<ILiteralNode>(literal);
        }

        public Reference<IAndNode> CreateAndOperation(
            string definition,
            Reference<IBooleanLogicNode> leftExpression,
            Reference<IBooleanLogicNode> rightExpression)
        {
            var andNode = new AndNode(
                m_ids.Next, 
                definition,
                leftExpression, 
                rightExpression);

            return AstNodeRegistration<IAndNode>(andNode);
        }

        public Reference<IOrNode> CreateOrOperation(
            string definition,
            Reference<IBooleanLogicNode> leftExpression,
            Reference<IBooleanLogicNode> rightExpression)
        {
            var orNode = new OrNode(
                m_ids.Next, 
                definition,
                leftExpression, 
                rightExpression);

            return AstNodeRegistration<IOrNode>(orNode);
        }

        public Reference<INegateNode> CreateNegationOperation(
            string definition,
            Reference<IBooleanLogicNode> childExpression)
        {
            var negateNode = new NegateNode(
                m_ids.Next, 
                definition,
                childExpression);

            return AstNodeRegistration<INegateNode>(negateNode);
        }

        public Reference<IEqualityNode> CreateEqualityOperation(
            string definition,
            Reference<IAstNode> leftExpression, 
            Reference<IAstNode> rightExpression)
        {
            var equalityNode = new EqualityNode(
                m_ids.Next, 
                definition,
                leftExpression, 
                rightExpression);

            return AstNodeRegistration<IEqualityNode>(equalityNode);
        }

        public Reference<IAstNode> CreateMultiplicationOperation(
            string definition,
            Reference<ICalculationNode> leftExpression, 
            Reference<ICalculationNode> rightExpression)
        {
            var multiplicationNode = new MultiplyNode(
                m_ids.Next,
                definition,
                leftExpression,
                rightExpression);

            return AstNodeRegistration<IMultiplyNode>(multiplicationNode);
        }

        public Reference<IAstNode> CreateDivisionOperation(
            string definition,
            Reference<ICalculationNode> leftExpression, 
            Reference<ICalculationNode> rightExpression)
        {
            var divisionNode = new DivideNode(
                m_ids.Next,
                definition,
                leftExpression,
                rightExpression);

            return AstNodeRegistration<IDivideNode>(divisionNode);
        }

        public Reference<IAstNode> CreateAdditionOperation(
            string definition, 
            Reference<ICalculationNode> leftExpression, 
            Reference<ICalculationNode> rightExpression)
        {
            var addNode = new AddNode(
                m_ids.Next,
                definition,
                leftExpression,
                rightExpression);

            return AstNodeRegistration<IAddNode>(addNode);
        }

        public Reference<IAstNode> CreateSubtractionOperation(
            string definition, 
            Reference<ICalculationNode> leftExpression, 
            Reference<ICalculationNode> rightExpression)
        {
            var subtractNode = new SubtractNode(
                m_ids.Next,
                definition,
                leftExpression,
                rightExpression);

            return AstNodeRegistration<ISubtractNode>(subtractNode);
        }

        public Reference<IAstNode> CreateInequalityOperation(
            string definition, Reference<IAstNode> leftExpression, 
            Reference<IAstNode> rightExpression)
        {
            var inequalityNode = new InequalityNode(
                m_ids.Next,
                definition,
                leftExpression,
                rightExpression);

            return AstNodeRegistration<IInequalityNode>(inequalityNode);
        }

        public Reference<IAstNode> CreateDate(string dateText)
        {
            var dateNode = new DateNode(
                m_ids.Next,
                dateText);

            return AstNodeRegistration<IDateNode>(dateNode);
        }

        public Reference<IAstNode> CreateDateVariableName(string variableName)
        {
            var dateNode = new DateVariableNode(
                m_ids.Next,
                variableName);

            return AstNodeRegistration<IDateVariableNode>(dateNode);
        }

        public Reference<IAstNode> CreateText(string text)
        {
            var dateNode = new TextNode(
                m_ids.Next,
                text);

            return AstNodeRegistration<ITextNode>(dateNode);
        }

        public Reference<IAstNode> CreateTextVariableName(string variableName)
        {
            var textNode = new TextVariableNode(
                m_ids.Next,
                variableName);

            return AstNodeRegistration<ITextVariableNode>(textNode);
        }

        public Reference<IAstNode> CreateGreaterThanOperation(
            string definition, 
            Reference<IAstNode> leftExpression, 
            Reference<IAstNode> rightExpression)
        {
            var greaterThanNode = new GreaterThanNode(
                m_ids.Next,
                definition,
                leftExpression,
                rightExpression);

            return AstNodeRegistration<IGreaterThanNode>(greaterThanNode);
        }

        public Reference<IAstNode> CreateGreaterOrEqualOperation(
            string definition, 
            Reference<IAstNode> leftExpression, 
            Reference<IAstNode> rightExpression)
        {
            var greaterOrEqualNode = new GreaterOrEqualNode(
                m_ids.Next,
                definition,
                leftExpression,
                rightExpression);

            return AstNodeRegistration<IGreaterOrEqualNode>(greaterOrEqualNode);
        }

        public Reference<IAstNode> CreateLessThanOperation(string definition, Reference<IAstNode> leftExpression, Reference<IAstNode> rightExpression)
        {
            var lessThanNode = new LessThanNode(
                m_ids.Next,
                definition,
                leftExpression,
                rightExpression);

            return AstNodeRegistration<ILessThanNode>(lessThanNode);
        }

        public Reference<IAstNode> CreateLessOrEqualOperation(string definition, Reference<IAstNode> leftExpression, Reference<IAstNode> rightExpression)
        {
            var lessOrEqualNode = new LessOrEqualNode(
                m_ids.Next,
                definition,
                leftExpression,
                rightExpression);

            return AstNodeRegistration<ILessOrEqualNode>(lessOrEqualNode);
        }

        private Reference<T> AstNodeRegistration<T>(T node) where T : IAstNode
        {
            m_registry.Add(node);
            return new Reference<T>(node.Id);
        }
    }
}
