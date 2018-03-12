using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.Ast.Nodes.Common;
using QuestionaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.Ast.Nodes.Questionnaire
{
    internal class QuestionnaireRootNode : 
        AstNodeBase, 
        IQuestionnaireRootNode
    {
        public QuestionnaireRootNode(
            Guid id, 
            string definition,
            string questionnaireName, 
            IEnumerable<Reference<IStatementNode>> statements) 
            : base(id, definition)
        {
            QuestionnaireName = questionnaireName;
            Statements = statements;
        }

        public string QuestionnaireName { get; }

        public IEnumerable<Reference<IStatementNode>> Statements { get; }
    }
}