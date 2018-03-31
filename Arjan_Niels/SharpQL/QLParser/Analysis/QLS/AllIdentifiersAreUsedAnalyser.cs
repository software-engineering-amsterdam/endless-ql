using QLParser.AST.QLS;
using System.Collections.Generic;

namespace QLParser.Analysis.QLS
{
    public class AllIdentifiersAreUsedAnalyser : IQLSVisitor, IQLSAnalyser
    {
        private List<string> VisitedIDs;

        public AllIdentifiersAreUsedAnalyser()
        {
            this.VisitedIDs = new List<string>();
        }

        public bool Analyse(QLSNode node)
        {
            var isValid = true;
            this.VisitedIDs.Clear();
            this.Visit(node);

            foreach (var key in SymbolTable.Instance.TypeMap.Keys)
            {
                if (!this.VisitedIDs.Contains(key))
                {
                    Analyser.AddMessage(string.Format("Identifier has not been included in QLS: {0}", key), MessageType.WARNING);
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
