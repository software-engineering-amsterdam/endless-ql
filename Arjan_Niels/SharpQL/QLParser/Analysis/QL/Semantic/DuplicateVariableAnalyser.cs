using QLParser.AST.QL;
using QLParser.AST.QL.ExpressionNodes;
using System.Collections.Generic;
using System.Linq;

namespace QLParser.Analysis.QL.Semantic
{
    /// <summary>
    /// The DuplicateVariableAnalyser walks over the tree and looks if their are any duplicate variables.
    /// </summary>
    public class DuplicateVariableAnalyser : IQLAnalyser, IQLVisitor
    {
        private IList<string> VisitedIDs;

        public DuplicateVariableAnalyser()
        {
            this.VisitedIDs = new List<string>();
        }

        /// <summary>
        /// This function will return true if it doesn't encounter any problems. 
        /// </summary>
        public bool Analyse(QLNode node)
        {
            // Reset the Analyser
            this.VisitedIDs.Clear();

            // This visit discovers all the identifiers.
            this.Visit((dynamic)node);
            return VisitedIDs.Count == VisitedIDs.Distinct().Count();
        }

        private bool AddVariable(string id, QValueType type)
        {
            VisitedIDs.Add(id);
            if (!SymbolTable.Add(id, type))
            {
                Analyser.AddMessage(string.Format("Duplicate identifier {0} {1}", id, type), Language.QL, MessageType.ERROR);
                return false;
            }

            return true;
        }

        public void Visit(QuestionNode node)
        {
            var id = node.ID;
            var type = node.ValueType;
            AddVariable(id, type);
        }

        public void Visit(ComputedNode node)
        {
            var id = node.ID;
            var type = node.ValueType;
            AddVariable(id, type);
        }

        public void Visit(FormNode node)
        {
            VisitChildren(node);
        }

        public void Visit(ConditionalNode node)
        {
            VisitChildren(node);
        }

        private void VisitChildren(QLCollectionNode node)
        {
            foreach (var child in node.Children)
                child.Accept(this);
        }

        public void Visit(QLCollectionNode node)
        {
            VisitChildren(node);
        }

        public void Visit(ExpressionNode node)
        {
            return;
        }
    }
}