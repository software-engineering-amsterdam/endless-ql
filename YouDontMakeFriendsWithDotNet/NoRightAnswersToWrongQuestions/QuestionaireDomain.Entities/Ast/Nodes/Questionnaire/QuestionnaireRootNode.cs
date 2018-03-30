using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Common;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire
{
    internal class QuestionnaireRootNode : 
        AstNodeBase, 
        IQuestionnaireRootNode
    {
        public QuestionnaireRootNode(
            Guid id, 
            string definition,
            string questionnaireName, 
            IEnumerable<DomainId<IStatementNode>> statements) 
            : base(id, definition)
        {
            QuestionnaireName = questionnaireName;
            Statements = statements;
        }

        public string QuestionnaireName { get; }

        public IEnumerable<DomainId<IStatementNode>> Statements { get; }
    }
}