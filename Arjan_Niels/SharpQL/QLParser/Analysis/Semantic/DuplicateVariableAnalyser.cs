using QLParser.AST.Nodes;

namespace QLParser.Analysis.Semantic
{
    /// <summary>
    /// The TypeAnalyser walks over the tree and looks if all the variable type
    /// are in compliance with the language.
    /// </summary>
    public class DuplicateVariableAnalyser : IAnalyser
    {
        /// <summary>
        /// This function will return true if it doesn't encounter any problems. 
        /// </summary>
        /// <param name="node"></param>
        /// <param name="logErrors"></param>
        /// <returns></returns>
        public bool Analyse(Node node)
        {
            var result = true;
            string id = "";
            QValueType type = QValueType.UNKNOWN;
            if (node.Type == NodeType.QUESTION)
            {
                var questionNode = (QuestionNode)node;
                id = questionNode.ID;
                type = questionNode.ValueType;
                return AddVariable(id, type);
            }
            else if (node.Type == NodeType.COMPUTED)
            {
                var computedNode = (ComputedNode)node;
                id = computedNode.ID;
                type = computedNode.ValueType;
                return AddVariable(id, type);
            }

            // Set result to false if any of the children encounters an error.
            foreach (Node child in node.Children)
                if (!Analyse(child) && result)
                    result = false;

            return result;
        }

        public bool AddVariable(string id, QValueType type)
        {
            if (!SymbolTable.Add(id, type))
            {
                Analyser.AddMessage(string.Format("Duplicate identifier {0} {1}", id, type), MessageType.ERROR);
                return false;
            }

            return true;
        }
    }
}