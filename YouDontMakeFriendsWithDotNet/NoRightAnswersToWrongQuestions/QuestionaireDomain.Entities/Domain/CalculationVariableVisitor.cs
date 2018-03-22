//using System;
//using System.Collections.Generic;
//using System.Linq;
//using System.Text;
//using System.Threading.Tasks;
//using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
//using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
//using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

//namespace QuestionnaireDomain.Entities.Domain
//{
//    public interface ICalculationVariableVisitor
//    {

//    }

//    internal class CalculationVariableVisitor : ICalculationVariableVisitor
//    {
//        private IList<Reference<ICalculatedQuestionNode>> m_variables =
//            new List<Reference<ICalculatedQuestionNode>>();

//        public IList<Reference<ICalculatedQuestionNode>> Build(
//            Reference<ICalculationNode> node)
//        {
//            dynamic d = node;
//            this.VisitStart(d);
//            return m_variables;
//        }

//        public void VisitStart(ICalculationNode calc)
//        {
//            Visit(calc);
//        }


//        public void Visit(ICalculationVariableNode variableNode)
//        {

//        }
//    }
//}
