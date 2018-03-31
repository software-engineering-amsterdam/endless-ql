using QLParser.AST.QLS;
using System.Collections.Generic;
using System.Linq;

namespace QLParser.Analysis.QLS
{
    public class DuplicateIdentifiersAnalyser : IQLSAnalyser, IQLSVisitor
    {
        private IList<QLSNode> _visitedNodes;
        public DuplicateIdentifiersAnalyser()
        {
            this._visitedNodes = new List<QLSNode>();
        }

        public bool Analyse(QLSNode node)
        {
            this._visitedNodes.Clear();
            this.Visit(node);

            var isValid = true;
            foreach (var visitedNode in this._visitedNodes)
            {
                var idCount = this._visitedNodes.Count(x => x.ID == visitedNode.ID);
                if (idCount > 1)
                {
                    isValid = false;
                    Analyser.AddMessage(string.Format("{0} Duplicate key in QLS: {1}", visitedNode.Location, visitedNode.ID), LanguageType.QLS, MessageType.WARNING);
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
