using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class Reference<T> where T : IAstNode
    {
        public Guid Id { get; set; }

        public static implicit operator Reference<IAstNode>(Reference<T> d)
        {
            return new Reference<IAstNode> {Id = d.Id};
        }
    }
}