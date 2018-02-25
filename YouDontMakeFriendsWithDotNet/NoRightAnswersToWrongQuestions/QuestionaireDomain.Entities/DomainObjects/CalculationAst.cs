using System;
using QuestionaireDomain.Entities.API;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class CalculationAst : AstNodeBase, ICalculationAst
    {
        public CalculationAst(string calculationName)
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