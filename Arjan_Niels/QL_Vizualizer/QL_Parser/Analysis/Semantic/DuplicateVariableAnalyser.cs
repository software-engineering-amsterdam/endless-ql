using QL_Parser.AST.Nodes;

namespace QL_Parser.Analysis.Semantic
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

            if (node.Type == NodeType.QUESTION)
            {
                var questionNode = (QuestionNode)node;
                if (!SymbolTable.Add(questionNode.ID, questionNode.ValueType))
                {
                    Analyser.AddMessage(string.Format("Duplicate identifier {0} {1}", questionNode.ID, questionNode.ValueType), MessageType.ERROR);
                    return false;
                }
            }

            // Set result to false if any of the children encounters an error.
            foreach (Node child in node.Children)
                if (!Analyse(child) && result)
                    result = false;

            return result;
        }
    }
}