using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational;
using QuestionnaireDomain.Entities.Ast.Nodes.Relational.Interfaces;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireInfrastructure.API;

namespace QuestionnaireDomain.Entities.Ast.Tools
{
    internal class AstFactory : IAstFactory
    {
        private readonly IIdMaker m_ids;
        private readonly IDomainItemRegistry m_registry;
        private readonly ISymbolTable m_symbolTable;

        public AstFactory(
            IIdMaker ids, 
            IDomainItemRegistry registry,
            ISymbolTable symbolTable)
        {
            m_ids = ids;
            m_registry = registry;
            m_symbolTable = symbolTable;
        }

        public DomainId<IQuestionnaireRootNode> CreateQuestionnaire(
            string definition,
            string questionnaireName,
            IEnumerable<DomainId<IStatementNode>> statements)
        {
            var questionnaire = new QuestionnaireRootNode(
                m_ids.Next,
                definition,
                questionnaireName,
                statements);

            return DomainItemRegistration<IQuestionnaireRootNode>(questionnaire);
        }
        
        public DomainId<IConditionalStatementNode> CreateConditional(
            string definition, 
            DomainId<IBooleanLogicNode> predicate, 
            IEnumerable<DomainId<IStatementNode>> consequent,
            IEnumerable<DomainId<IStatementNode>> alternative)
        {
            var condition = new ConditionalNode(
                m_ids.Next, 
                definition,
                predicate,
                consequent,
                alternative);

            return DomainItemRegistration<IConditionalStatementNode>(condition);
        }

        public DomainId<IUserInputQuestionNode> CreateUserInputQuestion(
            string definition,
            string questionName,
            string questionText,
            IQuestionType questionType)
        {
            var question = new UserInputQuestionNode(
                m_ids.Next,
                definition,
                questionName,
                questionText,
                questionType);

            questionType.InitializeVariable(m_symbolTable,question.Id);
       
            return DomainItemRegistration<IUserInputQuestionNode>(question);
        }

    
        public DomainId<ICalculatedQuestionNode> CreateCalculatedQuestion(
            string definition, 
            string questionName, 
            string questionText, 
            IQuestionType questionType,
            DomainId<ICalculationNode> calculation)
        {
            var question = new CalculatedQuestionNode(
                m_ids.Next,
                definition,
                questionName,
                questionText,
                questionType,
                calculation);

            return DomainItemRegistration<ICalculatedQuestionNode>(question);
        }

        public DomainId<INumberNode> CreateNumber(string numberText)
        {
            var number = new NumberNode(m_ids.Next, numberText);
            return DomainItemRegistration<INumberNode>(number);
        }

        public DomainId<ICalculationVariableNode> CreateNumberVariableName(string variableName)
        {
            var variable = new CalculationVariableNode(m_ids.Next, variableName);
            return DomainItemRegistration<ICalculationVariableNode>(variable);
        }

        public DomainId<IBooleanVariableNode> CreateBooleanVariableName(string variableName)
        {
            var variable = new BooleanVariableNode(m_ids.Next, variableName);
            return DomainItemRegistration<IBooleanVariableNode>(variable);
        }

        public DomainId<IBooleanLiteralNode> CreateBooleanLiteral(string booleanString)
        {
            var literal = new BooleanLiteralNode(m_ids.Next,booleanString);
            return DomainItemRegistration<IBooleanLiteralNode>(literal);
        }

        public DomainId<IAndNode> CreateAndOperation(
            string definition,
            DomainId<IBooleanLogicNode> leftExpression,
            DomainId<IBooleanLogicNode> rightExpression)
        {
            var andNode = new AndNode(
                m_ids.Next, 
                definition,
                leftExpression, 
                rightExpression);

            return DomainItemRegistration<IAndNode>(andNode);
        }

        public DomainId<IOrNode> CreateOrOperation(
            string definition,
            DomainId<IBooleanLogicNode> leftExpression,
            DomainId<IBooleanLogicNode> rightExpression)
        {
            var orNode = new OrNode(
                m_ids.Next, 
                definition,
                leftExpression, 
                rightExpression);

            return DomainItemRegistration<IOrNode>(orNode);
        }

        public DomainId<INegateNode> CreateNegationOperation(
            string definition,
            DomainId<IBooleanLogicNode> childExpression)
        {
            var negateNode = new NegateNode(
                m_ids.Next, 
                definition,
                childExpression);

            return DomainItemRegistration<INegateNode>(negateNode);
        }

        public DomainId<IEqualityNode> CreateEqualityOperation(
            string definition,
            DomainId<IAstNode> leftExpression, 
            DomainId<IAstNode> rightExpression)
        {
            var equalityNode = new EqualityNode(
                m_ids.Next, 
                definition,
                leftExpression, 
                rightExpression);

            return DomainItemRegistration<IEqualityNode>(equalityNode);
        }

