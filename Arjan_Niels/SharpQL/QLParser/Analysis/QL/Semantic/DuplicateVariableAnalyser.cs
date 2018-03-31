using QLParser.AST.QL;
using QLParser.AST.QL.ExpressionNodes;
using System.Collections.Generic;
using System.Linq;

namespace QLParser.Analysis.QL.Semantic
{
    /// <summary>
    /// The DuplicateVariableAnalyser walks over the tree and looks if their aren't any duplicate variable names.
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
        /// <param name="node"></param>
        /// <param name="logErrors"></param>
        /// <returns></returns>
        public bool Analyse(QLNode node)
        {
            // Reset the Analyser
            this.VisitedIDs.Clear();

            // This visit discovers all the identifiers.
            this.Visit(node);
            return VisitedIDs.Count == VisitedIDs.Distinct().Count();
        }

        public bool AddVariable(string id, QValueType type)
        {
            VisitedIDs.Add(id);
            if (!SymbolTable.Add(id, type))
            {
                Analyser.AddMessage(string.Format("Duplicate identifier {0} {1}", id, type), LanguageType.QL, MessageType.ERROR);
                return false;
            }

            return true;
        }

        public void Visit(QuestionNode node)
        {
            var id = node.ID;
            var type = node.ValueType;
            AddVariable(id, type);

            VisitChildren(node);
        }

        public void Visit(ComputedNode node)
        {
            var id = node.ID;
            var type = node.ValueType;
            AddVariable(id, type);

            VisitChildren(node);
        }

        public void Visit(FormNode node)
        {
            VisitChildren(node);
        }

        public void Visit(QLNode node)
        {
            VisitChildren(node);
        }

        public void Visit(ExpressionNode node)
        {
            VisitChildren(node);
        }

        public void Visit(ConditionalNode node)
        {
            VisitChildren(node);
        }

        private void VisitChildren(QLNode node)
        {
            foreach (var child in node.Children)
                child.Accept(this);
        }
    }
}