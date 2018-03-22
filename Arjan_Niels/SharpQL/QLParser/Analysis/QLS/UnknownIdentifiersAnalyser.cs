using QLParser.AST.QLS;
using System.Collections.Generic;
using System.Linq;

namespace QLParser.Analysis.QLS
{
    public class UnknownIdentifiersAnalyser : IQLSAnalyser, IQLSVisitor
    {
        private IList<string> VisitedIDs;
        public UnknownIdentifiersAnalyser()
        {
            this.VisitedIDs = new List<string>();
        }

        public bool Analyse(QLSNode node)
        {
            this.Visit(node);
            var isValid = true;
            foreach (var id in VisitedIDs)
            {
                if (!SymbolTable.Instance.TypeMap.Keys.Contains(id))
                {
                    Analyser.AddMessage(string.Format("Unknown identifier in QLS: {0}", id), MessageType.ERROR);
                    isValid = false;
                }
            }

            return isValid;
        }

        public void Visit(QLSQuestionNode node)
        {
            this.VisitedIDs.Add(node.ID);
            VisitChildren(node);
        }

        public void Visit(QLSStructuralNode node)
        {
            VisitChildren(node);
        }

        public void Visit(QLSNode node)
        {
            VisitChildren(node);
        }

        private void VisitChildren(QLSNode node)
        {
            foreach (var child in node.Children)
                child.Accept(this);
        }
    }
}
