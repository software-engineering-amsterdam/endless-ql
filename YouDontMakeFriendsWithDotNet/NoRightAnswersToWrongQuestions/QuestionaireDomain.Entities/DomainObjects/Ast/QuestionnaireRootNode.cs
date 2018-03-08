using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;

namespace QuestionaireDomain.Entities.DomainObjects.Ast
{
    internal class QuestionnaireRootNode : AstNodeBase, IRootNode
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
        
        public override void Accept(IAstVisitor visitor)
        {
            //ToDo: something here
        }
    }
}