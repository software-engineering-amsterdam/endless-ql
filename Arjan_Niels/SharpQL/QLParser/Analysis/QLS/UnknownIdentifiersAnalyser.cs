using QLParser.AST.QLS;
using System.Collections.Generic;
using System.Linq;

namespace QLParser.Analysis.QLS
{
    public class UnknownIdentifiersAnalyser : IQLSAnalyser, IQLSVisitor
    {
        private IList<QLSNode> _visitedNodes;
        public UnknownIdentifiersAnalyser()
        {
            this._visitedNodes = new List<QLSNode>();
        }

        public bool Analyse(QLSNode node)
        {
            this._visitedNodes.Clear();
            this.Visit(node);
            var isValid = true;
            foreach (var visitedNode in _visitedNodes)
            {
                if (!SymbolTable.Instance.TypeMap.Keys.Contains(visitedNode.ID))
                {
                    Analyser.AddMessage(string.Format("{0} Unknown identifier in QLS: {1}", visitedNode.Location, visitedNode.ID), LanguageType.QLS, MessageType.ERROR);
                    isValid = false;
                }
            }

            return isValid;
        }

        public void Visit(QLSQuestionNode node)
        {
            this._visitedNodes.Add(node);
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
