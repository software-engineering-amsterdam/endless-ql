using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class AstNode : AstNodeBase, IRootNode
    {
        public AstNode(
            Guid id, 
            string questionnaireName, 
            IEnumerable<Reference<IStatementNode>> statements) 
            : base(id)
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