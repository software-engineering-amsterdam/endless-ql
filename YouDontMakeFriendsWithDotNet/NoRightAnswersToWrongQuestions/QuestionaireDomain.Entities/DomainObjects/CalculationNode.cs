//using System;
//using QuestionaireDomain.Entities.API;
//using QuestionaireDomain.Entities.API.AstNodes;
//using QuestionaireDomain.Entities.API.AstNodes.Calculation;

//namespace QuestionaireDomain.Entities.DomainObjects
//{
//    public class CalculationNode : AstNodeBase, ICalculationNode
//    {
//        public CalculationNode(Guid id, string calculationDefinition) : base(id)
//        {
//            CalculationDefinition = calculationDefinition;
//        }
        
//        public string CalculationDefinition { get; }

//        public override void Accept(IAstVisitor visitor)
//        {
//            throw new NotImplementedException();
//        }
//    }
//}