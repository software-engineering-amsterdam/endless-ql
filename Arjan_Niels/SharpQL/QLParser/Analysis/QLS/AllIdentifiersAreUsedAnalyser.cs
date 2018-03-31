using QLParser.AST.QLS;
using System.Collections.Generic;
using System.Linq;

namespace QLParser.Analysis.QLS
{
    public class AllIdentifiersAreUsedAnalyser : IQLSVisitor, IQLSAnalyser
    {
        private List<QLSNode> _visitedNodes;

        public AllIdentifiersAreUsedAnalyser()
        {
            this._visitedNodes = new List<QLSNode>();
        }

        public bool Analyse(QLSNode node)
        {
            var isValid = true;
            this._visitedNodes.Clear();
            this.Visit(node);

            foreach (var key in SymbolTable.Instance.TypeMap.Keys)
            {
                if (!this._visitedNodes.Select(x => x.ID).Contains(key))
                {
                    Analyser.AddMessage(string.Format("Identifier has not been included in QLS: {0}", key), Language.QLS, MessageType.WARNING);
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