        public DomainId<IAstNode> CreateMultiplicationOperation(
            string definition,
            DomainId<ICalculationNode> leftExpression, 
            DomainId<ICalculationNode> rightExpression)
        {
            var multiplicationNode = new MultiplyNode(
                m_ids.Next,
                definition,
                leftExpression,
                rightExpression);

            return DomainItemRegistration<IMultiplyNode>(multiplicationNode);
        }

        public DomainId<IAstNode> CreateDivisionOperation(
            string definition,
            DomainId<ICalculationNode> leftExpression, 
            DomainId<ICalculationNode> rightExpression)
        {
            var divisionNode = new DivideNode(
                m_ids.Next,
                definition,
                leftExpression,
                rightExpression);

            return DomainItemRegistration<IDivideNode>(divisionNode);
        }

        public DomainId<IAstNode> CreateAdditionOperation(
            string definition, 
            DomainId<ICalculationNode> leftExpression, 
            DomainId<ICalculationNode> rightExpression)
        {
            var addNode = new AddNode(
                m_ids.Next,
                definition,
                leftExpression,
                rightExpression);

            return DomainItemRegistration<IAddNode>(addNode);
        }

        public DomainId<IAstNode> CreateSubtractionOperation(
            string definition, 
            DomainId<ICalculationNode> leftExpression, 
            DomainId<ICalculationNode> rightExpression)
        {
            var subtractNode = new SubtractNode(
                m_ids.Next,
                definition,
                leftExpression,
                rightExpression);

            return DomainItemRegistration<ISubtractNode>(subtractNode);
        }

        public DomainId<IAstNode> CreateInequalityOperation(
            string definition, DomainId<IAstNode> leftExpression, 
            DomainId<IAstNode> rightExpression)
        {
            var inequalityNode = new InequalityNode(
                m_ids.Next,
                definition,
                leftExpression,
                rightExpression);

            return DomainItemRegistration<IInequalityNode>(inequalityNode);
        }

        public DomainId<IAstNode> CreateDate(string dateText)
        {
            var dateNode = new DateNode(
                m_ids.Next,
                dateText);

            return DomainItemRegistration<IDateNode>(dateNode);
        }

        public DomainId<IAstNode> CreateDateVariableName(string dateVariableName)
        {
            var dateNode = new DateVariableNode(
                m_ids.Next,
                dateVariableName);

            return DomainItemRegistration<IDateVariableNode>(dateNode);
        }

        public DomainId<IAstNode> CreateText(string text)
        {
            var dateNode = new TextNode(
                m_ids.Next,
                text);

            return DomainItemRegistration<ITextNode>(dateNode);
        }

        public DomainId<IAstNode> CreateTextVariableName(string textVariableName)
        {
            var textNode = new TextVariableNode(
                m_ids.Next,
                textVariableName);

            return DomainItemRegistration<ITextVariableNode>(textNode);
        }

        public DomainId<IAstNode> CreateGreaterThanOperation(
            string definition, 
            DomainId<IAstNode> leftExpression, 
            DomainId<IAstNode> rightExpression)
        {
            var greaterThanNode = new GreaterThanNode(
                m_ids.Next,
                definition,
                leftExpression,
                rightExpression);

            return DomainItemRegistration<IGreaterThanNode>(greaterThanNode);
        }

        public DomainId<IAstNode> CreateGreaterOrEqualOperation(
            string definition, 
            DomainId<IAstNode> leftExpression, 
            DomainId<IAstNode> rightExpression)
        {
            var greaterOrEqualNode = new GreaterOrEqualNode(
                m_ids.Next,
                definition,
                leftExpression,
                rightExpression);

            return DomainItemRegistration<IGreaterOrEqualNode>(greaterOrEqualNode);
        }

        public DomainId<IAstNode> CreateLessThanOperation(string definition, DomainId<IAstNode> leftExpression, DomainId<IAstNode> rightExpression)
        {
            var lessThanNode = new LessThanNode(
                m_ids.Next,
                definition,
                leftExpression,
                rightExpression);

            return DomainItemRegistration<ILessThanNode>(lessThanNode);
        }

        public DomainId<IAstNode> CreateLessOrEqualOperation(string definition, DomainId<IAstNode> leftExpression, DomainId<IAstNode> rightExpression)
        {
            var lessOrEqualNode = new LessOrEqualNode(
                m_ids.Next,
                definition,
                leftExpression,
                rightExpression);

            return DomainItemRegistration<ILessOrEqualNode>(lessOrEqualNode);
        }

        public DomainId<IUntypedVariableNode> CreateUntypedVariableName(string variableName)
        {
            var untypedVariableNode = new UntypedVariableNode(
                m_ids.Next,
                variableName);

            return DomainItemRegistration<IUntypedVariableNode>(untypedVariableNode);
        }

        private DomainId<T> DomainItemRegistration<T>(T node) where T : IDomainItem
        {
            m_registry.Add(node);
            return new DomainId<T>(node.Id);
        }
    }
}
