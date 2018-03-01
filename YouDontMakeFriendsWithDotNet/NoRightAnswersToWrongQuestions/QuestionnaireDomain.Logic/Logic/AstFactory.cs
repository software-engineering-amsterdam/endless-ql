using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.DomainObjects;

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
            var questionnaire = new Ast(
                m_ids.Next,
                questionaireName,
                statements);
            return AstNodeRegistration<IRootNode>(questionnaire);
        }

        public Reference<ICalculationNode> CreateCalculation(string calculationDefinition)
        {
            var calculation = new CalculationNode(m_ids.Next, calculationDefinition);
            return AstNodeRegistration<ICalculationNode>(calculation);
        }

        public Reference<IConditionalStatementNode> CreateConditional(string questionDefinition)
        {
            var condition = new ConditionalAst(m_ids.Next, questionDefinition);
            return AstNodeRegistration<IConditionalStatementNode>(condition);
        }

        public Reference<IQuestionNode> CreateQuestion(
            string questionName, 
            string questionText, 
            Type questionType)
        {
            var question = new Question(m_ids.Next, questionName, questionText, questionType);
            return AstNodeRegistration<IQuestionNode>(question);
        }

        private Reference<T> AstNodeRegistration<T>(T node) where T : IAstNode
        {
            m_registry.Add(node);
            return new Reference<T> { Id = node.Id };
        }

        public Reference<INumberNode> CreateNumber(string numberText)
        {
            var number = new NumberNode(m_ids.Next, numberText);
            return AstNodeRegistration<INumberNode>(number);
        }

        public Reference<IVariableNode> CreateNumberVariableName(string variableName)
        {
            var variable = new VariableNode(m_ids.Next, variableName);
            return AstNodeRegistration<IVariableNode>(variable);
        }
    }
}
