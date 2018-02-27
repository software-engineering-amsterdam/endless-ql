using System;
using QuestionaireDomain.Entities.API;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class CalculationAst : AstNodeBase, ICalculationAst
    {
        public CalculationAst(Guid id, string calculationName) : base(id)
        {
            CalculationName = calculationName;
        }

        public override void Accept(IAstVisitor visitor)
        {
            throw new NotImplementedException();
        }

        public string CalculationName { get; }
    }
}